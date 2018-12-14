package com.yieldbook.mortgage.bigdata.bean;

import com.opencsv.bean.CsvBindByPosition;

public class EmbsFnmaPool {
		@CsvBindByPosition(position = 0)
	    private String columnName;

	    @CsvBindByPosition(position = 1)
	    private String format;
	    
	    @CsvBindByPosition(position = 2)
	    private int start;	    

	    @CsvBindByPosition(position = 3)
	    private int length;

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}




}
