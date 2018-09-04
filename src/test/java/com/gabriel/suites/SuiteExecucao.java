package com.gabriel.suites;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
 // especificar as classes que você quer que faça parte do seu Suite
})
public class SuiteExecucao {
	
	@Before
	public static void before() {
		
	}
	
	@After
	public static void tearDown() {
		
	}

}
