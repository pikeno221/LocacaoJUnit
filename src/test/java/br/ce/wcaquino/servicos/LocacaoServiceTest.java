package br.ce.wcaquino.servicos;

import static com.gabriel.utils.DataUtils.isMesmaData;
import static com.gabriel.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

import com.gabriel.entidades.Filme;
import com.gabriel.entidades.Locacao;
import com.gabriel.entidades.Usuario;
import com.gabriel.servicos.LocacaoService;

public class LocacaoServiceTest {

	@Test
	public void teste() {
		// cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 2, 5.0);

		// acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// verificacao
		assertThat(locacao.getValor(), is(equalTo(5.0)));
		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
}
