#!/bin/bash

START=$(date +%s)
currentUser=$(whoami)
if [ "$currentUser" != "oozie" ]
then
    echo "Must login in as oozie"
    exit;
fi	

AS_OF_DATE=$1
START=$1

if [ -z "$AS_OF_DATE" ]
then
      echo "Must input as of date!"
      exit;
fi

AS_OF_DATE_LENGTH=${#AS_OF_DATE}
if [ "$AS_OF_DATE_LENGTH" -lt "6" ]
then 
  AS_OF_DATE+=${START:0:4}
  AS_OF_DATE+="0"
  FORTH_DATE=$AS_OF_DATE
  AS_OF_DATE+=${START:5:1}
  FORTH_DATE+="04"
else 
  if [ "$AS_OF_DATE_LENGTH" -eq "6" ]
  then
     FORTH_DATE=$AS_OF_DATE
     AS_OF_DATE+="01"
     FORTH_DATE+="04"
  else
     AS_OF_DATE=$(echo ${START:0:6})
     FORTH_DATE=$AS_OF_DATE
     AS_OF_DATE+="01"
     FORTH_DATE+="04"
  fi   
fi

d=$FORTH_DATE
echo $FORTH_DATE
this_date=$FORTH_DATE
found=0
today=DATE=`date +%Y%m%d`
echo today
d=$(echo $d | tr -d "-")
while [[ "$found" -eq "0" && $d -le $today ]]
do
	this_date=$(echo $d | tr -d "-")
	ls /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$this_date/Products/FHLMONLF.ZIP
	if [[ $? -eq 0 ]]
	then
	    found=1
	 fi
    d=$(date -I -d "$d + 1 day")
    d=$(echo $d | tr -d "-")
done
echo $this_date
AS_OF_DATE=$this_date

cd /usr/book/embs/fhlmc
ls $AS_OF_DATE
if [[ $? -ne 0 ]]
then
  echo "Create a folder $AS_OF_DATE"
  mkdir $AS_OF_DATE
fi
cd $AS_OF_DATE
year=${AS_OF_DATE:0:4}
month=${AS_OF_DATE:4:2}

ls FHLMONLA.TXT
if [[ $? -ne 0 ]]
then
    ls FHLMONLA.ZIP
    if [[ $? -ne 0 ]]
    then
		echo copy source file FHLMONLA.ZIP, FHLMONLA.SIG
		ls /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FHLMONLA.ZIP
		if [[ $? -ne 0 ]]
		then 
			echo 'There is no monthly files FHLMONLA.ZIP under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/'
	  		exit 10
		else	
			cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FHLMONLA.ZIP .
			cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Signal/FHLMONLA.SIG .

		fi
	fi
	unzip FHLMONLA.ZIP
fi

ls FHLMONLF.TXT
if [[ $? -ne 0 ]]
then
    ls FHLMONLF.ZIP
    if [[ $? -ne 0 ]]
    then
		echo copy source file FHLMONLF.ZIP, FHLMONLF.SIG
		ls /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FHLMONLF.ZIP
		if [[ $? -ne 0 ]]
		then 
			echo 'There is no monthly files FHLMONLF.ZIP under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/'
	  		exit 10
		else	
			cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FHLMONLF.ZIP .
			cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Signal/FHLMONLF.SIG .
		fi
	fi
	unzip FHLMONLF.ZIP
fi

FILESIZE1=$(stat -c%s FHLMONLA.TXT)
FILESIZE2=$(stat -c%s FHLMONLF.TXT)
let FILEZIZE=$FILESIZE1+FILESIZE2

hadoop fs -ls /user/hive/warehouse/prd2.db/fhlmc_embs_monthly/year=$year/month=$month
if [[ $? -eq 0 ]]
then
	hadoop fs -rm -r /user/hive/warehouse/prd2.db/fhlmc_embs_monthly/year=$year/month=$month
fi

sigName=FHLMONLA.SIG
day=$(cat $sigName|cut -f 1 -d ' ')
time=$(cat $sigName|cut -f 2 -d ' ')
asOfDate=$day
asOfDate1=$day
asOfDate1+=$time
asOfDate1+=":00"
echo "\t$day\t$time\t\t$asOfDate1"

effectiveDate=${day:0:6}
effectiveDate+="01 00:00:00"
echo "$day $time $asOfDate"
echo "effectiveDate: $effectiveDate"
timeSec=$(date -d "$effectiveDate" +%s)
timeSec+="000"
echo $timeSec

filename=FHLMONLA.TXT
dos2unix $filename
sigName=$(echo $filename|cut -f 1 -d '.')
sigName+=".SIG"
if [ ! -f $sigName ]
then
	echo "missing SIG file $sigName" 
	exit;
fi

echo "remove header if it has"
firstWord=$(head -n  1 $filename|cut -f 1 -d '|')
if [ "$firstWord" == "Loan Identifier" ]
then
   sed -i 1d $filename
fi

newFileName=$(echo $filename|cut -f 1 -d '.')
newFileName+="_TS.dat"
if [ -f $newFileName ]
then
        echo 'rm -rf $newFileName'
        rm -rf $newFileName
fi

echo $asOfDate
sed  "s/$/\|$timeSec\/g" $filename >>$newFileName

filename2="FHLMONLF.TXT"
echo 'Process $filename2'

FILELINES2=$(wc -l $filename2 | awk  '{print $1;}') 
dos2unix $filename2

echo "remove header if it has"
firstWord=$(head -n  1 $filename2|cut -f 1 -d '|')
if [ "$firstWord" == "Loan Identifier" ]
then
   sed -i 1d $filename2
fi

newFileName2=$(echo $filename2|cut -f 1 -d '.')
newFileName2+="_TS.dat"

if [ -f $newFileName2 ]
then
        echo 'rm -rf $newFileName2'
        rm -rf $newFileName2
fi

sed  "s/$/\|$timeSec/g" $filename2 >>$newFileName2

newFileName3="FHLMONLAF_TS.dat"
newFileName4="FHLMONLAF_TS_SORT.dat"
newFileName5="FHLMONLAF_TS_SORT.csv"
if [ -f $newFileName3 ]
then
        echo 'rm -rf $newFileName3'
        rm -rf $newFileName3
fi


cat $newFileName $newFileName2 > $newFileName3
#sed -i 's/ *| */|/g' $newFileName3
sort $newFileName3 > $newFileName4

libfile=/usr/book/repository/com/yieldbook/embs/0.0.1-SNAPSHOT/embs-0.0.1-SNAPSHOT-shaded.jar


FHLMONLAF_num=$(wc -l $newFileName4 | awk  '{print $1;}')
let FHLMONLAF_1_start=1
let FHLMONLAF_1_end=FHLMONLAF_num/5
let FHLMONLAF_2_start=FHLMONLAF_1_end+1
let FHLMONLAF_2_end=FHLMONLAF_num/5*2
let FHLMONLAF_3_start=FHLMONLAF_2_end+1
let FHLMONLAF_3_end=FHLMONLAF_num/5*3
let FHLMONLAF_4_start=FHLMONLAF_3_end+1
let FHLMONLAF_4_end=FHLMONLAF_num/5*4
let FHLMONLAF_5_start=FHLMONLAF_4_end+1
let FHLMONLAF_5_end=FHLMONLAF_num

sed -n '$FHLMONLAF_1_start,$FHLMONLAF_1_endp' filename > FHLMONLAF_1.dat
java -Xms1024m -Xmx2048m -jar $libfile -t fhlmc -i FHLMONLAF_1.dat -o fhlmc_embs_monthly_1.csv

sed -n '$FHLMONLAF_2_start,$FHLMONLAF_2_endp' filename > FHLMONLAF_2.csv
java -Xms1024m -Xmx2048m -jar $libfile -t fhlmc -i FHLMONLAF_2.dat -o fhlmc_embs_monthly_2.csv

sed -n '$FHLMONLAF_3_start,$FHLMONLAF_3_endp' filename > FHLMONLAF_3.csv
java -Xms1024m -Xmx2048m -jar $libfile -t fhlmc -i FHLMONLAF_3.dat -o fhlmc_embs_monthly_3.csv

sed -n '$FHLMONLAF_4_start,$FHLMONLAF_4_endp' filename > FHLMONLAF_4.csv
java -Xms1024m -Xmx2048m -jar $libfile -t fhlmc -i FHLMONLAF_4.dat -o fhlmc_embs_monthly_4.csv

sed -n '$FHLMONLAF_5_start,$FHLMONLAF_5_endp' filename > FHLMONLAF_5.csv
java -Xms1024m -Xmx2048m -jar $libfile -t fhlmc -i FHLMONLAF_4.dat -o fhlmc_embs_monthly_5.csv


#hadoop fs -rm -r /user/oozie/source/fhlmc_embs_monthly

hadoop fs -copyFromLocal fhlmc_embs_monthly_1.csv
hadoop fs -copyFromLocal fhlmc_embs_monthly_2.csv
hadoop fs -copyFromLocal fhlmc_embs_monthly_3.csv
hadoop fs -copyFromLocal fhlmc_embs_monthly_4.csv
hadoop fs -copyFromLocal fhlmc_embs_monthly_5.dat


kite-dataset csv-import hdfs:/usr/oozie dataset:hive://ybrdev79:9083/prd2/fhlmc_embs_monthly --delimiter '|' --no-header

year=${day:0:4}
month=${day:4:2}
#impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;use prd2;select count(*) from prd2.fhlmc_embs_monthly where year=$year and month=$month" > /tmp/count.tsv

COUNT=$(impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;select count(*) from prd2.fhlmc_embs_monthly where as_of_date=$timeSec")
if [ -z "$COUNT" ]
then COUNT=NULL
fi

END=$(date +%s)
DIFF=$(echo "$END - $START" | bc)
echo "SOURCE_FILENAME=$SOURCE_FILENAME	FILESIZE=$FILESIZE	FILELINES=$FILELINES	START=$START	DIFF=$DIFF	AS_OF_DATE=$AS_OF_DATE"
echo "YEAR=$year	MONTH=$month	COUNT=$COUNT"
#impala-shell -B -i ybgdev79.ny.ssmb.com -q "invalidate metadata;insert into prd.monitors values ('$SOURCE_FILENAME',$FILESIZE,$FILELINES,$AS_OF_DATE,$year,$month,$START,$DIFF,$COUNT,'SUCCESS')"

#rm -rf *.TXT *_TS.dat