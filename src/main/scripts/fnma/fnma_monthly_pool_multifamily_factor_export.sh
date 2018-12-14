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

asOfDateMonthly=${AS_OF_DATE:0:6}
asOfDateMonthly+="00"
echo $asOfDateMonthly

if [ ! -d "$EXPORT/fnma/$AS_OF_DATE" ]
then
  echo "Create a folder $EXPORT/fnma/$AS_OF_DATE"
  mkdir $EXPORT/fnma/$AS_OF_DATE
fi
cd $EXPORT/fnma/$AS_OF_DATE

output_file=fnma_monthly_pool_multifamily_factor.dat

hive --hiveconf asofdate=$asOfDateMonthly --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select cur_date, cusip, prefix, transaction_id, security_description, issue_date, maturity_date, issuance_upb, 
security_upb_current, current_factor, paying_ptr_current, prepayment_allocation, prepayment_factor, 
wa_accruing_note_rate_issurance, wa_remaining_term_issurance, wa_accruing_note_rate_current  from 
prd.fnma_monthly_pool_multifamily_factor where as_of_date=${hiveconf:asofdate} and 
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
