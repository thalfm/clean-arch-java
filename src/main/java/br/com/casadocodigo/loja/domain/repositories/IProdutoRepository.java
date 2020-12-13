package br.com.casadocodigo.loja.domain.repositories;

import java.util.List;

import br.com.casadocodigo.loja.domain.entities.Produto;

public interface IProdutoRepository {
	public void gravar(Produto produto);
	public List<Produto> listar();
	public Produto find(int id);
}
