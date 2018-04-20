package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @authorGabriela Vieira - 81610253 - SIN3AN-MCA1
 *
 */
@Service
public class ChamadoService {
	private ChamadoDAO dao;
	
	@Autowired
	public ChamadoService(ChamadoDAO dao){
		this.dao = dao;
	}
	
	@Transactional
	public List<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}
	
	@Transactional
	public List <Chamado> listarChamados()throws IOException{
		return dao.listarChamados();
	}
	
	@Transactional
	public int novoChamado(Chamado chamado) throws IOException{
		chamado.setDt_abertura(new Date());
		chamado.setDt_fechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		return dao.novoChamado(chamado);
	}
	
	@Transactional
	public void cadastrarChamado(Chamado chamado) throws IOException{
		chamado.setDt_abertura(new Date());
		chamado.setDt_fechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		System.out.println(chamado.getDescricao());
		dao.cadastrarChamado(chamado);
	}
	
	public void fecharChamados(ArrayList<Integer> lista) throws IOException{
		dao.fecharChamados(lista);
	}
	
	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		return dao.listarChamadosAbertos(fila);
	}
	
	}



