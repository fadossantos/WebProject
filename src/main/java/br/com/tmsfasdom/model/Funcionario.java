package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the Funcionarios database table.
 * 
 */
@Entity
@Table(name="Funcionarios", uniqueConstraints = @UniqueConstraint(columnNames = { "cpf" }))
@NamedQuery(name="Funcionario.findAll", query="SELECT f FROM Funcionario f")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String cpf;

	private byte ativo;

	private String email;

	private String nomeFuncionario;

	private String numeroMatricula;

	//bi-directional many-to-one association to Designacao
	//@OneToMany(mappedBy="funcionario", fetch = FetchType)
	//private List<Designacao> designacaos;

	//bi-directional many-to-one association to Escritorio
	@ManyToOne
	@JoinColumn(name="idEscritorioRegional")
	private Escritorio escritorio;

	//bi-directional many-to-one association to InsercaoSistema
	//@OneToMany(mappedBy="funcionarioSupervisor")
	//private List<InsercaoSistema> insercaoSistemasSupervisados;

	//bi-directional many-to-one association to InsercaoSistema
	//@OneToMany(mappedBy="funcionarioResponsavelInsercao")
	//private List<InsercaoSistema> insercaoSistemasResponsabilizados;

	//bi-directional many-to-one association to Relatorio
	//@OneToMany(mappedBy="funcionario")
	//private List<Relatorio> relatorios;

	//bi-directional many-to-one association to Revisao
	//@OneToMany(mappedBy="funcionario")
	//private List<Revisao> revisaos;

	//bi-directional many-to-one association to Usuario
	@OneToOne(mappedBy="funcionario", fetch = FetchType.EAGER)
	@JsonIgnore
	private Usuario usuario;

	//bi-directional many-to-one association to Vistoria
	//@OneToMany(mappedBy="funcionario")
	//private List<Vistoria> vistorias;

	public Funcionario() {
	}

	public byte getAtivo() {
		return this.ativo;
	}

	public void setAtivo(byte ativo) {
		this.ativo = ativo;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeFuncionario() {
		return this.nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public String getNumeroMatricula() {
		return this.numeroMatricula;
	}

	public void setNumeroMatricula(String numeroMatricula) {
		this.numeroMatricula = numeroMatricula;
	}

	//public List<Designacao> getDesignacaos() {
	//	return this.designacaos;
	//}

	//public void setDesignacaos(List<Designacao> designacaos) {
	//	this.designacaos = designacaos;
	//}

	//public Designacao addDesignacao(Designacao designacao) {
	//	getDesignacaos().add(designacao);
	//	designacao.setFuncionario(this);

	//	return designacao;
	//}

	//public Designacao removeDesignacao(Designacao designacao) {
	//	getDesignacaos().remove(designacao);
	//	designacao.setFuncionario(null);

	//	return designacao;
	//}

	public Escritorio getEscritorio() {
		return this.escritorio;
	}

	public void setEscritorio(Escritorio escritorio) {
		this.escritorio = escritorio;
	}

	//public List<InsercaoSistema> getInsercaoSistemasSupervisados() {
	//	return this.insercaoSistemasSupervisados;
	//}

	//public void setInsercaoSistemasSupervisados(List<InsercaoSistema> insercaoSistemasSupervisados) {
	//	this.insercaoSistemasSupervisados = insercaoSistemasSupervisados;
	//}

	//public InsercaoSistema addInsercaoSistemasSupervisados(InsercaoSistema insercaoSistemasSupervisados) {
	//	getInsercaoSistemasSupervisados().add(insercaoSistemasSupervisados);
	//	insercaoSistemasSupervisados.setFuncionarioSupervisor(this);

	//	return insercaoSistemasSupervisados;
	//}

	//public InsercaoSistema removeInsercaoSistemasSupervisados(InsercaoSistema insercaoSistemasSupervisados) {
	//	getInsercaoSistemasSupervisados().remove(insercaoSistemasSupervisados);
	//	insercaoSistemasSupervisados.setFuncionarioSupervisor(null);

	//	return insercaoSistemasSupervisados;
	//}

	//public List<InsercaoSistema> getInsercaoSistemasResponsabilizados() {
	//	return this.insercaoSistemasResponsabilizados;
	//}

	//public void setInsercaoSistemas2(List<InsercaoSistema> insercaoSistemasResponsabilizados) {
	//	this.insercaoSistemasResponsabilizados = insercaoSistemasResponsabilizados;
	//}

	//public InsercaoSistema addInsercaoSistemas2(InsercaoSistema insercaoSistemasResponsabilizados) {
	//	getInsercaoSistemasResponsabilizados().add(insercaoSistemasResponsabilizados);
	//	insercaoSistemasResponsabilizados.setFuncionarioResponsavelInsercao(this);

	//	return insercaoSistemasResponsabilizados;
	//}

	//public InsercaoSistema removeInsercaoSistemas2(InsercaoSistema insercaoSistemasResponsabilizados) {
	//	getInsercaoSistemasResponsabilizados().remove(insercaoSistemasResponsabilizados);
	//	insercaoSistemasResponsabilizados.setFuncionarioResponsavelInsercao(null);

	//	return insercaoSistemasResponsabilizados;
	//}

	//public List<Relatorio> getRelatorios() {
	//	return this.relatorios;
	//}

	//public void setRelatorios(List<Relatorio> relatorios) {
	//	this.relatorios = relatorios;
	//}

	//public Relatorio addRelatorio(Relatorio relatorio) {
	//	getRelatorios().add(relatorio);
	//	relatorio.setFuncionario(this);

	//	return relatorio;
	//}

	//public Relatorio removeRelatorio(Relatorio relatorio) {
	//	getRelatorios().remove(relatorio);
	//	relatorio.setFuncionario(null);

	//	return relatorio;
	//}

	//public List<Revisao> getRevisaos() {
	//	return this.revisaos;
	//}

	//public void setRevisaos(List<Revisao> revisaos) {
	//	this.revisaos = revisaos;
	//}

	//public Revisao addRevisao(Revisao revisao) {
	//	getRevisaos().add(revisao);
	//	revisao.setFuncionario(this);

	//	return revisao;
	//}

	//public Revisao removeRevisao(Revisao revisao) {
	//	getRevisaos().remove(revisao);
	//	revisao.setFuncionario(null);

	//	return revisao;
	//}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
		usuario.setFuncionario(this);
	}

	//public List<Vistoria> getVistorias() {
	//	return this.vistorias;
	//}

	//public void setVistorias(List<Vistoria> vistorias) {
	//	this.vistorias = vistorias;
	//}

	//public Vistoria addVistoria(Vistoria vistoria) {
	//	getVistorias().add(vistoria);
	//	vistoria.setFuncionario(this);

	//	return vistoria;
	//}

	//public Vistoria removeVistoria(Vistoria vistoria) {
	//	getVistorias().remove(vistoria);
	//	vistoria.setFuncionario(null);

	//	return vistoria;
	//}

}