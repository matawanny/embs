kite-dataset create dataset:hive:old/fhlmc_arm_loan --schema fhlmc_arm_loan.avsc --partition-by year-month.json --format parquet
kite-dataset create dataset:hive:old/fhlmc_frm_loan --schema fhlmc_frm_loan.avsc --partition-by year-month.json --format parquet
kite-dataset create dataset:hive:old/fhlmc_frm_modified_loan --schema fhlmc_frm_modified_loan.avsc --partition-by year-month.json --format parquet
