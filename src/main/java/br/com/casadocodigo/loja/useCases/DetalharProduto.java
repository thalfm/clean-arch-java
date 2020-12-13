package br.com.casadocodigo.loja.useCases;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.domain.repositories.IProdutoRepository;

public class DetalharProduto {
	@Autowired
	private IProdutoRepository produtoRepository;

	public DetalharProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public Produto detalhar(int id) {
		return this.produtoRepository.find(id);
	}
}
