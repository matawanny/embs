#!/bin/bash

HOME=/usr/book
table=pool
id_column=agency_pool_id
dataset=${table}_sqoop
location=/user/hive/warehouse/${dataset}
kite=$HOME/kite-dataset

# delete the current version of the dataset
# ignore warnings if it wasn't there
$kite delete $dataset > /dev/null 2>&1

# copy the snapshot from Sybase using Sqoop
#          --as-avrodatafile \
sqoop import -libjars /usr/book/jconn4.jar --verbose --connect jdbc:sybase:Tds:ybrdmguat01.ny.ssmb.com:13110/prd \
          --username sa \
	  --password ybdbsa \
          --table $table \
	  --driver com.sybase.jdbc4.jdbc.SybDriver \
          --as-parquetfile \
          --compress --compression-codec snappy \
          --target-dir $location \
          --split-by $id_column \
          --num-mappers 2

# re-create the dataset in Hive
# gets schema from data and creates all partitions
$kite create $dataset --location hdfs:$location
