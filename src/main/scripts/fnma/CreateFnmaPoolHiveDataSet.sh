#!/bin/bash

if [ -z "$BASE" ]
then
  source $OOZIE_HOME/SetupEnv.sh
fi

currentUser=$(whoami)
if [ "$currentUser" != "oozie" ]
then
    echo "Must login in as oozie"
    exit 3;
fi

cd $APP/yb-bigdata/src/main/avro/fnma

kite-dataset delete dataset:hive://$HIVE:9083/prd/fnma_daily_pool_detail
kite-dataset create dataset:hive://$HIVE:9083/prd/fnma_daily_pool_detail --schema fnma_daily_pool_detail.avsc --partition-by embs_daily_partition.json --format parquet


kite-dataset delete dataset:hive://$HIVE:9083/prd/fnma_daily_pool_supplement
kite-dataset create dataset:hive://$HIVE:9083/prd/fnma_daily_pool_supplement --schema fnma_daily_pool_supplement.avsc --partition-by embs_daily_partition.json --format parquet

kite-dataset delete dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_detail
kite-dataset create dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_detail --schema fnma_monthly_pool_detail.avsc --partition-by embs_daily_partition.json --format parquet


kite-dataset delete dataset:hive://$HIVE:9083/prd/fnma_monthly_arm_pool
kite-dataset create dataset:hive://$HIVE:9083/prd/fnma_monthly_arm_pool --schema fnma_monthly_arm_pool.avsc --partition-by embs_daily_partition.json --format parquet

kite-dataset delete dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_supplement
kite-dataset create dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_supplement --schema fnma_monthly_pool_supplement.avsc --partition-by embs_daily_partition.json --format parquet

kite-dataset delete dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_multifamily_factor
kite-dataset create dataset:hive://$HIVE:9083/prd/fnma_monthly_pool_multifamily_factor --schema fnma_monthly_pool_multifamily_factor.avsc --partition-by embs_daily_partition.json --format parquet
