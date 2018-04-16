#!/bin/bash

table=ratings
id_column=timestamp
id_column_value=1112486027
unique=`date +%N`
dataset=${table}_${unique}
location=/user/hive/warehouse/${dataset}

# import one row to determine the schema
sqoop import --connect jdbc:postgresql://blue-2.vpc.cloudera.com/ \
          --username blue -P \
          --table $table \
          --as-parquetfile \
          --where "$id_column=$id_column_value" \
          --compress --compression-codec snappy \
          --target-dir $location \
          --split-by $id_column \
          --num-mappers 1

$HOME/kite-dataset create $dataset --location hdfs:$location

if [ -z "$1" ]; then
  echo "Schema:"
  $HOME/kite-dataset schema $dataset
else
  $HOME/kite-dataset schema $dataset -o $1
fi

$HOME/kite-dataset delete $dataset > /dev/null 2>&1

