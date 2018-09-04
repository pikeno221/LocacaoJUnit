package com.gabriel.servicos;

import static com.gabriel.utils.DataUtils.adicionarDias;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.gabriel.model.Filme;
import com.gabriel.model.Locacao;
import com.gabriel.model.Usuario;
import com.gabriel.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws Exception {
		Double totalPrecoLocacao = 0.0;
		Locacao locacao = new Locacao();
		if (usuario == null) {
			throw new Exception("Usuário Vazio");
		}
		if (filmes == null || filmes.isEmpty()) {
			throw new Exception("Usuário Vazio");
		}

		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new RuntimeException("Filme não disponível no estoque");
			}
			totalPrecoLocacao += filme.getPrecoLocacao();
		}
		locacao.setFilmes(filmes);
		locacao.setValor(totalPrecoLocacao);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		if (DataUtils.verificarDiaSemana(adicionarDias(dataEntrega, 1), 1)) {
			dataEntrega = adicionarDias(dataEntrega, 2);
		}

		dataEntrega =

				adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...

		return locacao;
	}

	public List<Filme> adicionaFilmesLocacao() {
		Filme filme1 = new Filme(1, "Filme 1", 2, 7.0);
		Filme filme2 = new Filme(2, "Filme 2", 2, 7.5);

		List<Filme> filmes = Arrays.asList(filme1, filme2);
		return filmes;
	}

	public void aplicaDesconto(Locacao locacao) {
		switch (locacao.getFilmes().size()) {
		case 3:
			locacao.setValor(locacao.getValor()
					- (locacao.getFilmes().get(2).getPrecoLocacao() * locacao.getFilmes().get(2).getDesconto(3)));
			break;
		case 4:
			locacao.setValor(locacao.getValor()
					- (locacao.getFilmes().get(3).getPrecoLocacao() * locacao.getFilmes().get(3).getDesconto(4)));
			break;
		case 5:
			locacao.setValor(locacao.getValor()
					- (locacao.getFilmes().get(4).getPrecoLocacao() * locacao.getFilmes().get(4).getDesconto(5)));
			break;
		}

		if (locacao.getFilmes().size() >= 6) {
			locacao.setValor(locacao.getValor()
					- (locacao.getFilmes().get(5).getPrecoLocacao() * locacao.getFilmes().get(5).getDesconto(6)));
		}

	}

}