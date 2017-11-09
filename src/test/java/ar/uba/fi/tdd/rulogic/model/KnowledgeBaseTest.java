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

		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));
	}

	@Test
	public void testCorrectFact2() {

		Assert.assertTrue(this.knowledgeBase.answer("mujer(maria)."));
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

		Assert.assertFalse(this.knowledgeBase.answer("varon(cecilia)."));
	}

	@Test
	public void testIncorrectFact2() {

		Assert.assertFalse(this.knowledgeBase.answer("piloto(kimi)."));
	}


	@Test(expected = RuntimeException.class)
	public void testQueryInvalidaTiraError() {
		this.knowledgeBase.answer("esposa");
	}

	@Test(expected = RuntimeException.class)
	public void testQueryInvalidaTiraError2() {
		this.knowledgeBase.answer("varon(juan()).");
	}

	@Test(expected = RuntimeException.class)
	public void testQueryInvalidaTiraError3() {
		this.knowledgeBase.answer("varon(juan)");
	}

	@Test(expected = RuntimeException.class)
	public void testDiferenteCantidadDeParametrosEnRuleTiraError() {
		this.knowledgeBase.answer("hijo(pepe, juan, ramiro).");
	}

	@Test
	public void testCorrectRule1(){
		Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe, juan)."));
	}

	@Test
	public void testCorrectRule2(){
		Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas, cecilia, roberto)."));
	}

	@Test
	public void testIncorrectRule2(){
		Assert.assertFalse(this.knowledgeBase.answer("tio(julian, francisco, alfonso)."));
	}

	@Test
	public void testIncorrectRule1(){
		Assert.assertFalse(this.knowledgeBase.answer("hijo(julian, francisco)."));
	}

}
