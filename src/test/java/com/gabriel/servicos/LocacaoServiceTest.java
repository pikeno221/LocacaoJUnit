package com.gabriel.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.gabriel.model.Filme;
import com.gabriel.model.Locacao;
import com.gabriel.model.Usuario;
import com.gabriel.utils.DataUtils;

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
		contator++;
	}

	@After
	public void tearDown() {
	}

	@BeforeClass
	public static void setupCLass() {
	}

	@AfterClass
	public static void tearDownClass() {
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

	@Test // (expected = RuntimeException.class) // Preparando método para receber uma
			// excessão
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
		// exception.expect(RuntimeException.class);
		// exception.expectMessage("Filme não disponível no estoque");

		service.alugarFilme(usuario, service.adicionaFilmesLocacao());
	}

	@Test
	public void testaLocacao_ValorEmprestimo() throws Exception {

		Usuario usuario = new Usuario(1, "Usuario 1");

		Locacao locacao = service.alugarFilme(usuario, service.adicionaFilmesLocacao());
		assertEquals(14.5, locacao.getValor(), 0.01);
		// assertThat(, 0.0, 0.01);
	}

	@Test
	public void testaDesconto3Filmes() throws Exception {
		Double valorTotal = 0.0;
		Filme filme0 = new Filme(0, "Filme 0", 1, 7.0);
		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.5);

		List<Filme> filmes = Arrays.asList(filme0, filme1, filme2);
		service.aplicaDesconto(filmes);

		for (Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
		}
		assertEquals(19.62, valorTotal, 0.01);
	}

	@Test
	public void testaDesconto4Filmes() throws Exception {
		Double valorTotal = 0.0;
		Filme filme0 = new Filme(0, "Filme 0", 1, 7.0);
		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.0);
		Filme filme3 = new Filme(3, "Filme 3", 2, 7.0);

		List<Filme> filmes = Arrays.asList(filme0, filme1, filme2, filme3);
		service.aplicaDesconto(filmes);

		for (Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
		}
		assertEquals(24.50, valorTotal, 0.01);
	}

	@Test
	public void testaDesconto5Filmes() throws Exception {
		Double valorTotal = 0.0;
		Filme filme0 = new Filme(0, "Filme 0", 1, 7.0);
		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.0);
		Filme filme3 = new Filme(3, "Filme 3", 2, 7.0);
		Filme filme4 = new Filme(4, "Filme 4", 2, 7.0);

		List<Filme> filmes = Arrays.asList(filme0, filme1, filme2, filme3, filme4);
		service.aplicaDesconto(filmes);

		for (Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
		}
		assertEquals(29.75, valorTotal, 0.01);
	}

	@Test
	public void testaDesconto6Filmes() throws Exception {
		Double valorTotal = 0.0;
		Filme filme0 = new Filme(0, "Filme 0", 1, 7.0);
		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.0);
		Filme filme3 = new Filme(3, "Filme 3", 2, 7.0);
		Filme filme4 = new Filme(4, "Filme 4", 2, 7.0);
		Filme filme5 = new Filme(5, "Filme 5", 2, 7.0);

		List<Filme> filmes = Arrays.asList(filme0, filme1, filme2, filme3, filme4, filme5);
		service.aplicaDesconto(filmes);

		for (Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
		}
		assertEquals(35.00, valorTotal, 0.01);
	}

	@Test
	public void testaDesconto7Filmes() throws Exception {
		Double valorTotal = 0.0;
		Filme filme0 = new Filme(0, "Filme 0", 1, 7.0);
		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.0);
		Filme filme3 = new Filme(3, "Filme 3", 2, 7.0);
		Filme filme4 = new Filme(4, "Filme 4", 2, 7.0);
		Filme filme5 = new Filme(5, "Filme 5", 2, 7.0);
		Filme filme6 = new Filme(6, "Filme 6", 2, 7.0);

		List<Filme> filmes = Arrays.asList(filme0, filme1, filme2, filme3, filme4, filme5, filme6);
		service.aplicaDesconto(filmes);

		for (Filme filme : filmes) {
			valorTotal += filme.getPrecoLocacao();
		}
		assertEquals(42.00, valorTotal, 0.01);
	}

	@Test
	//@Ignore
	public void naoDeveDevolverFilmeNoDomingo() throws Exception {	
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		Usuario user = new Usuario(1, "User1");
		List<Filme> filmes = Arrays.asList(new Filme(1, "Filme1", 1, 5.0));

		Locacao retorno = service.alugarFilme(user, filmes);

		boolean ehSegunda = DataUtils.verificarDiaSemana(retorno.getDataRetorno(), Calendar.MONDAY);
		Assert.assertTrue(ehSegunda);
	}
}
