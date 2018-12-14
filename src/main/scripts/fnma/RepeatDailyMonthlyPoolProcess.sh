#!/bin/bash
print_current_time(){
  local output="`date "+%Y%m%d %H:%M:%S"`"
  $echo output
}

print_current_time

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

if [ -z "$1" ]
then
  today=$(date +"%Y%m%d")
else
  today=$(date -d "$1" +"%Y%m%d")  
fi

echo "today=$today"
weekday=$(date -d "$today" +%a)
case "$weekday" in 
	Mon)
		days=2
		;;
	*)  
	    days=1  
	    ;;	
esac	
lastBusinessDay=$(date -d "$days day ago" +"%Y%m%d")

ls $EXPORT/fnma/$lastBusinessDay/*daily_pool*.SIG >/dev/null 2>&1
if [[ $? -ne 0 ]]
then
  d=$lastBusinessDay
else
  d=$today  
fi
end=$today

while [ "$d" -le "$end" ]
do
        day=$(date -d "$d" +%a)
        if [[ "$day" != "Sun" ]]
        then
          as_of_date=$(echo $d | tr -d "-")
		  echo $as_of_date
          echo "sh $APP/yb-bigdata/src/main/scripts/fnma/DailyPoolProcess.sh $as_of_date"
          sh -x $APP/yb-bigdata/src/main/scripts/fnma/DailyPoolProcess.sh $as_of_date
          case $? in 
          1)
              echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_detail_export.sh $as_of_date"
	          sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_detail_export.sh $as_of_date          
          	;;
          2)
	          echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_supplement_export.sh $as_of_date"
	          sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_supplement_export.sh $as_of_date          
            ;;
          3)
              echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_detail_export.sh $as_of_date"
	          sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_detail_export.sh $as_of_date
	          echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_supplement_export.sh $as_of_date"
	          sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_daily_pool_supplement_export.sh $as_of_date
            ;;
          0)
              echo "No FNMA daily pool exporting files."
            ;;    
         esac 
        fi

	    FNMFCTR_MISSING=0
	    FNMARM_MISSING=0
	    FNMDISCL_MISSING=0
	    FNMDUSMBS_MISSING=0
	    MONTHLY_POOL_PROCESS=0
	    if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMFCTR.ZIP" ]
	    then
	      FNMFCTR_MISSING=1
	    else
	      MONTHLY_POOL_PROCESS=1
	    fi
	    if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMARM.ZIP" ]
	    then
	      FNMARM_MISSING=1
	    else
	      MONTHLY_POOL_PROCESS=1
	    fi
        if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMDISCL.ZIP" ]
        then
          FNMDISCL_MISSING=1
        else
          MONTHLY_POOL_PROCESS=1    
        fi          
        if [ ! -f "/net/ybr-prodnfs11/vendordata-PROD/data-grp15/embsdata/embs/daily/$as_of_date/Products/FNMDUSMBS.ZIP" ]
        then
          FNMDUSMBS_MISSING=1
        else
          MONTHLY_POOL_PROCESS=1
        fi
        if [ "$MONTHLY_POOL_PROCESS" -eq "1" ]
        then
          echo "$T sh  $APP/yb-bigdata/src/main/scripts/fnma/MonthlyPoolProcess.sh $as_of_date"
          sh -x $APP/yb-bigdata/src/main/scripts/fnma/MonthlyPoolProcess.sh $as_of_date
          if [ "$FNMFCTR_MISSING" -eq "0" ]
          then
           	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_detail_export.sh $as_of_date"
          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_detail_export.sh $as_of_date
          fi
          if [ "$FNMARM_MISSING" -eq "0" ]
          then
          	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_arm_pool_export.sh $as_of_date"
          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_arm_pool_export.sh $as_of_date
          fi
          if [ "$FNMDISCL_MISSING" -eq "0" ]
          then	          	
          	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_supplement_export.sh $as_of_date"
          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_supplement_export.sh $as_of_date          	          	
          fi 
          if [ "$FNMDUSMBS_MISSING" -eq "0" ]
          then
           	echo "sh $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_multifamily_factor_export.sh $as_of_date"
          	sh -x $APP/yb-bigdata/src/main/scripts/fnma/fnma_monthly_pool_multifamily_factor_export.sh $as_of_date
          fi         
        fi         	    
          
        d=$(date -I -d "$d + 1 day")
        d=$(echo $d | tr -d "-")
done