package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author Gabriela Vieira - 81610253 - SIN3AN-MCA1
 */


@Service
public class FilaService {
	private FilaDAO dao;
	
	@Autowired
	public FilaService(FilaDAO dao) {
		this.dao = dao;
	}
	
	@Transactional
	public List<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	
	@Transactional
	public Fila carregar(int id) throws IOException{
		return dao.selecionar(id);
	}
	
	@Transactional
	public void criar(Fila fila) throws IOException{
		dao.criarFila(fila);
	}
}
