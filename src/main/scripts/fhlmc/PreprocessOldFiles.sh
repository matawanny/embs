#!/bin/bash
START=$(date +%s)
SOURCE_FILENAME=$1
if [ -z "$SOURCE_FILENAME" ]
then
      echo "Must input source file name!"
      exit;
fi

currentUser=$(whoami)
if [ "$currentUser" != "root" ]
then
    echo "Must login in as root"
    exit;
fi	

FILESIZE=$(stat -c%s "$SOURCE_FILENAME")
filename=$1

ext=${filename##*.}

if [ "$ext" = "Z" ]
then
   gunzip $filename
   filename=$(echo $filename|cut -f 1 -d '.')
   fileclass=$(echo $filename|cut -f 1 -d '_')
   prefix=$(echo $filename|cut -f 2 -d '_')
   filename+=".dat"
   asOfDate=$(($prefix+5))
   day=$asOfDate
   if [ "$prefix" = "FHLMONLA" ]
   then
       asOfDate+=" 17:04"
   else
                asOfDate+=" 17:12"
    fi
    year=${day:0:4}
    month=${day:4:2}
fi

AS_OF_DATE=$(date -d "$asOfDate" +%s)
echo $AS_OF_DATE

FILELINES=$(wc -l $filename | awk  '{print $1;}')
#dos2unix $filename


newFileName=$(echo $filename|cut -f 1 -d '.')
newFileName+="_LOAN.dat"
newFileNameMod=$(echo $filename|cut -f 1 -d '.')
newFileNameMod+="_MODIFIED_LOAN.dat"

if [ -f $newFileName ];
then
        echo 'rm -rf $newFileName'
        rm -rf $newFileName
fi

if [ -f $newFileNameMod ];
then
        echo 'rm -rf $newFileNameMod'
        rm -rf $newFileNameMod
fi

java -Xms1024m -Xmx2048m -jar $BIGDATA_JAR -t fhlmc_loan_old -i $filename
let COUNT_LOAN=0
let COUNT_MODIFIED_LOAN=0

if [[ -s $newFileName ]] 
then
	echo "$newFileName has data."
	sed -i 's/ *| */|/g' $newFileName 
        if [ "$fileclass" = "FHLMONLA" ]
        then
           kite-dataset csv-import $newFileName dataset:hive:old/fhlmc_arm_loan --delimiter '|' --no-header
           COUNT_LOAN=$(impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;use old;select count(*) from fhlmc_arm_loan where year=$year and month=$month")

        else
           kite-dataset csv-import $newFileName dataset:hive:old/fhlmc_frm_loan --delimiter '|' --no-header 
           COUNT_LOAN=$(impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;use old;select count(*) from fhlmc_frm_loan where year=$year and month=$month")
        fi

else
        echo "$newFileName is empty"
        rm -rf $newFileName
fi

if [[ -s $newFileNameMod ]]
then
        echo "$newFileNameMod has data."
	sed -i 's/ *| */|/g' $newFileNameMod
        kite-dataset csv-import $newFileNameMod dataset:hive:old/fhlmc_frm_modified_loan --delimiter '|' --no-header 
    COUNT_MODIFIED_LOAN=$(impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata;use old;select count(*) from fhlmc_frm_modified_loan where year=$year and month=$month")

else
        echo "$newFileNameMod is empty"
        rm -rf $newFileNameMod
fi
let COUNT=COUNT_LOAN+COUNT_MODIFIED_LOAN
END=$(date +%s)
DIFF=$(echo "$END - $START" | bc)
echo "SOURCE_FILENAME=$SOURCE_FILENAME  FILESIZE=$FILESIZE      FILELINES=$FILELINES    START=$START    DIFF=$DIFF      AS_OF_DATE=$AS_OF_DATE"
echo "YEAR=$year        MONTH=$month    COUNT=$COUNT"
impala-shell -B -i ybgdev93.ny.ssmb.com -q "invalidate metadata; use old; insert into monitors values ('$SOURCE_FILENAME',$FILESIZE,$FILELINES,$AS_OF_DATE,$year,$month,$START,$DIFF,$COUNT,'SUCCESS')"
if [ "$fileclass" = "FHLMONLA" ]
then
	rm -rf FHLMONLA*.dat 
else
	rm -rf FHLMONLF*.dat
fi


