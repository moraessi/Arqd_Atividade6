package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Gabriela Vieira - 81610253 - SIN3AN-MCA1
 */

@Repository
public class ChamadoDAO {
	@PersistenceContext
	EntityManager manager;
	
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(){
		return manager.createQuery("select c from Chamado c").getResultList();
	}

	
	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(Fila fila){
		return manager.createQuery("select c from Chamado c where id_fila ="+fila.getId()).getResultList();
	}
	
	
	public void atualizar(Chamado chamado) {
		manager.merge(chamado);
	}

	
	public void remover(Chamado chamado) {
		manager.remove(chamado);
	}

	
	public Chamado selecionar(int id) {
		return manager.find(Chamado.class, id);
	}

	
	public void cadastrarChamado(Chamado chamado){
		manager.persist(chamado);
	}
	
	
	public int novoChamado(Chamado chamado){
		manager.persist(chamado);
		return 1;
	}
	
	
	public void fecharChamados(ArrayList<Integer> lista) throws IOException{
		for (int id:lista) {
			Chamado chamado = manager.find(Chamado.class, id);
			chamado.setDt_fechamento(new java.util.Date());
			chamado.setStatus(Chamado.FECHADO);
			manager.merge(chamado);
		}
	}
	
	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		//conectei minha fila com a persistencia
		fila = manager.find(Fila.class, fila.getId());
		
		String jpql = "select c from Chamado c where c.fila = :fila and c.status = :status";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("fila", fila);
		query.setParameter("status", Chamado.ABERTO);

		List<Chamado> result = query.getResultList();
		return result;
	}
	

	
}
