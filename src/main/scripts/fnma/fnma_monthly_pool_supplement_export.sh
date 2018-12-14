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

cd $EXPORT/fnma
ls $AS_OF_DATE >/dev/null 2>&1
if [[ $? -ne 0 ]]
then
	echo "Create folder $AS_OF_DATE"
	mkdir $AS_OF_DATE
fi

asOfDateMonthly=${AS_OF_DATE:0:6}
asOfDateMonthly+="00"
echo $asOfDateMonthly

output_file=fnma_monthly_pool_supplement.dat

hive --hiveconf asofdate=$asOfDateMonthly --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select pool_number, pool_prefix, reporting_period, cusip_number, issue_date, current_upb, total_active_loan_count, 
quartile, third_party_origination_type, interest_only from prd.fnma_monthly_pool_supplement where 
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



output_file=fnma_monthly_pool_supplement_servicer.data

hive --hiveconf asofdate=$AS_OF_DATE --hiveconf lastchgdate=$AS_OF_DATE -e 'set hive.cli.print.header=true; 
select pool_number, pool_prefix, reporting_period, cusip_number, issue_date, s.servicer_name, s.servicer_upb, 
s.servicer_percent, IF(s.loan_count="",NULL,s.loan_count) as s.loan_count from prd.fnma_monthly_pool_supplement lateral view explode(servicers) dummy 
as s where as_of_date=${hiveconf:asofdate} and 
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
