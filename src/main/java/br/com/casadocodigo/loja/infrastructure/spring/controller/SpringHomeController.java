package br.com.casadocodigo.loja.infrastructure.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.useCases.ListarProduto;

@Controller
public class SpringHomeController {

	@Autowired
	private ListarProduto listarProduto;
	
	@RequestMapping("/")
	@Cacheable(value = "produtosHome")
	public ModelAndView index() {
		List<Produto> produtos = this.listarProduto.listar();
		return new ModelAndView("home")
				.addObject("produtos", produtos);
	}
}
