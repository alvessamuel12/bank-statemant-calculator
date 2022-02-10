package com.calculator.utils;

import com.calculator.model.Account;
import com.calculator.model.Operation;
import com.calculator.model.OperationOptions;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ManageFiles implements FileIO {



    @Override
    public List<Operation> readFile(String pathFile) {
        Path path = Paths.get(pathFile);
        Reader reader;
        List<Operation> operations = new LinkedList<>();
        String [] line;
        int count = 0;
        try {
            reader = Files.newBufferedReader(path);
            CSVReader csvReader = new CSVReader(reader);
            while((line = csvReader.readNext()) != null) {
                if (count == 0) {
                    count++;
                    continue;
                }
                operations.add(Creator.createOperation(line));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }

        return operations;
    }

    @Override
    public void writeFile(String filePath, String content) throws IOException {
        Path path = Paths.get(filePath);
        Files.writeString(path, content);
    }

    @Override
    public Path createFile(String path) throws IOException {
        return Files.createFile(Paths.get(path));
    }

    @Override
    public Boolean createDirectory(Path pathDirectory) throws IOException {
        Boolean isCreated = false;
        if (!new File(pathDirectory.toString()).exists()) {
            Files.createDirectory(pathDirectory);
            isCreated = true;
        }
        return isCreated;
    }

    public void cleanDirectory(Path pathDirectory) throws IOException {
        if(new File(pathDirectory.toString()).exists()) {
            File dir;
            dir = new File(pathDirectory.toString());
            Arrays.stream(dir.listFiles()).forEach(File::delete);
            Files.delete(pathDirectory);
            Files.createDirectory(pathDirectory);
        }
    }


}
