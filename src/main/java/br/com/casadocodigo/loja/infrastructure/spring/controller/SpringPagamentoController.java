package br.com.casadocodigo.loja.infrastructure.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.domain.entities.Carrinho;

@Controller
@RequestMapping("/pagamento")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class SpringPagamentoController {
	@Autowired
	Carrinho carrinho;
	
	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public ModelAndView finalizar(RedirectAttributes model) {
		System.out.println(carrinho.getTotal());
		model.addFlashAttribute("sucesso", "Pagamento Realizado com Sucesso");
		return new ModelAndView("redirect:/produtos");
	}
}
