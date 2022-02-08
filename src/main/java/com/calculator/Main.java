package com.calculator;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;


/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {

        Path path = Paths.get("./src/main/resources/operacoes.csv");

        Reader reader = null;
        List<String []> operacoes = new LinkedList<>();
        String [] line;
        CSVReader csvReader = null;
        int count = 0;
        try {
            reader = Files.newBufferedReader(path);
            csvReader = new CSVReader(reader);
            while((line = csvReader.readNext()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                operacoes.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }


        System.out.println(operacoes.get(0)[0]);
        System.out.println();


//        Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);


//        reader.;

//        LocalDateTime data = LocalDateTime.parse("2022-01-08T12:18:40", DateTimeFormatter.ISO_DATE_TIME);

        /*
        * map do nome do operador com um set de operacoes
        * */

        /*
         * carregar as operações para uma estrutura e organizar por conta,
         * ordenar por data e ir efetuando o manejamento da conta
         * */
    }
}
