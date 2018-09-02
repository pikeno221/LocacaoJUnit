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
	public ErrorCollector error = new ErrorCollector(); // Rastrear todo código sem parar no primeiro

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testeLocacao() throws Exception { // no Console da Error ao invés de falha lançando pra cima a exceção
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
		 * e.printStackTrace(); Assert.fail("Não deveria lançar Exceção"); }
		 */
	}

	@Test(expected = RuntimeException.class) // Preparando método para receber uma excessão
	public void testeLocacao_FilmeSemEstoque() throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 0, 7.0);

		service.alugarFilme(usuario, filme);

	}

	@Test
	public void testeLocacao_FilmeSemEstoque_2() { // Consegue Verificar a mensagem de Excessão que chegou e da um
													// Matcher (is)
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 0, 7.0);

		try {
			service.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter Lançado uma Exceção");
		} catch (Exception e) {
			e.printStackTrace();
			assertThat(e.getMessage(), is("Filme não disponível no estoque"));
		}
	}

	@Test
	public void testeLocacao_FilmeSemEstoque_3() throws Exception {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario(1, "Usuario 1");
		Filme filme = new Filme(1, "Filme 1", 0, 7.0);


		exception.expect(RuntimeException.class);
		exception.expectMessage("Filme não disponível no estoque");
		
		service.alugarFilme(usuario, filme);

	}
}
