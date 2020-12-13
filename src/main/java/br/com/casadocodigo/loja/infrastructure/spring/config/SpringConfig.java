package br.com.casadocodigo.loja.infrastructure.spring.config;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.casadocodigo.loja.infrastructure.hibernate.repositories.ProdutoRepository;
import br.com.casadocodigo.loja.useCases.CriarCarrinhoItem;
import br.com.casadocodigo.loja.useCases.CriarProduto;
import br.com.casadocodigo.loja.useCases.DetalharProduto;
import br.com.casadocodigo.loja.useCases.ListarProduto;

public class SpringConfig {

	@Autowired
	private ProdutoRepository produtoRepository = new ProdutoRepository();
	
	public ListarProduto listarProduto() {
		return new ListarProduto(this.produtoRepository);
	}
	
	public CriarProduto criarProduto() {
		return new CriarProduto(this.produtoRepository);
	}
	
	public DetalharProduto detalharProduto() {
		return new DetalharProduto(this.produtoRepository);
	}
	
	public CriarCarrinhoItem criarCarrinhoItem() {
		return new CriarCarrinhoItem(this.produtoRepository);
	}
}
