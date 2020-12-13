package br.com.casadocodigo.loja.domain.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class Carrinho implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new HashMap<CarrinhoItem, Integer>();
	
	public void add(CarrinhoItem item) {
		this.itens.put(item, this.addQuantidade(item) + 1);
	}
	
	public int getQuantidade(){
	    return itens.values()
	    		.stream()
	    		.reduce(0, (proximo, acumulador) -> (proximo + acumulador));
	}
	
	public int addQuantidade(CarrinhoItem item) {
		if (!this.itens.containsKey(item)) {
			this.itens.put(item, 0);
		}
		
		return this.itens.get(item);
	}
	
	public Collection<CarrinhoItem> getItens() {
	    return this.itens.keySet();
	}
	
	public BigDecimal getTotal(CarrinhoItem item){
	    return item.getTotal(this.addQuantidade(item));
	}
	
	public BigDecimal getTotal(){
	    BigDecimal total = BigDecimal.ZERO;
	    for (CarrinhoItem item : itens.keySet()) {
	        total = total.add(getTotal(item));
	    }
	    return total;
	}

	public void remover(Integer produtoId, TipoPreco tipoPreco) {
		Produto produto = new Produto();
	    produto.setId(produtoId);
	    itens.remove(new CarrinhoItem(tipoPreco, produto));
	}
}
