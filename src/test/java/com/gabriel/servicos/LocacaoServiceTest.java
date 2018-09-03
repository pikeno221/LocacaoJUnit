package com.gabriel.servicos;

import static com.gabriel.utils.DataUtils.isMesmaData;
import static com.gabriel.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.gabriel.model.Filme;
import com.gabriel.model.Locacao;
import com.gabriel.model.Usuario;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector(); // Rastrear todo código sem parar no primeiro

	@Rule
	public ExpectedException exception = ExpectedException.none();

	private static int contator = 0;

	private LocacaoService service;

	@Before
	public void setup() {
		service = new LocacaoService();
		System.out.println("before");
		contator++;
		System.out.println(contator);
	}

	@After
	public void tearDown() {
		System.out.println("after");
	}

	@BeforeClass
	public static void setupCLass() {
		System.out.println("before Class");
	}

	@AfterClass
	public static void tearDownClass() {
		System.out.println("after Class");
	}

	@Test
	public void testeLocacao() throws Exception { // no Console da Error ao invés de falha lançando pra cima a exceção
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 2, 7.0);

		/*
		 * Locacao locacao = service.alugarFilme(usuario, filme);
		 * error.checkThat(locacao.getValor(), is(equalTo(7.0)));
		 * error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		 * error.checkThat(isMesmaData(locacao.getDataRetorno(),
		 * obterDataComDiferencaDias(1)), is(true));
		 * 
		 * 
		 * /* try { locacao = service.alugarFilme(usuario, filme); locacao =
		 * service.alugarFilme(usuario, filme); error.checkThat(locacao.getValor(),
		 * is(equalTo(7.0))); error.checkThat(isMesmaData(locacao.getDataLocacao(), new
		 * Date()), is(true)); error.checkThat(isMesmaData(locacao.getDataRetorno(),
		 * obterDataComDiferencaDias(1)), is(true)); } catch (Exception e) {
		 * e.printStackTrace(); Assert.fail("Não deveria lançar Exceção"); }
		 */
	}

	@Test//(expected = RuntimeException.class) // Preparando método para receber uma excessão
	public void testeLocacao_FilmeSemEstoque() throws Exception {
		Usuario usuario = new Usuario(1, "Usuario 1");

		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.5);

		ArrayList<Filme> filmes = new ArrayList<Filme>();
		filmes.addAll((Arrays.asList(filme1, filme2)));

		service.alugarFilme(usuario, service.adicionaFilmesLocacao());

	}

	@Test
	public void testeLocacao_FilmeSemEstoque_2() { // Consegue Verificar a mensagem de Excessão que chegou e da um
													// Matcher (is)
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 2, 7.0);

		try {
			service.alugarFilme(usuario, service.adicionaFilmesLocacao());
		} catch (Exception e) {
			// e.printStackTrace();
			assertThat(e.getMessage(), is("Filme não disponível no estoque"));
		}
	}

	@Test
	public void testeLocacao_FilmeSemEstoque_3() throws Exception {

		Usuario usuario = new Usuario(1, "Usuario 1");

		Filme filme = new Filme(1, "Filme 1", 0, 7.0);

//		exception.expect(RuntimeException.class);
//		exception.expectMessage("Filme não disponível no estoque");

		service.alugarFilme(usuario, service.adicionaFilmesLocacao());
	}
	
	@Test 
	public void testaLocacao_ValorEmprestimo() throws Exception {
		
		Usuario usuario = new Usuario(1, "Usuario 1");
		
		Locacao locacao = service.alugarFilme(usuario, service.adicionaFilmesLocacao());
		System.out.println(locacao.getValor());
		assertEquals(14.5, locacao.getValor(), 0.01);
		//assertThat(, 0.0, 0.01);
	}
}
