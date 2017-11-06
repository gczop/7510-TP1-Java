package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		knowledgeBase = new KnowledgeBase();
		knowledgeBase.useDatabase("src/main/resources/rules.db");
	}

	@Test
	public void testCorrectFact1() {

		Assert.assertTrue(this.knowledgeBase.answer("varon (javier)."));
	}

	@Test
	public void testCorrectFact2() {

		Assert.assertTrue(this.knowledgeBase.answer("mujer (maria)."));
	}
	@Test
	public void testCorrectFact3() {

		Assert.assertTrue(this.knowledgeBase.answer("padre(roberto, cecilia)."));
	}

	@Test
	public void testCorrectFact4() {

		Assert.assertTrue(this.knowledgeBase.answer("hermano(roberto, nicolas)."));
	}

	@Test
	public void testIncorrectFact1() {

		Assert.assertFalse(this.knowledgeBase.answer("varon(cecilia)"));
	}


	@Test
	public void testReglaNoExiste() throws Exception {

		Assert.assertFalse(this.knowledgeBase.answer("esposa(cecilia, alejandro)."));
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidQuery() {

		Assert.assertFalse(this.knowledgeBase.answer("esposa"));
	}

}
