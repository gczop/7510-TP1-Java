package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guillermo on 06/11/17.
 */
public class RuleManager {

    String regex;

    public RuleManager(String RuleRegex){
        regex = RuleRegex;
    }

    public boolean isRule(String query){
        return query.matches(regex);
    }

    public String getName(String rule){
        String[] parts = rule.split("\\(");
        return parts[0];
    }


}
