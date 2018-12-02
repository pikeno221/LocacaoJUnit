package br.ce.wcaquino.servicos;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

public class CalculadoraMockTest {

	@Test
	public void terste() {
		Calculadora calc = Mockito.mock(Calculadora.class);

		Mockito.when(calc.somar(Mockito.anyInt(), Mockito.anyInt())).thenReturn(5);
		
		Mockito.when(calc.somar(Mockito.eq(1), Mockito.anyInt())).thenReturn(6);


		assertEquals(5, calc.somar(22, 22));
		
		assertEquals(6, calc.somar(1, 22));
	}
}
