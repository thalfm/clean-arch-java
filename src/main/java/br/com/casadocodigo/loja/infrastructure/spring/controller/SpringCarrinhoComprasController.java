package br.com.casadocodigo.loja.infrastructure.spring.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.domain.entities.Carrinho;
import br.com.casadocodigo.loja.domain.entities.CarrinhoItem;
import br.com.casadocodigo.loja.domain.entities.TipoPreco;
import br.com.casadocodigo.loja.useCases.CriarCarrinhoItem;

@Controller
@RequestMapping("/carrinho")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class SpringCarrinhoComprasController {

	@Autowired
	CriarCarrinhoItem criarCarrinhoItem;
	@Autowired
	Carrinho carrinho;
	
	@RequestMapping("/add")
	public Callable<ModelAndView> add(Integer produtoId, TipoPreco tipoPreco) {
		return () -> {
			ModelAndView view = new ModelAndView("redirect:/carrinho");
			
			CarrinhoItem carrinhoItem = this.criarCarrinhoItem.criar(produtoId, tipoPreco);
			this.carrinho.add(carrinhoItem);
			return view;
			
		};
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView itens(){
	    return new ModelAndView("/carrinho/itens");
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Integer produtoId, TipoPreco tipoPreco){
	    this.carrinho.remover(produtoId, tipoPreco);
	    return new ModelAndView("redirect:/carrinho");
	}
}
