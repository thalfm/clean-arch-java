package br.com.casadocodigo.loja.infrastructure.hibernate.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.loja.domain.entities.Produto;
import br.com.casadocodigo.loja.domain.repositories.IProdutoRepository;

@Repository
@Transactional
public class ProdutoRepository implements IProdutoRepository{

	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Produto produto) {
		manager.persist(produto);
	}
	
	public List<Produto> listar(){		
	    return manager
	    		.createQuery("select p from Produto p", Produto.class)
	    		.getResultList();
	}

	@Override
	public Produto find(int id) {
		return manager.createQuery("select distinct(p) from Produto p " + 
		        "join fetch p.precos precos where p.id = :id", Produto.class)
		            .setParameter("id", id).getSingleResult();
	}
}
