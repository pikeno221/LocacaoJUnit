package com.gabriel.servicos;

import static com.gabriel.utils.DataUtils.adicionarDias;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gabriel.model.Filme;
import com.gabriel.model.Locacao;
import com.gabriel.model.Usuario;

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
		locacao.setValor(totalPrecoLocacao);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
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

	public List<Filme> aplicaDesconto(List<Filme> filmes) {
		Filme filmeAux;
		int qnt = filmes.size();
		switch (qnt) {
		case 3:
			filmeAux = filmes.get(2);
			filmeAux.setPrecoLocacao(
					(filmeAux.getPrecoLocacao() - (filmeAux.getPrecoLocacao() * filmeAux.getDesconto(3))));
			break;
		case 4:
			filmeAux = filmes.get(3);
			filmeAux.setPrecoLocacao(
					(filmeAux.getPrecoLocacao() - (filmeAux.getPrecoLocacao() * filmeAux.getDesconto(4))));
			break;
		case 5:
			filmeAux = filmes.get(4);
			filmeAux.setPrecoLocacao(
					(filmeAux.getPrecoLocacao() - (filmeAux.getPrecoLocacao() * filmeAux.getDesconto(5))));
			break;
		}

		if (filmes.size() >= 6) {
			Filme filmeAux2 = filmes.get(filmes.size() -1);
			filmeAux2.setPrecoLocacao(filmeAux2.getPrecoLocacao() - ((filmeAux2.getPrecoLocacao() * filmeAux2.getDesconto(6))));
		}

		return filmes;
	}
}