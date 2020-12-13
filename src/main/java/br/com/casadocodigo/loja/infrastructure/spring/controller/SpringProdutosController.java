package br.com.casadocodigo.loja.infrastructure.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.application.controller.ProdutoController;
import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.domain.entities.TipoPreco;
import br.com.casadocodigo.loja.infrastructure.spring.infra.FileSaver;
import br.com.casadocodigo.loja.infrastructure.spring.validator.ProdutoValidation;
import br.com.casadocodigo.loja.useCases.DetalharProduto;

@Controller
@RequestMapping("/produtos")
public class SpringProdutosController {

	@Autowired
	private ProdutoController produtoController;
	
	@Autowired
    private FileSaver fileSaver;
	
	@Autowired
	private DetalharProduto detalharProduto;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}

	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView mv = new ModelAndView("produtos/form");
		mv.addObject("tipos", TipoPreco.values());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	@CacheEvict(value="produtosHome", allEntries=true)
	public ModelAndView gravar(MultipartFile sumario, @Valid Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return form(produto);
		}
		
		String path = fileSaver.write("arquivos-sumario", sumario);
	    produto.setSumarioPath(path);
		
		this.produtoController.criarProduto(produto);
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
		return new ModelAndView("redirect:produtos");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("produtos/lista");
		mv.addObject("produtos", this.produtoController.listar());
		return mv;
	}
	
	@RequestMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id){
	    ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
	    Produto produto = detalharProduto.detalhar(id);
	    modelAndView.addObject("produto", produto);
	    return modelAndView;
	}
}
