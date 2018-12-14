package com.yieldbook.mortgage.bigdata.bean;

import com.opencsv.bean.CsvBindByPosition;

public class EmbsGnmaLoan {
		@CsvBindByPosition(position = 0)
	    private String columnName;

	    @CsvBindByPosition(position = 1)
	    private String type;

	    @CsvBindByPosition(position = 2)
	    private int length;

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}
	    

	}
