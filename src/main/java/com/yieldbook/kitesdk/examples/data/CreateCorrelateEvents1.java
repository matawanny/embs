package com.yieldbook.kitesdk.examples.data;

import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.kitesdk.data.DatasetDescriptor;
import org.kitesdk.data.Datasets;
import com.yieldbook.kitesdk.examples.data.CorrelatedEvents;

import com.google.common.base.Preconditions;


public class CreateCorrelateEvents1 extends BaseEventsTool {

  @Override
  public int run(List<String> args) throws Exception {

    String inputUri = uri;
    String outputUri = "dataset:hive:/prd/correlated_events";

    if (args.size() == 1) {
      outputUri = args.get(0);
    }

/*    Preconditions.checkState(Datasets.exists(inputUri),
        "input dataset doesn't exists");*/

    if (!Datasets.exists(outputUri)) {
      Datasets.create(outputUri, new DatasetDescriptor.Builder()
          .format("avro")
          .schema(CorrelatedEvents.class)
          .partitionStrategyUri("resource:gnma/embs_daily_partition.json")
          .build());
    }else{
        boolean success = Datasets.delete("dataset:hive:/prd/correlated_events");
        Datasets.create(outputUri, new DatasetDescriptor.Builder()
        .format("avro")
        .partitionStrategyUri("resource:gnma/embs_daily_partition.json")        
        .schema(CorrelatedEvents.class)
        .build());
    	
    }
/*    CorrelateEventsTask task = new CorrelateEventsTask(inputUri, outputUri);
    task.run();*/

    return 0;
  }

  public static void main(String[] args) throws Exception {
    int rc = ToolRunner.run(new Configuration(), new CreateCorrelateEvents1(), args);

    System.exit(rc);
  }
}
