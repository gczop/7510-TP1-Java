package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guillermo on 08/11/17.
 */
public class FactsAndRulesTests {
    @InjectMocks
    private FactManager factM = new FactManager("\\w+\\(\\w+(, \\w+)*\\).");
    private RuleManager ruleM= new RuleManager("\\w+\\(\\w+(, \\w+)*\\) :- (\\w+\\(\\w+(, \\w+)*\\), )*\\w+\\(\\w+(, \\w+)*\\).");

    @Test
    public void testValidFact1() {
        Assert.assertTrue(factM.isFact("varon(juan)."));
    }

    @Test
    public void testValidFact2() {
        Assert.assertTrue(factM.isFact("padre(juan, florencia)."));
    }

    @Test
    public void testInValidFact() {
        Assert.assertFalse(factM.isFact("padre(juan, flo."));
    }

    @Test
    public void testValidRule1(){
        Assert.assertTrue(ruleM.isRule("hijo(X, Y) :- varon(X), padre(Y, X)."));
    }

    @Test
    public void testValidRule2(){
        Assert.assertTrue(ruleM.isRule("tio(X, Y, Z) :- varon(X), hermano(X, Z), padre(Z, Y)."));
    }

    @Test
    public void testRuleManagerGetsCorrectRuleName(){
       Assert.assertTrue(ruleM.getName("hijo(X, Y) :- varon(X), padre(Y, X).").equals("hijo"));
    }

    @Test
    public void testFactManagerGetsCorrectMultipleFacts(){
        List<String> a = new ArrayList<>();
        a.add("piloto(fernando)");
        a.add("equipo(renault,fernando)");
        Assert.assertTrue(a.equals(factM.getMultipleFacts("piloto(fernando),equipo(renault,fernando)")));
    }




}