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


output_file=fnma_daily_pool_detail.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true;select pool_number, prefix, cusip_number, issue_date, 
round(pass_thru_rate, 3) as pas_thru_rate, round(original_wac,3) as original_wac, 
round(original_wam, 3) as original_wam, maturity_date, 
printf("%.2f", original_balance) as original_balance, round(smp_percent, 2) as smp_percent, 
round(margin, 4) as margin, IF(subtype="",NULL,subtype) as subtype, 
IF(transfer_type="",NULL,transfer_type) as transfer_type, first_rate_chg_date, 
first_paymt_chg_date, round(orig_net_life_cap, 4) as orig_net_life_cap, 
round(orig_net_life_floor, 4) as orig_net_life_floor, months_to_next_rt_chg from prd.fnma_daily_pool_detail where 
as_of_date=${hiveconf:asofdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_daily_pool_arm_reset.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; select pool_number, prefix, cusip_number, 
a.next_rate_chg_date as next_rate_chg_date, a.pct_balance as pct_balance, 
round(a.mbs_margin_high, 4) as mbs_margin_high, 
round(a.mbs_margin_low, 4) as mbs_margin_low, 
round(a.wtd_avg_mbs_margin, 4) as wtd_avg_mbs_margin, 
round(a.accrual_net_coupon_high, 4) as accrual_net_coupon_high, 
round(a.accrual_net_coupon_low, 4) as accrual_net_coupon_low, 
round(a.wtd_avg_net_coupon, 4) as wtd_avg_net_coupon, 
round(a.net_life_cap_high, 4) as net_life_cap_high, 
round(a.net_life_cap_low, 4) as net_life_cap_low, 
round(a.net_life_floor_high, 4) as net_life_floor_high, 
round(a.net_life_floor_low, 4) as net_life_floor_low 
from prd.fnma_daily_pool_detail lateral view explode(arm_resets) dummy as a  where 
as_of_date=${hiveconf:asofdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

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
