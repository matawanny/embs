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


output_file=fnma_daily_pool_supplement.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; select pool_number, cusip, issue_date, 
security_description, printf("%.2f", issue_amount) as issue_amount, pass_through_rate, initial_accrual_rate, first_pi_date, seller, servicer, 
number_of_loans, average_loan_size, maturity_date, initial_interest_rate_change_date, wa_months_to_roll, 
sub_type, convertible, transfer_type, pass_through_method, wa_coupon, wa_max_pool_accrual_rate, 
wa_min_pool_accrual_rate, wa_loan_age, wa_loan_term, wa_remaining_maturity_at_issuance, wa_ltv, 
wa_credit_score, percent_upb_without_credit_score, percent_upb_with_interest_only, percent_upb_with_fully_amortizing, 
prefix, first_payment_change_date, percent_upb_with_third_party_origination, wa_combined_loan_to_value_ratio, 
wa_original_loan_size  from prd.fnma_daily_pool_supplement where 
as_of_date=${hiveconf:asofdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_daily_pool_quartile.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; select pool_number, cusip, issue_date, 
q.quartile_level, q.loan_size, q.coupon, q.ltv, q.credit_score, q.loan_term, q.loan_age, q.remaining_maturity 
from prd.fnma_daily_pool_supplement lateral view explode(quartiles) dummy as q where  
as_of_date=${hiveconf:asofdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_daily_pool_element.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; select pool_number, cusip, issue_date, 
e.element_name, e.entity_name, e.entity_value, e.number_of_loans, e.percent_of_upb, e.aggregate_upb  
from prd.fnma_daily_pool_supplement lateral view explode(elements) dummy as e where  
as_of_date=${hiveconf:asofdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_daily_pool_next_rage_change_date.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; select pool_number, cusip, issue_date, 
n.date, n.percent_of_balance, n.mbs_margin_high, n.mbs_margin_low, n.mbs_margin, n.net_coupon_high, n.net_coupon_low, 
n.wa_net_coupon, n.net_life_caps_high, n.net_life_caps_low, n.net_life_floor_high, n.net_life_floor_low   
from prd.fnma_daily_pool_supplement lateral view explode(next_rate_change_dates) dummy as n where  
as_of_date=${hiveconf:asofdate}' | sed 's/[[:space:]]\+/\|/g' | grep -v "^WARN" > $output_file 2> /dev/null

FILELINES=$(wc -l $output_file | awk  '{print $1;}') 
if [ $FILELINES -le 1 ]
then
   rm -rf $output_file
else 
	#sed -i "s/NULL//g" $output_file
	write_signature $output_file $AS_OF_DATE
fi

output_file=fnma_daily_pool_wa_for_next_rage_change_date.dat

hive --hiveconf asofdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; select pool_number, cusip, issue_date, 
w.wa_mbs_margin, w.wa_net_coupon, w.wa_net_life_caps, w.wa_net_life_floor 
from prd.fnma_daily_pool_supplement s lateral view explode(wa_for_next_rate_change_dates) dummy as w where  
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
