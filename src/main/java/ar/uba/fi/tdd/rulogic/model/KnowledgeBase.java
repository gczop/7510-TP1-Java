package ar.uba.fi.tdd.rulogic.model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

	public void tryTest(){
		String rule1 = "hijo(X, Y) :- varon(X), padre(Y, X).";
		String rule2 = "tio(X, Y, Z):- varon(X), hermano(X, Z), padre(Z, Y).";
		String fact = "padre(juan, pepe).";
		String filteredfact = "(juan,pepe)";
		String factRegex = "\\w+\\(\\w+(,\\w+)*\\)";
		String ruleRegex = "\\w+\\(\\w+(, \\w+)*\\) :- (\\w+\\(\\w+(, \\w+)*\\), )*\\w+\\(\\w+(, \\w+)*\\).";
		String parametersRegex = "\\(([^()]+)\\)";

		String _final = "varon(X),padre(Y,X)";

		boolean a = rule1.matches(ruleRegex);
		boolean b = rule2.matches(ruleRegex);
		boolean c = _final.matches(factRegex);
		boolean d = filteredfact.matches(parametersRegex);

		Pattern p = Pattern.compile(factRegex);
		Matcher m = p.matcher(_final);

		while(m.find()){
			String z = m.group();
			String o = "hola";
		}

	}



}
