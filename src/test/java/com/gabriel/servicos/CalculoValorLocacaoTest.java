package com.gabriel.servicos;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.gabriel.model.Filme;
import com.gabriel.model.Locacao;
import com.gabriel.model.Usuario;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {
	private LocacaoService service;

	@Parameter
	public List<Filme> filmes;

	@Parameter(value = 1)
	public Double valorLocacao;

	@Parameter(value = 2)
	public String cenario;

	@Before
	public void setup() {
		service = new LocacaoService();
		valorOriginalFilme = new ArrayList<>();
		for (Filme filme : filmes) {
			valorOriginalFilme.add(filme.getPrecoLocacao());
		}
	}
	private List<Double> valorOriginalFilme; 

	private static Filme filme0 = new Filme(0, "Filme 0", 1, 7.0);
	private static Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
	private static Filme filme2 = new Filme(2, "Filme 2", 2, 7.0);
	private static Filme filme3 = new Filme(3, "Filme 3", 2, 7.0);
	private static Filme filme4 = new Filme(4, "Filme 4", 2, 7.0);
	private static Filme filme5 = new Filme(5, "Filme 5", 2, 7.0);
	private static Filme filme6 = new Filme(6, "Filme 6", 2, 7.0);

	@Parameters(name = "{2}")
	public static Collection<Object[]> getParametros() {
		return Arrays.asList(new Object[][] { { Arrays.asList(filme0, filme1, filme2), 19.25, "3 filmes: 25%" },
				{ Arrays.asList(filme0, filme1, filme2, filme3), 24.50, "4 filmes: 50%" },
				{ Arrays.asList(filme0, filme1, filme2, filme3, filme4), 29.75, "5 filmes: 75%" },
				{ Arrays.asList(filme0, filme1, filme2, filme3, filme4, filme5), 35.00, "6 filmes: 100%" },
				{ Arrays.asList(filme0, filme1, filme2, filme3, filme4, filme5, filme6), 42.00, "7 filmes: 100%" }, });
	}

	@Test
	public void testaLocacao_ValorEmprestimo() throws Exception {
		Usuario usuario = new Usuario(1, "Usuario 1");
		Locacao locacao = service.alugarFilme(usuario, filmes);
		service.aplicaDesconto(locacao);
		// assertEquals(24.50, valorTotal, 0.01);
		assertThat(locacao.getValor(), is(valorLocacao));
	}

}
