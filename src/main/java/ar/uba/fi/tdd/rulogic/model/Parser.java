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

    public List<String> parse(String path){
        List<String> array = new ArrayList<>();
        try {
            File file = new File(path);
            Scanner input = new Scanner(file).useDelimiter("\\n");
            while (input.hasNextLine()) {
                array.add(input.nextLine());
            }

            input.close();


        }catch (FileNotFoundException e){
            //Todo: devolver error o algo
        }
        return array;
    }

    public List<String> readFile(String path) throws IOException{
        List<String> lines = new ArrayList<>();
        File dbFile = new File(path);
        BufferedReader buffer = new BufferedReader(new FileReader(dbFile));
        String line;
        while((line = buffer.readLine()) !=null){
            lines.add(line);

        }
        return lines;
    }

}
