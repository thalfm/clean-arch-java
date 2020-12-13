package br.com.casadocodigo.loja.infrastructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.casadocodigo.loja.application.controller.ProdutoController;
import br.com.casadocodigo.loja.domain.repositories.IProdutoRepository;
import br.com.casadocodigo.loja.infrastructure.hibernate.repositories.ProdutoRepository;
import br.com.casadocodigo.loja.useCases.CriarCarrinhoItem;
import br.com.casadocodigo.loja.useCases.CriarProduto;
import br.com.casadocodigo.loja.useCases.DetalharProduto;
import br.com.casadocodigo.loja.useCases.ListarProduto;

@Configuration
public class Config {

	private SpringConfig springConfig = new SpringConfig();
	
	@Bean
	public ListarProduto listarProduto() {
		return springConfig.listarProduto();
	}
	
	@Bean
	public CriarProduto criarProduto() {
		return springConfig.criarProduto();
	}
	
	@Bean
	public ProdutoController produtoController() {
		return new ProdutoController(this.listarProduto(), this.criarProduto());
	}
	
	@Bean
	public DetalharProduto detalharProduto() {
		return springConfig.detalharProduto();
	}
	
	@Bean
	public CriarCarrinhoItem criarCarrinhoItem() {
		return springConfig.criarCarrinhoItem();
	}
	
	@Bean
	public IProdutoRepository produtoRepository() {
		return new ProdutoRepository();
	}
}
