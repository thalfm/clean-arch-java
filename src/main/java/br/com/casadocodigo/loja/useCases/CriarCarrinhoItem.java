package br.com.casadocodigo.loja.useCases;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casadocodigo.loja.domain.entities.CarrinhoItem;
import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.domain.entities.TipoPreco;
import br.com.casadocodigo.loja.domain.repositories.IProdutoRepository;

public class CriarCarrinhoItem {
	
	@Autowired
	private IProdutoRepository produtoRepository;
	
	public CriarCarrinhoItem(IProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public CarrinhoItem criar(Integer produtoId, TipoPreco tipo) {
		Produto produto = produtoRepository.find(produtoId);
		CarrinhoItem carrinhoItem = new CarrinhoItem(tipo, produto);
		
		return carrinhoItem;
	}
}
