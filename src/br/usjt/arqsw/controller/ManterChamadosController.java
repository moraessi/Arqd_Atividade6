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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;


/**
 * 
 * @author Gabriela Vieira - 81610253 - SIN3AN-MCA1
 */



@Transactional
@Controller
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;
	

	
	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	
	@RequestMapping("/tela_inicio")
	public String inicio2() {
		return "TelaPrincipal";
	}
	
	
	private List<Fila> listarFilas() throws IOException{
			return filaService.listarFilas();
	}
	
	
	private List<Chamado> listarChamados(Fila fila) throws IOException{
		return chamadoService.listarChamados(fila);
	}
	
	
	private void cadastrarChamado(String desc, Fila fila) throws IOException{
		Chamado chamado = new Chamado();
		Date d = new Date();
		chamado.setDescricao(desc);
		chamado.setFila(fila);
		chamado.setStatus("ABERTO");
		chamado.setDt_abertura(d);
		chamadoService.cadastrarChamado(chamado);
	}
	
	
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/listar_todas_filas")
	public String listarTodasFilas(Model model) {
		try {
			List<Fila> filas = filaService.listarFilas();
			model.addAttribute("filas", filas);
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	
	@RequestMapping("/listar_filas_fechar")
	public String listarFilasFechar(Model model) {
		try {
			List<Fila> filas = filaService.listarFilas();
			model.addAttribute("filas", filas);
			return "ChamadoFechar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/cadastrar_chamado")
	public String cadastrarChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "CadastrarChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/cadastrar_fila")
	public String cadastrarChamado(@Valid Fila fila, BindingResult result, Model model ) throws IOException{
		fila.setNome("nome");
		filaService.criar(fila);
		return "CadastrarFila";
	}
	

	@RequestMapping("/chamado_cadastrado")
	public String chamadoCadastrado(String desc, Fila fila) {
		try {
			cadastrarChamado(desc, fila);
			return "ChamadoCadastrado";		
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	


	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamados", listarChamados(fila));
			
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	/**
	@RequestMapping("/listar_chamados_fechados")
	public String listarChamadosFechados(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListarF";
				//return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamados", listarChamados(fila));
			
			return "ChamadoFechado";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	
	@RequestMapping("/fechar_chamados")
	public String fecharChamados( 
			@RequestParam Map<String, String> allRequestParams) {
		try {
			Set<String> chaves = allRequestParams.keySet();
			Iterator<String> i = chaves.iterator();
			ArrayList<Integer> lista = new ArrayList<>();
			while(i.hasNext()) {
				String chave = i.next();
				String valor = (String) allRequestParams.get(chave);
				if(valor.equals("on")) {
					lista.add(Integer.parseInt(chave));	
				}
			}
			if(lista.size()>0) {
				chamadoService.fecharChamados(lista);
		}
		return "ChamadoFechado";
		
	} catch (IOException e) {
		e.printStackTrace();
		return "Erro";
		}
	}
}*/
	
	@RequestMapping("/listar_chamados_abertos")
	public String listarChamadosAbertos(Fila fila, Model model) {
		try {
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);

			List<Chamado> chamados = chamadoService.listarChamadosAbertos(fila);
			model.addAttribute("chamados", chamados);
			return "ChamadoFecharSelecionar";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/fechar_chamados")
	public String fecharChamados(
			@RequestParam Map<String, String> allRequestParams) {
		try {
			Set<String> chaves = allRequestParams.keySet();
			Iterator<String> i = chaves.iterator();
			ArrayList<Integer> lista = new ArrayList<>();
			while (i.hasNext()) {
				String chave = i.next();
				String valor = allRequestParams.get(chave);
				if (valor.equals("on")) {
					lista.add(Integer.parseInt(chave));
				}
			}
			if (lista.size() > 0) {
				chamadoService.fecharChamados(lista);
			}
			return "ChamadoFechado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}




