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

output_file=fnma_monthly_arm_pool.dat

hive --hiveconf asofdate=$asOfDateMonthly --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select cusip_number, pool_issue_date, pool_maturity_date, lender_issuer, lender_city, state, subtype, transfer_type, 
pass_through_rate_structure, convertible_flag, deferred_interest_allowed, original_security_balance, 
original_number_of_loans, original_wa_coupon, original_wa_maturity, first_rate_change_date, security_balance_mlnm, 
security_balance_year, security_balance_month, current_security_balance, current_trading_factor, current_wa_coupon, 
current_wa_maturity, current_deferred_interest, return_deferred_interest, wa_months_to_rate_change, wa_mbs_margin, 
wa_lpt_life_cap, wa_lpt_life_life, wa_loan_margin, wa_negative_amortization_limit, published_pass_through_rate, 
rate_difference_flag, accrual_rate, lookback_rate_change, lookback_payment_change, per_adjustment_rate_cap, 
rate_adjustment_frequency, payment_change_frequency, amortization_recast_frequency, accrual_rate_rounding_method_code, 
payment_cap, minimum_index_movement, maximum_accrual_rate, minimum_accrual_rate from prd.fnma_monthly_arm_pool where 
as_of_date=${hiveconf:asofdate} and 
last_chg_date=${hiveconf:lastchgdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_monthly_arm_pool_next_rate_change_date.dat

hive --hiveconf asofdate=$asOfDateMonthly --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select cusip_number, pool_issue_date, n.next_rate_change_date, n.original_note_rate, n.percent_security_balance, 
n.mbs_margin_high, n.mbs_margin_low, n.pass_through_rate_cap_high, n.pass_through_rate_cap_low, 
n.pass_through_rate_floor_high, n.pass_through_rate_floor_low, n.pass_through_rate_high, n.pass_through_rate_low, 
n.cross_coupon_high, n.cross_coupon_low, n.wa_mbs_margin, n.wa_pass_through_rate, n.wa_coupon, n.note_rate_cap, 
IF(n.loan_count="",NULL,n.loan_count) as n.loan_count from prd.fnma_monthly_arm_pool lateral view explode(next_rate_change_dates) dummy as n where 
as_of_date=${hiveconf:asofdate} and 
last_chg_date=${hiveconf:lastchgdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_monthly_arm_pool_first_payment_date.dat

hive --hiveconf asofdate=$asOfDateMonthly --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select cusip_number, pool_issue_date, f.first_payment_date, f.original_note_rate, f.percent_security_balance, 
f.loan_survival_ratio from prd.fnma_monthly_arm_pool lateral view explode(first_payment_dates) dummy as f where 
as_of_date=${hiveconf:asofdate} 
and last_chg_date=${hiveconf:lastchgdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

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
