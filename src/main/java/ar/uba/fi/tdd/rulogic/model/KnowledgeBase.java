package ar.uba.fi.tdd.rulogic.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;


public class KnowledgeBase {

	private Parser parser;
	private Database database;



	public KnowledgeBase(){
		parser = new Parser();
		database = new Database();

	}

	public boolean answer(String query){

		String filteredQuery = filter(query);
		return database.has(filteredQuery);
	}

	public void useDatabase(String path){

		try {
			List<String> databaseEntries = parser.readFile(path);
			for (int i=0;i<databaseEntries.size();i++){
				String entry = filter(databaseEntries.get(i));
				if(!database.add(entry)){
					System.out.printf("Entrada invalida: %s",entry);
					throw new java.lang.RuntimeException("La base de datos es invalida");
				}
			}
		}catch (IOException e ){
			throw new java.lang.RuntimeException("Error parseando la base de datos");
		}

	}

	private String filter(String line){
		String filteredLine = line.replaceAll("\\ ","").replaceAll("\\.", "");
		return 	filteredLine;
	}

}
