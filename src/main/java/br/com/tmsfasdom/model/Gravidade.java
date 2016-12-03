package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Gravidade database table.
 * 
 */
@Entity
@NamedQuery(name="Gravidade.findAll", query="SELECT g FROM Gravidade g")
public class Gravidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idGravidade;

	private String descGravidade;

	private int peso;

	//bi-directional many-to-one association to NaoConformidade
	@OneToMany(mappedBy="gravidade")
	private List<NaoConformidade> naoConformidades;

	public Gravidade() {
	}

	public int getIdGravidade() {
		return this.idGravidade;
	}

	public void setIdGravidade(int idGravidade) {
		this.idGravidade = idGravidade;
	}

	public String getDescGravidade() {
		return this.descGravidade;
	}

	public void setDescGravidade(String descGravidade) {
		this.descGravidade = descGravidade;
	}

	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public List<NaoConformidade> getNaoConformidades() {
		return this.naoConformidades;
	}

	public void setNaoConformidades(List<NaoConformidade> naoConformidades) {
		this.naoConformidades = naoConformidades;
	}

	public NaoConformidade addNaoConformidade(NaoConformidade naoConformidade) {
		getNaoConformidades().add(naoConformidade);
		naoConformidade.setGravidade(this);

		return naoConformidade;
	}

	public NaoConformidade removeNaoConformidade(NaoConformidade naoConformidade) {
		getNaoConformidades().remove(naoConformidade);
		naoConformidade.setGravidade(null);

		return naoConformidade;
	}

}