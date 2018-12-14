#!/bin/bash

START=$(date +%s)
currentUser=$(whoami)
if [ "$currentUser" != "oozie" ]
then
    echo "Must login in as oozie"
    exit 10
fi	

if [ -z "$BASE" ]
then
  source $OOZIE_HOME/SetupEnv.sh
fi

AS_OF_DATE=$1
day=$1

if [ -z "$AS_OF_DATE" ]
then
      echo "Must input as of date!"
      exit 11
fi

if [ ! -d "$EMBS/fnma/$AS_OF_DATE" ]
then
  echo "Create a folder $EMBS/fnma/$AS_OF_DATE"
  mkdir $EMBS/fnma/$AS_OF_DATE
fi
cd $EMBS/fnma/$AS_OF_DATE

FNMDAILY_MISSING=0
FNMDLYSU_MISSING=0
DAILY_POOL_PROCESSED=$(expr 0)
if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDAILY.ZIP" ]
then
	echo '$T There is no pool level file FNMDAILY.ZIP files under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/'
	FNMDAILY_MISSING=1
else  
	cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDAILY.ZIP .
	unzip -o FNMDAILY.ZIP
fi

if [ "$FNMDAILY_MISSING" -eq "0" ] 
then
	let DAILY_POOL_PROCESSED=DAILY_POOL_PROCESSED+1
	filename="FNMDAILY.TXT"
	echo "$T ETL $filename"
	hadoop fs -ls /user/hive/warehouse/prd.db/fnma_daily_pool_detail/as_of_date_copy=$AS_OF_DATE
	if [[ $? -eq 0 ]]
	then
		hadoop fs -rm -r /user/hive/warehouse/prd.db/fnma_daily_pool_detail/as_of_date_copy=$AS_OF_DATE
	fi
	
	java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fnma_daily_pool_detail -i $filename -d $AS_OF_DATE
fi


if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDLYSU.ZIP" ]
then
	echo '$T There is no supplement file FNMDLYSU.ZIP files under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/'
	FNMDLYSU_MISSING=1
else  
	cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDLYSU.ZIP .
	unzip -o FNMDLYSU.ZIP
fi

if [ "$FNMDLYSU_MISSING" -eq "0" ] 
then
	let DAILY_POOL_PROCESSED=DAILY_POOL_PROCESSED+2
	filename="FNMDLYSU.TXT"
	echo "$T ETL $filename"
	hadoop fs -ls /user/hive/warehouse/prd.db/fnma_daily_pool_supplement/as_of_date_copy=$AS_OF_DATE
	if [[ $? -eq 0 ]]
	then
		hadoop fs -rm -r /user/hive/warehouse/prd.db/fnma_daily_pool_supplement/as_of_date_copy=$AS_OF_DATE
	fi
	firstLine=$(head -n  1 $filename)
	if [[ $firstLine != *\|* ]]; then
	  echo "cut header"
	  sed -i 1d $filename
	fi
	
	java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fnma_daily_pool_supplement -i $filename -d $AS_OF_DATE
fi

echo "Clean up local files:"
#rm -rf FNMDAILY.* FNMDLYSU.*

echo "DAILY_POOL_PROCESSED=$DAILY_POOL_PROCESSED"
exit $DAILY_POOL_PROCESSED
