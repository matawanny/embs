#!/bin/bash

START=$(date +%s)
SOURCE_FILENAME=$1
if [ -z "$SOURCE_FILENAME" ]
then
      echo "Must input source file name!"
      exit;
fi

currentUser=$(whoami)
if [ "$currentUser" != "oozie" ]
then
    echo "Must login in as oozie"
    exit;
fi	

FILESIZE=$(stat -c%s "$SOURCE_FILENAME")
filename=$1

ext=${filename##*.}
if [ "$ext" == "ZIP" ]
then
   unzip $filename
   filename=$(echo $filename|cut -f 1 -d '.')  
   filename+=".TXT"
fi

if [ "$ext" == "Z" ]
then
   gunzip $filename
   filename=$(echo $filename|cut -f 1 -d '.')
   filename+=".dat"
fi

echo $filename
FILELINES=$(wc -l $filename | awk  '{print $1;}') 
dos2unix $filename

name=$(echo $filename|cut -f 1 -d '.'|cut -f 2 -d '_')
prefix=$(echo $filename|cut -f 1 -d '.'|cut -f 1 -d '_')

if [ "$name" == "$prefix" ]
then
	sigName=$(echo $filename|cut -f 1 -d '.')
	sigName+=".SIG"
	if [ ! -f $sigName ]
        then
		echo "missing SIG file $sigName" 
		exit;
	fi

	day=$(cat $sigName|cut -f 1 -d ' ')
	time=$(cat $sigName|cut -f 2 -d ' ')
	asOfDate=$day
	asOfDate+=" "
	asOfDate+=$time
	asOfDate+=":00"
	
	asOfDate1=$day
	asOfDate1+=$time
	asOfDate1+=":00"
	echo "\t$day\t$time\t\t$asOfDate"
else
	asOfDate=$(($name+5))
	asOfDate1=$(($name+5))
	day=$asOfDate
	if [ "$prefix" == "FHLMONLA" ]
	then
        	asOfDate+=" 17:04:00"
        	asOfDate1+="17:04:00"
	else
        	asOfDate+=" 17:12:00"
        	asOfDate1+="17:12:00"
	fi
	echo "$prefix\t\t$asOfDate"
fi

timeSec=$(date -d "$asOfDate" +%s)
echo $timeSec
AS_OF_DATE=$timeSec
timeSec+="000"
newFileName=$(echo $filename|cut -f 1 -d '.')
newFileName+="_TS.dat"
echo $newFileName
echo "remove header if it has"
firstWord=$(head -n  1 $filename|cut -f 1 -d '|')
if [ "$firstWord" == "Loan Identifier" ]
then
   sed -i 1d $filename
fi

if [ -f $newFileName ]
then
	echo 'rm -rf $newFileName'
	rm -rf $newFileName
fi

libfile=/usr/book/repository/com/yieldbook/embs/0.0.1-SNAPSHOT/embs-0.0.1-SNAPSHOT-shaded.jar
sed -i 's/ *| */|/g' $filename

java -Xms1024m -Xmx2048m -jar $libfile -t fhlmc -i $filename -o $newFileName -d $asOfDate1

#awk  -F"|" '{print $0FS var}'var="$timeSec" $filename >> $newFileName 
#sed  "s/$/\|$timeSec/g" $filename >>$newFileName

kite-dataset csv-import $newFileName dataset:hive://ybrdev79:9083/prd/fhlmonly --delimiter '|' --no-header
year=${day:0:4}
month=${day:4:2}
#impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;use prd;select count(*) from fhlmonly where year=$year and month=$month" > /tmp/count.tsv

COUNT=$(impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;select count(*) from prd.fhlmonly where as_of_date=$timeSec")
if [ -z "$COUNT" ]
then COUNT=NULL
fi

END=$(date +%s)
DIFF=$(echo "$END - $START" | bc)
echo "SOURCE_FILENAME=$SOURCE_FILENAME	FILESIZE=$FILESIZE	FILELINES=$FILELINES	START=$START	DIFF=$DIFF	AS_OF_DATE=$AS_OF_DATE"
echo "YEAR=$year	MONTH=$month	COUNT=$COUNT"
impala-shell -B -i ybgdev79.ny.ssmb.com -q "invalidate metadata;insert into prd.monitors values ('$SOURCE_FILENAME',$FILESIZE,$FILELINES,$AS_OF_DATE,$year,$month,$START,$DIFF,$COUNT,'SUCCESS')"

#rm -rf *.TXT *_TS.dat