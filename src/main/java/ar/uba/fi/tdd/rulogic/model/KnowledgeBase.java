package ar.uba.fi.tdd.rulogic.model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.util.List;
public class KnowledgeBase {

	private Parser parser;
	private Database database;

	public KnowledgeBase(){
		parser = new Parser();
		database = new Database();

	}

	public boolean answer(String query){
		return database.has(query);
	}

	public void useDatabase(String path){

		try {
			List<String> databaseEntries = parser.readFile(path);
			for (int i=0;i<databaseEntries.size();i++){
				if(!database.add(databaseEntries.get(i))){
					System.out.printf("Entrada invalida: %s",databaseEntries.get(i));
					throw new java.lang.RuntimeException("La base de datos es invalida");
				}
			}
		}catch (IOException e ){
			throw new java.lang.RuntimeException("Error parseando la base de datos");
		}

	}

}
