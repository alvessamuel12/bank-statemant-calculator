package com.calculator.utils;

import com.calculator.model.Operation;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileIO {

    List<Operation> readFile(String pathFile);
    void writeFile(String filePath, String content) throws IOException;
    Path createFile(String path) throws IOException;
    Boolean createDirectory(Path pathDirectory) throws IOException;
}
