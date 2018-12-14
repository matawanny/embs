#!/bin/bash
START=$(date +%s)
SOURCE_FILENAME=$1
if [ -z "$SOURCE_FILENAME" ]
then
      echo "Must input source file name!"
      exit 10
fi

currentUser=$(whoami)
if [ "$currentUser" != "oozie" ]
then
    echo "Must login in as oozie"
    exit 11
fi	

FILESIZE=$(stat -c%s "$SOURCE_FILENAME")
filename=$1

ext=${filename##*.}

sigName=$(echo $filename|cut -f 1 -d '.')
sigName+=".SIG"
if [ ! -f $sigName ]
    then
	echo "missing SIG file $sigName" 
	exit 12
fi

day=$(cat $sigName|awk '{print $1}')
year=${day:0:4}
month=${day:4:2}
time=$(cat $sigName|awk '{print $2}')
asOfDate=$day
asOfDate+=$time
echo "\t$day\t$time\t\t$asOfDate"

asOfDate1=$day
asOfDate1+=" "
asOfDate1+=$time
timeSec=$(date -d "$asOfDate1" +%s)
timeSec+="000"=
echo $timeSec
AS_OF_DATE=$timeSec

if [ "$ext" == "ZIP" ]
then
   unzip -o $filename
fi

newFileName=$(echo $filename|awk -F. '{print $1}')
newFIleName+=$timeSec
newFileName+=".csv"
echo $newFileName

if [ -f $newFileName ]
then
	echo 'rm -rf $newFileName'
	rm -rf $newFileName
fi

FILES="$(zipinfo FNMMONLL.ZIP | grep dat | awk '{print $9}')"

let FILELENS=0
for zf in $FILES ; do
	echo "$zf"
	let FILELINES=${FILELINES}+$(wc -l $zf | awk  '{print $1;}')
	echo $FILELINES
	#dos2unix $zf
	sed -i 's/ *| */|/g' $zf
    java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fnma_loan -i $zf -o $newFileName -d $asOfDate
    #break
done
echo $FILELINES

let COUNT_LOAN=0

if [[ -s $newFileName ]] 
then
	echo "$newFileName has data."
        kite-dataset csv-import $newFileName dataset:hive://$HIVE:9083/prd/fnmamonly --delimiter '|' --no-header

else
        echo "$newFileName is empty"
        rm -rf $newFileName
        exit -1
fi

END=$(date +%s)
DIFF=$(echo "$END - $START" | bc)
echo "SOURCE_FILENAME=$SOURCE_FILENAME  FILESIZE=$FILESIZE      FILELINES=$FILELINES    START=$START    DIFF=$DIFF      AS_OF_DATE=$timeSec"
echo "YEAR=$year        MONTH=$month    COUNT=$COUNT"

COUNT=$(impala-shell -B -i $HIVE.ny.ssmb.com -q "invalidate metadata;select count(*) from prd.fnmamonly where as_of_date=$timeSec")
if [ -z "$COUNT" ]
then COUNT=NULL
fi

END=$(date +%s)
DIFF=$(echo "$END - $START" | bc)
echo "SOURCE_FILENAME=$SOURCE_FILENAME	FILESIZE=$FILESIZE	FILELINES=$FILELINES	START=$START	DIFF=$DIFF	AS_OF_DATE=$timeSec"
echo "YEAR=$year	MONTH=$month	COUNT=$COUNT"
impala-shell -B -i $HIVE.ny.ssmb.com -q "invalidate metadata; insert into prd.fnma_monitors values ('$SOURCE_FILENAME',$FILESIZE,$FILELINES,$timeSec,$year,$month,$START,$DIFF,$COUNT,'SUCCESS')"

# rm -rf *.dat
