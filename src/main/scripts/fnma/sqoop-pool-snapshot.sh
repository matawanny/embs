q/bin/bash

HOME=/usr/book
table=pool
id_column=product_id
dataset=${table}_sqoop
location=/user/hive/warehouse/prd/${dataset}
kite=$HOME/kite-dataset

# delete the current version of the dataset
# ignore warnings if it wasn't there
$kite delete dataset:hive:prd/$dataset > /dev/null 2>&1
hadoop fs -rm -r -f hdfs:$location
# copy the snapshot from Sybase using Sqoop
#          --as-avrodatafile \
sqoop import -libjars /usr/book/jconn4.jar --verbose --connect jdbc:sybase:Tds:icgmrt390db1u.nam.nsroot.net:20021/prd \
          --username bpa \
	  --password bpa123 \
          --table $table \
	  --driver com.sybase.jdbc4.jdbc.SybDriver \
          --as-parquetfile \
          --compress --compression-codec snappy \
          --target-dir $location \
          --split-by $id_column \
          --num-mappers 5

# re-create the dataset in Hive
# gets schema from data and creates all partitions
$kite create dataset:hive:prd/$dataset --location hdfs:$location
