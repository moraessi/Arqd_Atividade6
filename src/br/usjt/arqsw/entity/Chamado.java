package br.usjt.arqsw.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author Gabriela Vieira - 81610253 - SIN3AN-MCA1
 */

@Entity
public class Chamado {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_chamado")
	private int id;
	
	@NotNull
	@Column(name="dt_abertura")
	private Date dt_abertura;
	
	@Column(name="dt_fechamento")
	private Date dt_fechamento;
	
	@NotNull
	private String status;
	
	@NotNull 
	@Size(max=100,min=10, message="O tamanho da descrição deve estar entre 10 e 100 caracteres")
	private String descricao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_fila")
	private Fila fila;
	
	
	public static final String ABERTO = "aberto";
	public static final String FECHADO = "fechado";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDt_abertura() {
		return dt_abertura;
	}
	public void setDt_abertura(Date dt_abertura) {
		this.dt_abertura = dt_abertura;
	}
	public Date getDt_fechamento() {
		return dt_fechamento;
	}
	public void setDt_fechamento(Date dt_fechamento) {
		this.dt_fechamento = dt_fechamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Fila getFila() {
		return fila;
	}
	public void setFila(Fila fila) {
		this.fila = fila;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	//public int getTempoDias(){
		//getTime e currentTimeMillis retornam o tempo em milisegundos
		//dividir por 1000 * 60 * 60 * 24 converte para dias
		//int dias;
		//if(dataFechamento == null){
			//considera o momento atual para calcular o tempo aberto
			//dias =  (int)(System.currentTimeMillis() - dataAbertura.getTime())/(1000 * 60 * 60 * 24);
		//} else {
			//considera a data de fechamento para calcular o tempo aberto
			//dias = (int)(dataFechamento.getTime() - dataAbertura.getTime())/(1000 * 60 * 60 * 24);
		//}
		//return dias;
	//}
	
	@Override
	public String toString() {
		return "Chamado [id=" + id + ", dt_abertura=" + dt_abertura
				+ ", dt_fechamento=" + dt_fechamento + ", status=" + status
				+ ", descricao=" + descricao + ", fila=" + fila + "]";
	}


}
