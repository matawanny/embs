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

cd $APP/yb-bigdata/src/main/avro/gnma

kite-dataset delete dataset:hive://$HIVE:9083/prd/gnma_daily_pool
kite-dataset create dataset:hive://$HIVE:9083/prd/gnma_pool_daily --schema gnma_pool_daily.avsc --partition-by embs_daily_partition.json --format parquet


kite-dataset delete dataset:hive://$HIVE:9083/prd/gnma_mortgage_insurance_daily
kite-dataset create dataset:hive://$HIVE:9083/prd/gnma_mortgage_insurance_daily --schema mortgage_insurance.avsc --partition-by embs_daily_partition.json --format parquet
