package br.com.casadocodigo.loja.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.domain.repositories.IProdutoRepository;

public class ListarProduto {

	@Autowired
	private IProdutoRepository produtoRepository;
	
	public ListarProduto(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	public List<Produto> listar() {
		return produtoRepository.listar();
	}
}
