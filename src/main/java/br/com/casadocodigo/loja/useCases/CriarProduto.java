package br.com.casadocodigo.loja.useCases;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.domain.repositories.IProdutoRepository;

public class CriarProduto {
	
	@Autowired
	private IProdutoRepository produtoRepository;

	public CriarProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public void gravar(Produto produto) {
		this.produtoRepository.gravar(produto);
	}
}
