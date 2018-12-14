package com.yieldbook.mortgage.bigdata.bean;

import com.opencsv.bean.CsvBindByPosition;

public class EmbsLoan {
		@CsvBindByPosition(position = 0)
	    private int mapping;

	    @CsvBindByPosition(position = 1)
	    private String columnName;

	    @CsvBindByPosition(position = 2)
	    private String type;
	    
		public int getMapping() {
			return mapping;
		}

		public void setMapping(int mapping) {
			this.mapping = mapping;
		}

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

		@Override
		public String toString() {
			return columnName + ", " + type + ", " + mapping;
		}

	}
