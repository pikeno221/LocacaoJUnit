package com.gabriel.model;

public class Filme {

	private int id;
	private String nome;
	private Integer estoque;
	private Double precoLocacao;

	public Filme() {
	}

	public Filme(int id, String nome, Integer estoque, Double precoLocacao) {
		this.id = id;
		this.nome = nome;
		this.estoque = estoque;
		this.precoLocacao = precoLocacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getPrecoLocacao() {
		return precoLocacao;
	}

	public void setPrecoLocacao(Double precoLocacao) {
		this.precoLocacao = precoLocacao;
	}

	public double getDesconto(int qnt) {
		switch (qnt) {
		case 3:
			return (25.0/100.0);
		case 4:
			return (50.0/100.0);
		case 5:
			return (75.0/100.0);
		case 6:
			return (100/100);
		default:
			return 0.0;

		}
	}
}