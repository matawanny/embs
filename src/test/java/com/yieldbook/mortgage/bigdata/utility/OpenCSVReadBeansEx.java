package com.yieldbook.mortgage.bigdata.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.yieldbook.mortgage.bigdata.bean.Car;

public class OpenCSVReadBeansEx {

	public static void main(String[] args) throws IOException {

		String fileName = "src/main/resources/cars.csv";
		Path myPath = Paths.get(fileName);

		try (BufferedReader br = Files.newBufferedReader(myPath,
				StandardCharsets.UTF_8)) {

			HeaderColumnNameMappingStrategy<Car> strategy = new HeaderColumnNameMappingStrategy<>();
			strategy.setType(Car.class);

			CsvToBean csvToBean = new CsvToBeanBuilder(br).withType(Car.class)
					.withMappingStrategy(strategy)
					.withIgnoreLeadingWhiteSpace(true).build();

			List<Car> cars = csvToBean.parse();

			cars.forEach(System.out::println);
		}
	}
}