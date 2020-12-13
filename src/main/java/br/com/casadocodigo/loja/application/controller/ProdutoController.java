package br.com.casadocodigo.loja.application.controller;

import java.util.List;

import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.useCases.CriarProduto;
import br.com.casadocodigo.loja.useCases.ListarProduto;

public class ProdutoController {
	private ListarProduto listarProduto;
	private CriarProduto criarProduto;

	public ProdutoController(ListarProduto listarProduto, CriarProduto criarProduto) {
		this.listarProduto = listarProduto;
		this.criarProduto = criarProduto;
	}
	
	public List<Produto> listar() {
		return this.listarProduto.listar();
	}
	
	public void criarProduto(Produto produto) {
		this.criarProduto.gravar(produto);
	}
}
