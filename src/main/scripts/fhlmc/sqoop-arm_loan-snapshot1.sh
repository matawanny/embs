#!/bin/bash

HOME=/usr/book
table=fhlmc_arm_loan
id_column=product_id
dataset=${table}_sqoop
location=/user/hive/warehouse/prd/${dataset}
kite=$HOME/kite-dataset
#query="select * from \${table} where eff_date >= convert(datetime, '1/1/2016', 101)"
echo $query 
# delete the current version of the dataset
# ignore warnings if it wasn't there
$kite delete dataset:hive:prd/$dataset > /dev/null 2>&1
hadoop fs -rm -r -f hdfs:$location
# copy the snapshot from Sybase using Sqoop
#          --as-avrodatafile \
var1="hadoop.security.credential.provider.path=jceks://hdfs/user/embs/uat.password.jceks"
sqoop import -libjars /usr/book/jconn4.jar,/usr/book/jTDS3.jar,/usr/book/cryptojce.jar -D$var1 \
--verbose --connect 'jdbc:sybase:Tds:icgmrt390db1u.nam.nsroot.net:20021/prd' \
--username bpa \
--password-alias uat.password.alias  \
--table $table \
--driver com.sybase.jdbc4.jdbc.SybDriver \
--connection-manager com.sybase.jdbc4.jdbc.SybUrlManager
--as-parquetfile \
--compress --compression-codec snappy \
--target-dir $location \
--split-by $id_column \
--num-mappers 4

# re-create the dataset in Hive
# gets schema from data and creates all partitions
$kite create dataset:hive://$HIVE:9083/prd/$dataset --location hdfs:$location
