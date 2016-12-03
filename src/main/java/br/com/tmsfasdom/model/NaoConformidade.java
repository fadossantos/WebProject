package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the NaoConformidades database table.
 * 
 */
@Entity
@Table(name="NaoConformidades")
@NamedQuery(name="NaoConformidade.findAll", query="SELECT n FROM NaoConformidade n")
public class NaoConformidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idNaoConformidades;

	private String descNaoConformidade;

	//bi-directional many-to-one association to Gravidade
	@ManyToOne
	@JoinColumn(name="idGravidade")
	private Gravidade gravidade;

	//bi-directional many-to-many association to Relatorio
	@ManyToMany
	@JoinTable(
		name="Relatorio_NaoConformidade"
		, joinColumns={
			@JoinColumn(name="idNaoConformidade")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idRelatorio")
			}
		)
	private List<Relatorio> relatorios;

	//bi-directional many-to-many association to Revisao
	@ManyToMany
	@JoinTable(
		name="Revisao_NaoConformidade"
		, joinColumns={
			@JoinColumn(name="idNaoConformidade")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idRevisao")
			}
		)
	private List<Revisao> revisaos;

	public NaoConformidade() {
	}

	public int getIdNaoConformidades() {
		return this.idNaoConformidades;
	}

	public void setIdNaoConformidades(int idNaoConformidades) {
		this.idNaoConformidades = idNaoConformidades;
	}

	public String getDescNaoConformidade() {
		return this.descNaoConformidade;
	}

	public void setDescNaoConformidade(String descNaoConformidade) {
		this.descNaoConformidade = descNaoConformidade;
	}

	public Gravidade getGravidade() {
		return this.gravidade;
	}

	public void setGravidade(Gravidade gravidade) {
		this.gravidade = gravidade;
	}

	public List<Relatorio> getRelatorios() {
		return this.relatorios;
	}

	public void setRelatorios(List<Relatorio> relatorios) {
		this.relatorios = relatorios;
	}

	public List<Revisao> getRevisaos() {
		return this.revisaos;
	}

	public void setRevisaos(List<Revisao> revisaos) {
		this.revisaos = revisaos;
	}

}