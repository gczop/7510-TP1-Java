package ar.uba.fi.tdd.rulogic.model;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by guillermo on 06/11/17.
 */
public class Parser {

    public Parser(){}

    public List<String> readFile(String path) throws IOException{
            List<String> lines = new ArrayList<>();
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            fileReader.close();
            bufferedReader.close();
            return lines;
    }

}
