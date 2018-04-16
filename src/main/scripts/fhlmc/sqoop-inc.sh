#!/bin/bash

table=ratings
id_column=timestamp
dataset=${table}_sqoop
job_name=import_${table}
temp_table=${dataset}_temp
location=/user/hive/warehouse/$temp_table
kite=$HOME/kite-dataset

num_jobs=`sqoop job --list | grep $job_name | wc -l`

if [[ $num_jobs == 0 ]]; then

  echo
  echo "Creating temporary dataset: $temp_table"
  echo

  $kite schema $dataset -o /tmp/schema-$unique.avsc || exit 1
  $kite create $temp_table --schema /tmp/schema-$unique.avsc || exit 1

  echo "Creating job $job_name"
  echo

  sqoop job --create $job_name -- import \
          --connect jdbc:postgresql://blue-2.vpc.cloudera.com/ \
          --username blue -P \
          --table $table \
          --as-avrodatafile \
          --append \
          --compress --compression-codec snappy \
          --target-dir $location \
          --split-by $id_column \
          --num-mappers 2 \
          --check-column $id_column \
          --incremental append \
          --last-value 0

  exit 0

fi

echo
echo "Running job $job_name"
echo

# the table has data so the job should exist
sqoop job --exec $job_name

echo
echo "Copying to Hive/Impala table: $dataset"
echo

$kite copy $temp_table $dataset || exit 1
$kite delete view:hive:$temp_table # remove all temp data

