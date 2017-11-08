package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by guillermo on 06/11/17.
 */
public class InvalidBaseTest {
    @InjectMocks
    private KnowledgeBase knowledgeBase;

    @Test(expected = RuntimeException.class)
    public void testInvalidDatabase() {
        //initMocks(this);
        knowledgeBase = new KnowledgeBase();
        knowledgeBase.useDatabase("src/main/resources/invalidDB.db");
    }
}
