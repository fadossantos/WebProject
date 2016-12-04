package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Endereco database table.
 * 
 */
@Entity
@NamedQuery(name="Endereco.findAll", query="SELECT e FROM Endereco e")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idEndereco;

	private String bairro;

	private String cep;

	private String complemento;

	private double latitude;

	private String logradouro;

	private double longitude;

	private String numero;

	//bi-directional many-to-one association to Cidade
	@ManyToOne
	@JoinColumn(name="idCidade")
	private Cidade cidade;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="idEstado")
	private Estado estado;

	//bi-directional many-to-one association to TipoLogradouro
	@ManyToOne
	@JoinColumn(name="idTipoLogradouro")
	private TipoLogradouro tipoLogradouro;

	//bi-directional many-to-one association to Escritorio
	//@OneToMany(mappedBy="endereco")
	//private List<Escritorio> escritorios;

	//bi-directional many-to-one association to Inspecao
	//@OneToMany(mappedBy="endereco")
	//private List<Inspecao> inspecaos;

	public Endereco() {
	}

	public long getIdEndereco() {
		return this.idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cidade getCidade() {
		return this.cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public TipoLogradouro getTipoLogradouro() {
		return this.tipoLogradouro;
	}

	public void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	//public List<Escritorio> getEscritorios() {
	//	return this.escritorios;
	//}

	//public void setEscritorios(List<Escritorio> escritorios) {
	//	this.escritorios = escritorios;
	//}

	//public Escritorio addEscritorio(Escritorio escritorio) {
	//	getEscritorios().add(escritorio);
	//	escritorio.setEndereco(this);

	//	return escritorio;
	//}

	//public Escritorio removeEscritorio(Escritorio escritorio) {
	//	getEscritorios().remove(escritorio);
	//	escritorio.setEndereco(null);

	//	return escritorio;
	//}

	//public List<Inspecao> getInspecaos() {
	//	return this.inspecaos;
	//}

	//public void setInspecaos(List<Inspecao> inspecaos) {
	//	this.inspecaos = inspecaos;
	//}

	//public Inspecao addInspecao(Inspecao inspecao) {
	//	getInspecaos().add(inspecao);
	//	inspecao.setEndereco(this);

	//	return inspecao;
	//}

	//public Inspecao removeInspecao(Inspecao inspecao) {
	//	getInspecaos().remove(inspecao);
	//	inspecao.setEndereco(null);

	//	return inspecao;
	//}

}