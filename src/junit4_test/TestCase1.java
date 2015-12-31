package junit4_test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import spms.servlets.EmailValidator;

public class TestCase1 {

	
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception{
		
	}
	
	@Test
	public void testSomething() throws Exception{
		EmailValidator ev = new EmailValidator();
		assertTrue(ev.validate("seongwons@gmail.com"));
		assertTrue(ev.validate("heell1@qwe.qwe"));
		assertTrue(ev.validate("hh12123@hooo.aad"));
		assertTrue(ev.validate("hhc___@hooo.acb"));
		assertTrue(ev.validate("seong.won.kong@naver.com"));

	}
}
