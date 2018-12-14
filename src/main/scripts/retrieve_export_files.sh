#!/bin/bash

today=$(date +"%Y%m%d")

cd /vendordata/yb/embs/
ls $today

if [[ $? -ne 0 ]]
then
	echo "Create folder $today"
	mkdir $today
fi

cd $today

wget http://$HIVE.ny.ssmb.com/export/fnma/$today/fnma_loan_daily.dat
wget http://$HIVE.ny.ssmb.com/export/fnma/$today/fnma_arm_loan_daily.dat
wget http://$HIVE.ny.ssmb.com/export/fnma/$today/fnma_mod_loan_daily.dat

wget http://$HIVE.ny.ssmb.com/export/fnma/$today/fnma_loan_monthly.dat
wget http://$HIVE.ny.ssmb.com/export/fnma/$today/fnma_arm_loan_monthly.dat
wget http://$HIVE.ny.ssmb.com/export/fnma/$today/fnma_mod_loan_monthly.dat

wget http://$HIVE.ny.ssmb.com/export/gnma/$today/gnma_loan_daily.dat
wget http://$HIVE.ny.ssmb.com/export/gnma/$today/gnma_arm_loan_daily.dat

wget http://$HIVE.ny.ssmb.com/export/gnma/$today/gnma_loan_monthly.dat
wget http://$HIVE.ny.ssmb.com/export/gnma/$today/gnma_arm_loan_monthly.dat

wget http://ybrdev93.ny.ssmb.com/export/fhlmc/$today/fhlmc_loan_daily.dat
wget http://ybrdev93.ny.ssmb.com/export/fhlmc/$today/fhlmc_arm_loan_daily.dat
wget http://ybrdev93.ny.ssmb.com/export/fhlmc/$today/fhlmc_mod_loan_daily.dat

wget http://ybrdev93.ny.ssmb.com/export/fhlmc/$today/fhlmc_loan_monthly.dat
wget http://ybrdev93.ny.ssmb.com/export/fhlmc/$today/fhlmc_arm_loan_monthly.dat
wget http://ybrdev93.ny.ssmb.com/export/fhlmc/$today/fhlmc_mod_loan_monthly.dat