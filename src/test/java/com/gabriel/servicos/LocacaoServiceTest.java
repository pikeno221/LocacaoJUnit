package com.gabriel.servicos;

import static com.gabriel.utils.DataUtils.isMesmaData;
import static com.gabriel.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.gabriel.model.Filme;
import com.gabriel.model.Locacao;
import com.gabriel.model.Usuario;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector(); // Rastrear todo c�digo sem parar no primeiro

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testeLocacao() throws Exception { // no Console da Error ao inv�s de falha lan�ando pra cima a exce��o
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 2, 7.0);

		Locacao locacao;
		locacao = service.alugarFilme(usuario, filme);
		error.checkThat(locacao.getValor(), is(equalTo(7.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));

		/*
		 * try { locacao = service.alugarFilme(usuario, filme); locacao =
		 * service.alugarFilme(usuario, filme); error.checkThat(locacao.getValor(),
		 * is(equalTo(7.0))); error.checkThat(isMesmaData(locacao.getDataLocacao(), new
		 * Date()), is(true)); error.checkThat(isMesmaData(locacao.getDataRetorno(),
		 * obterDataComDiferencaDias(1)), is(true)); } catch (Exception e) {
		 * e.printStackTrace(); Assert.fail("N�o deveria lan�ar Exce��o"); }
		 */
	}

	@Test(expected = RuntimeException.class) // Preparando m�todo para receber uma excess�o
	public void testeLocacao_FilmeSemEstoque() throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 0, 7.0);

		service.alugarFilme(usuario, filme);

	}

	@Test
	public void testeLocacao_FilmeSemEstoque_2() { // Consegue Verificar a mensagem de Excess�o que chegou e da um
													// Matcher (is)
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 0, 7.0);

		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter Lan�ado uma Exce��o");
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(e.getMessage(), is("Filme n�o dispon�vel no estoque"));
		}
	}

	@Test
	public void testeLocacao_FilmeSemEstoque_3() throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 0, 7.0);


		exception.expect(RuntimeException.class);
		exception.expectMessage("Filme n�o dispon�vel no estoque");
		
		service.alugarFilme(usuario, filme);

	}
}
