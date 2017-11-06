package ar.uba.fi.tdd.rulogic.model;

import com.sun.deploy.security.ruleset.Rule;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guillermo on 04/11/17.
 */
public  class Database {

    private Hashtable<String, String> facts;
    private Hashtable<String, String> rules;
    private FactManager factManager;
    private RuleManager ruleManager;

    private String FACT_REGEX = "\\w+\\(\\w+(,\\w+)*\\)";
    private String RULE_REGEX = "\\w+\\(\\w+(,\\ \\w+)*\\)\\ \\:\\-\\ (\\w+\\(\\w+(,\\ \\w+)*\\),\\ )*";
//                      "\\w+\\(\\w+(, \\w+)*\\) :- (\\w+\\(\\w+(, \\w+)*\\), )*\\w+\\(\\w+(, \\w+)*\\)."

    public Database(){
        facts = new Hashtable<>();
        rules = new Hashtable<>();
        factManager = new FactManager(FACT_REGEX);
        ruleManager = new RuleManager(RULE_REGEX);
    }


    //Interfaz publica

    public boolean add(String value){
        if (ruleManager.isRule(value)){
            rules.put(ruleManager.getName(value), value);
            return true;
        }
        if(factManager.isFact(value)){
            facts.put(value, "true");
            return true;
        }
        else{
            return false;
        }
    }

    public boolean has(String query){
        if(!isValid(query)){
            throw new java.lang.RuntimeException("Invalid query");
        }
        return (hasFact(query) || hasRule(query));
    }



//Cosas genericas

    private boolean isValid(String query){
        boolean fact = factManager.isFact(query);
        boolean rule = ruleManager.isRule(query);
        return factManager.isFact(query)||ruleManager.isRule(query);
    }

    private String[] getParameters(String query){
        String regex1 = "\\([^)]+\\)";
        String regex2 = "[()]";
        Matcher m = Pattern.compile(regex1).matcher(query);
        String parameterString = m.group();
        return parameterString.replace(regex2, "").split(",");
    }

    private String replaceParameters(String rule, String query){
        String[] queryParameters = getParameters(query);
        String[] ruleParameters = getParameters(rule);
        String replacedRule = rule;
        if(queryParameters.length!=ruleParameters.length){
            throw new java.lang.RuntimeException("Error al evaluar regla");
        }
        for(int i=0;i<queryParameters.length;i++){
            replacedRule = replacedRule.replaceAll(ruleParameters[i],queryParameters[i]);
        }
        return replacedRule;
    }

    //Busqueda

    private boolean hasFact(String query){
        return facts.get(query)=="true";
    }

    private boolean hasRule(String query) {
        String databaseRule = rules.get(ruleManager.getName(query));
        if(databaseRule==null){
            return false;
        }
        String queryRule = replaceParameters(databaseRule, query);
        String ruleDefinition = queryRule.split(":-")[1];
        List<String> facts = factManager.getMultipleFacts(ruleDefinition);
        for(int i=0;i<facts.size();i++){
            if(!hasFact(facts.get(i))){
                return false;
            }
        }
        return true;
    }


}
