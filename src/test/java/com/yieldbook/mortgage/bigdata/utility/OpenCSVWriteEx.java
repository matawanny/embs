package com.yieldbook.mortgage.bigdata.utility;

import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class OpenCSVWriteEx {

    public static void main(String[] args) throws IOException {

        String[] entries = { "book", "coin", "pencil", "cup" }; 
        String fileName = "src/main/resources/items.csv";
        
        
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName), '|',
                		CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                		CSVWriter.DEFAULT_LINE_END)) {
            
            writer.writeNext(entries);
        }        
    }
}