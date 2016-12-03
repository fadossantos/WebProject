package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Estado database table.
 * 
 */
@Entity
@NamedQuery(name="Estado.findAll", query="SELECT e FROM Estado e")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idEstado;

	private String nomeEstado;
	
	private final String pais = "Brasil";

	public String getPais() {
		return pais;
	}

	private String siglaEstado;

	//bi-directional many-to-one association to Cidade
	//@OneToMany(mappedBy="estado")
	//private List<Cidade> cidades;

	//bi-directional many-to-one association to Endereco
	//@OneToMany(mappedBy="estado")
	//private List<Endereco> enderecos;

	public Estado() {
	}

	public long getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNomeEstado() {
		return this.nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}

	public String getSiglaEstado() {
		return this.siglaEstado;
	}

	public void setSiglaEstado(String siglaEstado) {
		this.siglaEstado = siglaEstado;
	}

	//public List<Cidade> getCidades() {
	//	return this.cidades;
	//}

	//public void setCidades(List<Cidade> cidades) {
	//	this.cidades = cidades;
	//}

	//public Cidade addCidade(Cidade cidade) {
	//	getCidades().add(cidade);
	//	cidade.setEstado(this);

	//	return cidade;
	//}

	//public Cidade removeCidade(Cidade cidade) {
	//	getCidades().remove(cidade);
	//	cidade.setEstado(null);

	//	return cidade;
	//}

	//public List<Endereco> getEnderecos() {
	//	return this.enderecos;
	//}

	//public void setEnderecos(List<Endereco> enderecos) {
	//	this.enderecos = enderecos;
	//}

	//public Endereco addEndereco(Endereco endereco) {
	//	getEnderecos().add(endereco);
	//	endereco.setEstado(this);

	//	return endereco;
	//}

	//public Endereco removeEndereco(Endereco endereco) {
	//	getEnderecos().remove(endereco);
	//	endereco.setEstado(null);

	//	return endereco;
	//}

}