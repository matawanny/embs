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

start=$1
if [ -z "$start" ]
then
      echo "Must input start date!"
      exit 11
fi

end=$2
if [ -z "$end" ]
then
      echo "Must input end date!"
      exit 12
fi

startDay=$(date -d "$start" +"%Y%m%d")
endDay=$(date -d "$end" +"%Y%m%d")
today=$(date +"%Y%m%d")
if [[ $startDay -gt $endDay ]]
then
    echo "Start day cannot after end day!"
    exit 13
fi

if [[ $endDay -gt $today ]]
then
    echo "End day cannot after today!"
    exit 14
fi      

d=$start
#d=$(date -d "$start" +"%Y-%m-%d")
end=$(echo $end | tr -d "-")

while [ "$d" -le "$end" ]
do
        day=$(date -d "$d" +%a)
        if [[ "$day" != "Sun" ]]
        then
          as_of_date=$(echo $d | tr -d "-")
          echo $as_of_date
          echo "sh $APP/yb-bigdata/src/main/scripts/fnma/DailyPoolProcess.sh $as_of_date"
          sh -x $APP/yb-bigdata/src/main/scripts/fnma/DailyPoolProcess.sh $as_of_date
          if [[ $? -eq 0 ]]
          then
	          echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_detail_export.sh $as_of_date"
	          sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_detail_export.sh $as_of_date
	          echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_supplement_export.sh $as_of_date"
	          sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_supplement_export.sh $as_of_date
          fi          
          FNMFCTR_MISSING=false
          FNMARM_MISSING=false
          FNMDISCL_MISSING=false
          FNMDUSMBS_MISSING=false
		  MONTHLY_POOL_PROCESS=false
          if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMFCTR.ZIP" ]
          then
            FNMFCTR_MISSING=true
          else
            MONTHLY_POOL_PROCESS=true
          fi
          if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMARM.ZIP" ]
          then
            FNMARM_MISSING=true
           else
            MONTHLY_POOL_PROCESS=true           
          fi
          if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMDISCL.ZIP" ]
          then
            FNMDISCL_MISSING=true
          else
            MONTHLY_POOL_PROCESS=true            
          fi          
          if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMDUSMBS.ZIP" ]
          then
            FNMDUSMBS_MISSING=true
          else
            MONTHLY_POOL_PROCESS=true            
          fi  
          if [ "$MONTHLY_POOL_PROCESS" = "true" ]
          then
              echo "$T sh  $APP/yb-bigdata/src/main/scripts/fnma/MonthlyPoolProcess.sh $as_of_date"      
              sh -x $APP/yb-bigdata/src/main/scripts/fnma/MonthlyPoolProcess.sh $as_of_date
	          if [ "$FNMFCTR_MISSING" = "false" ]
	          then
	           	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_detail_export.sh $as_of_date"
	          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_detail_export.sh $as_of_date
	          fi
	          if [ "$FNMARM_MISSING" = "false" ]
	          then
	          	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_arm_pool_export.sh $as_of_date"
	          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_arm_pool_export.sh $as_of_date
	          fi
	          if [ "$FNMDISCL_MISSING" = "false" ]
	          then	          	
	          	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_supplement_export.sh $as_of_date"
	          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_supplement_export.sh $as_of_date          	          	
	          fi
	          if [ "$FNMDUSMBS_MISSING" = "false" ]
	          then
	           	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_multifamily_factor_export.sh $as_of_date"
	          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_multifamily_factor_export.sh $as_of_date
	          fi	           
          fi         
        fi
        d=$(date -I -d "$d + 1 day")
        d=$(echo $d | tr -d "-")
done
