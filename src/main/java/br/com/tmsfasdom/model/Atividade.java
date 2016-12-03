package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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
	private List<Inspecao> inspecaos1;

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
	private List<Inspecao> inspecaos2;

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

	public List<Inspecao> getInspecaos1() {
		return this.inspecaos1;
	}

	public void setInspecaos1(List<Inspecao> inspecaos1) {
		this.inspecaos1 = inspecaos1;
	}

	public List<Inspecao> getInspecaos2() {
		return this.inspecaos2;
	}

	public void setInspecaos2(List<Inspecao> inspecaos2) {
		this.inspecaos2 = inspecaos2;
	}

}