#!/bin/bash

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
if [ -z "$AS_OF_DATE" ]
then
      echo "Must input as of date!"
      exit 11
fi

write_signature(){
  local prefixFileName=$(echo $1|cut -f 1 -d '.' )
  local agencyName=$(echo $1|cut -f 1 -d '_' )
  local sigFileName=$EXPORT
  sigFileName+="/"
  sigFileName+=$agencyName
  sigFileName+="/"
  sigFileName+=$2
  sigFileName+="/"
  sigFileName+=$prefixFileName
  sigFileName+=".SIG"
  
  local dataFileName=$prefixFileName
  dataFileName+=".dat"
  
  local output="`date "+%Y%m%d %H:%M:%S"`";
  output+=" "
  output+=$dataFileName
  echo $output > $sigFileName
}

if [ ! -d "$EXPORT/fnma/$AS_OF_DATE" ]
then
  echo "Create a folder $EXPORT/fnma/$AS_OF_DATE"
  mkdir $EXPORT/fnma/$AS_OF_DATE
fi
cd $EXPORT/fnma/$AS_OF_DATE

asOfDateMonthly=${AS_OF_DATE:0:6}
asOfDateMonthly+="00"
echo $asOfDateMonthly

output_file=fnma_monthly_pool_detail.dat

hive --hiveconf asofdate=$asOfDateMonthly --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select cusip, pool_prefix, pool_number, IF(pool_type="",NULL,pool_type) as pool_type, 
printf("%.2f", original_balance) as original_balance, cur_date, printf("%.2f", current_balance) as current_balance, 
current_factor, pass_through_rate, issue_date, maturity_date ,original_wa_maturity, 
seller_name, seller_street, seller_city, seller_state, seller_zip, original_wa_coupon, sd_security_type, 
sd_interest_rate, sd_pool_prefix, sd_pool_number, current_wa_coupon, current_wa_maturity  
from prd.fnma_monthly_pool_detail where as_of_date=${hiveconf:asofdate} and 
last_chg_date=${hiveconf:lastchgdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

ls >/dev/null 2>&1
if [[ $? -ne 0 ]]
then
	cd ..
	rmdir $AS_OF_DATE
fi
