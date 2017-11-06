package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by guillermo on 06/11/17.
 */
public class FactManager {

    String regex;

    public FactManager(String regexForFacts) {
        regex = regexForFacts;
    }

    public boolean isFact(String query){
        return Pattern.matches(regex, query);
    }

    public List<String> getMultipleFacts(String definition){
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(definition);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

}