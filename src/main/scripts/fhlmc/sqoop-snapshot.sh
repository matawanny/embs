#!/bin/bash

HOME=/usr/book
table=movies
id_column=movie_id
dataset=${table}_sqoop
location=/user/hive/warehouse/${dataset}
kite=$HOME/kite-dataset

# delete the current version of the dataset
# ignore warnings if it wasn't there
$kite delete $dataset > /dev/null 2>&1

# copy the snapshot from postgres using Sqoop
#          --as-avrodatafile \
sqoop import --connect jdbc:postgresql://172.28.5.193/blue1 \
          --username blue1 -P \
          --table $table \
          --as-parquetfile \
          --compress --compression-codec snappy \
          --target-dir $location \
          --split-by $id_column \
          --num-mappers 2

# re-create the dataset in Hive
# gets schema from data and creates all partitions
$kite create $dataset --location hdfs:$location
