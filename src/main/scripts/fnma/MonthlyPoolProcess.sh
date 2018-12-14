#!/bin/bash

START=$(date +%s)
currentUser=$(whoami)
if [ "$currentUser" != "oozie" ]
then
    echo "$T Must login in as oozie"
    exit 10
fi	

if [ -z "$BASE" ]
then
  source $OOZIE_HOME/SetupEnv.sh
fi

AS_OF_DATE=$1
asOfDateMonthly=${AS_OF_DATE:0:6}
asOfDateMonthly+="00"
echo $T $asOfDateMonthly
day=$1

if [ -z "$AS_OF_DATE" ]
then
      echo "$T Must input as of date!"
      exit 11
fi

if [ ! -d "$EMBS/fnma/$AS_OF_DATE" ]
then
  echo "Create a folder $EMBS/fnma/$AS_OF_DATE"
  mkdir $EMBS/fnma/$AS_OF_DATE
fi
cd $EMBS/fnma/$AS_OF_DATE

MONTHLY_POOL_PROCESSED=$(expr 0)
FNMFCTR_MISSING=false
if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMFCTR.ZIP" ]
then
	echo "$T There is no monthly pool file FNMFCTR.ZIP files under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/"
    FNMFCTR_MISSING=true
else  
	cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMFCTR.ZIP .
	unzip -o FNMFCTR.ZIP
fi

if [ "$FNMFCTR_MISSING" != "true" ] 
then
	let MONTHLY_POOL_PROCESSED=MONTHLY_POOL_PROCESSED+1
	filename="FNMFCTR.TXT"
	echo "$T Process $filename"
	hadoop fs -ls /user/hive/warehouse/prd.db/fnma_monthly_pool_detail/as_of_date_copy=$asOfDateMonthly
	if [[ $? -eq 0 ]]
	then
		hadoop fs -rm -r /user/hive/warehouse/prd.db/fnma_monthly_pool_detail/as_of_date_copy=$asOfDateMonthly
	fi
	
	java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fnma_monthly_pool_detail -i $filename -d $AS_OF_DATE
fi

FNMARM_MISSING=false
if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMARM.ZIP" ]
then
	echo "$T There is no monthly ARM pool file FNMARM.ZIP files under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/"
	FNMARM_MISSING=true
else  
	cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMARM.ZIP .
    unzip -o FNMARM.ZIP
fi

if [ "$FNMARM_MISSING" != "true" ]
then
	let MONTHLY_POOL_PROCESSED=MONTHLY_POOL_PROCESSED+2
	filename="FNMARM.TXT"
	echo "$T Process $filename"
	hadoop fs -ls /user/hive/warehouse/prd.db/fnma_monthly_arm_pool/as_of_date_copy=$asOfDateMonthly
	if [[ $? -eq 0 ]]
	then
		hadoop fs -rm -r /user/hive/warehouse/prd.db/fnma_monthly_arm_pool/as_of_date_copy=$asOfDateMonthly
	fi
	#dos2unix $filename
	#echo "$T Cut first line."
	#sed -i 1d $filename
	java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fnma_monthly_arm_pool -i $filename -d $AS_OF_DATE
fi

FNMDISCL_MISSING=false
if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDISCL.ZIP" ]
then
	echo "$T There is no pool supplement monthly file FNMDISCL.ZIP files under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/"
	FNMDISCL_MISSING=true
else  
	cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDISCL.ZIP .
    unzip -o FNMDISCL.ZIP
fi

if [ "$FNMDISCL_MISSING" != "true" ] 
then
	let MONTHLY_POOL_PROCESSED=MONTHLY_POOL_PROCESSED+4
	filename="FNMDISCL.TXT"
	echo "$T Process $filename"
	
	hadoop fs -ls /user/hive/warehouse/prd.db/fnma_monthly_pool_supplement/as_of_date_copy=$asOfDateMonthly
	if [[ $? -eq 0 ]]
	then
		hadoop fs -rm -r /user/hive/warehouse/prd.db/fnma_monthly_pool_supplement/as_of_date_copy=$asOfDateMonthly
	fi
	#dos2unix $filename
	#echo "$T Cut first line."
	#sed -i 1d $filename
	java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fnma_monthly_pool_supplement -i $filename -d $AS_OF_DATE
fi

FNMDUSMBS_MISSING=false
if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDUSMBS.ZIP" ]
then
	echo "$T There is no pool supplement monthly file FNMDUSMBS.ZIP files under /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/"
	FNMDUSMBS_MISSING=true
else  
	cp /net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$AS_OF_DATE/Products/FNMDUSMBS.ZIP .
    unzip -o FNMDUSMBS.ZIP
fi

if [ "$FNMDUSMBS_MISSING" != "true" ] 
then
	let MONTHLY_POOL_PROCESSED=MONTHLY_POOL_PROCESSED+8
	filename="FNMDUSMBS.TXT"
	newFilename="FNMDUSMBS_TS.TXT"
	echo "$T Process $filename"
	firstWord=$(head -n  1 $filename|cut -f 1 -d ',')
	if [ "$firstWord" = "FNMA" ]
	then
	   sed -i 1d $filename
	fi
	sed  "s/$/\,$AS_OF_DATE\,$asOfDateMonthly/g" $filename >>$newFilename
	hadoop fs -ls /user/hive/warehouse/prd.db/fnma_monthly_pool_multifamily_factor/as_of_date_copy=$asOfDateMonthly
	if [[ $? -eq 0 ]]
	then
		hadoop fs -rm -r /user/hive/warehouse/prd.db/fnma_monthly_pool_multifamily_factor/as_of_date_copy=$asOfDateMonthly
	fi
	kite-dataset csv-import $newFilename dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_multifamily_factor --no-header	
fi

echo "$T Clean up local files:"
#rm -rf FNMFCTR.* FNMARM.* FNMDISCL.* FNMDUSMBS.*

echo "MONTHLY_POOL_PROCESSED=$MONTHLY_POOL_PROCESSED"
exit $MONTHLY_POOL_PROCESSED