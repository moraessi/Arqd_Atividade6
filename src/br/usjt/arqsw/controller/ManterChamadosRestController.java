package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 *  @author Gabriela Vieira - 81610253 - SIN3AN-MCA1
 */

@RestController
public class ManterChamadosRestController {
	private ChamadoService cService;
	private FilaService fService;
	

	@Autowired
	public 	ManterChamadosRestController(ChamadoService cs, FilaService fs) {
		cService = cs;
		fService = fs;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="rest/chamados")
	public @ResponseBody List<Chamado> listarChamados(){
		
		try {
			return cService.listarChamados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping (method=RequestMethod.POST, value="rest/chamado")
	public ResponseEntity <Chamado> inserirChamado(@RequestBody Chamado chamado) {
		
		try{
			int id =cService.novoChamado(chamado);
			chamado.setId(id);
			return new ResponseEntity(chamado, HttpStatus.OK);
			} catch(IOException e){
			e.printStackTrace();
			return new ResponseEntity(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
	
	}

