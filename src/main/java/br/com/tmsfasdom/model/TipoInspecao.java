package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TipoInspecao database table.
 * 
 */
@Entity
@NamedQuery(name="TipoInspecao.findAll", query="SELECT t FROM TipoInspecao t")
public class TipoInspecao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTipoInspecao;

	private String descTipoInspeca;

	//bi-directional many-to-many association to Cliente
	@ManyToMany(mappedBy="tipoInspecaos")
	private List<Cliente> clientes;

	//bi-directional many-to-one association to Inspecao
	@OneToMany(mappedBy="tipoInspecao")
	private List<Inspecao> inspecaos;

	public TipoInspecao() {
	}

	public int getIdTipoInspecao() {
		return this.idTipoInspecao;
	}

	public void setIdTipoInspecao(int idTipoInspecao) {
		this.idTipoInspecao = idTipoInspecao;
	}

	public String getDescTipoInspeca() {
		return this.descTipoInspeca;
	}

	public void setDescTipoInspeca(String descTipoInspeca) {
		this.descTipoInspeca = descTipoInspeca;
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
		inspecao.setTipoInspecao(this);

		return inspecao;
	}

	public Inspecao removeInspecao(Inspecao inspecao) {
		getInspecaos().remove(inspecao);
		inspecao.setTipoInspecao(null);

		return inspecao;
	}

}