package br.com.tmsfasdom.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Atividade database table.
 * 
 */
@Entity
@NamedQuery(name="Atividade.findAll", query="SELECT a FROM Atividade a")
public class Atividade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAtividade;

	private String descAtividade;

	//bi-directional many-to-many association to Inspecao
	@ManyToMany
	@JoinTable(
		name="Inspecao_Atividade_Apurada"
		, joinColumns={
			@JoinColumn(name="idAtividade")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idInspecao")
			}
		)
	private List<Inspecao> inspecao_Atividade_Apurada;

	//bi-directional many-to-many association to Inspecao
	@ManyToMany
	@JoinTable(
		name="Inspecao_Atividade_Informada"
		, joinColumns={
			@JoinColumn(name="idAtividade")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idInspecao")
			}
		)
	private List<Inspecao> inspecao_Atividade_Informada;

	public Atividade() {
	}

	public int getIdAtividade() {
		return this.idAtividade;
	}

	public void setIdAtividade(int idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getDescAtividade() {
		return this.descAtividade;
	}

	public void setDescAtividade(String descAtividade) {
		this.descAtividade = descAtividade;
	}

	public List<Inspecao> getInspecao_Atividade_Apurada() {
		return this.inspecao_Atividade_Apurada;
	}

	public void setInspecao_Atividade_Apurada(List<Inspecao> inspecao_Atividade_Apurada) {
		this.inspecao_Atividade_Apurada = inspecao_Atividade_Apurada;
	}

	public List<Inspecao> getInspecao_Atividade_Informada() {
		return this.inspecao_Atividade_Informada;
	}

	public void setInspecao_Atividade_Informada(List<Inspecao> inspecao_Atividade_Informada) {
		this.inspecao_Atividade_Informada = inspecao_Atividade_Informada;
	}

}