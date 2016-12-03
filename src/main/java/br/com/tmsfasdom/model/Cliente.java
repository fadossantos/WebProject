package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCliente;

	private String nomeCliente;

	private int prazoCliente;

	//bi-directional many-to-many association to Ramo
	@ManyToMany
	@JoinTable(
		name="Cliente_Ramos"
		, joinColumns={
			@JoinColumn(name="idCliente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idRamos")
			}
		)
	private List<Ramo> ramos;

	//bi-directional many-to-many association to TipoInspecao
	@ManyToMany
	@JoinTable(
		name="Cliente_TipoInspecao"
		, joinColumns={
			@JoinColumn(name="idCliente")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idTipoInspecao")
			}
		)
	private List<TipoInspecao> tipoInspecaos;

	//bi-directional many-to-one association to Inspecao
	@OneToMany(mappedBy="cliente")
	private List<Inspecao> inspecaos;

	public Cliente() {
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeCliente() {
		return this.nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getPrazoCliente() {
		return this.prazoCliente;
	}

	public void setPrazoCliente(int prazoCliente) {
		this.prazoCliente = prazoCliente;
	}

	public List<Ramo> getRamos() {
		return this.ramos;
	}

	public void setRamos(List<Ramo> ramos) {
		this.ramos = ramos;
	}

	public List<TipoInspecao> getTipoInspecaos() {
		return this.tipoInspecaos;
	}

	public void setTipoInspecaos(List<TipoInspecao> tipoInspecaos) {
		this.tipoInspecaos = tipoInspecaos;
	}

	public List<Inspecao> getInspecaos() {
		return this.inspecaos;
	}

	public void setInspecaos(List<Inspecao> inspecaos) {
		this.inspecaos = inspecaos;
	}

	public Inspecao addInspecao(Inspecao inspecao) {
		getInspecaos().add(inspecao);
		inspecao.setCliente(this);

		return inspecao;
	}

	public Inspecao removeInspecao(Inspecao inspecao) {
		getInspecaos().remove(inspecao);
		inspecao.setCliente(null);

		return inspecao;
	}

}