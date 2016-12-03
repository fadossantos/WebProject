package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Ramos database table.
 * 
 */
@Entity
@Table(name="Ramos")
@NamedQuery(name="Ramo.findAll", query="SELECT r FROM Ramo r")
public class Ramo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRamos;

	private String descRamos;

	//bi-directional many-to-many association to Cliente
	@ManyToMany(mappedBy="ramos")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Inspecao
	@OneToMany(mappedBy="ramo")
	private List<Inspecao> inspecaos;

	public Ramo() {
	}

	public int getIdRamos() {
		return this.idRamos;
	}

	public void setIdRamos(int idRamos) {
		this.idRamos = idRamos;
	}

	public String getDescRamos() {
		return this.descRamos;
	}

	public void setDescRamos(String descRamos) {
		this.descRamos = descRamos;
	}

	public List<Cliente> getClientes() {
		return this.clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Inspecao> getInspecaos() {
		return this.inspecaos;
	}

	public void setInspecaos(List<Inspecao> inspecaos) {
		this.inspecaos = inspecaos;
	}

	public Inspecao addInspecao(Inspecao inspecao) {
		getInspecaos().add(inspecao);
		inspecao.setRamo(this);

		return inspecao;
	}

	public Inspecao removeInspecao(Inspecao inspecao) {
		getInspecaos().remove(inspecao);
		inspecao.setRamo(null);

		return inspecao;
	}

}