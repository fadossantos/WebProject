package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Escritorio database table.
 * 
 */
@Entity
@NamedQuery(name="Escritorio.findAll", query="SELECT e FROM Escritorio e")
public class Escritorio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idEscritorio;

	private String descEscritorio;

	//bi-directional many-to-one association to Endereco
	@ManyToOne
	@JoinColumn(name="idEndereco")
	private Endereco endereco;

	//bi-directional many-to-one association to Funcionario
	//@OneToMany(mappedBy="escritorio")
	//private List<Funcionario> funcionarios;

	public Escritorio() {
	}

	public long getIdEscritorio() {
		return this.idEscritorio;
	}

	public void setIdEscritorio(long idEscritorio) {
		this.idEscritorio = idEscritorio;
	}

	public String getDescEscritorio() {
		return this.descEscritorio;
	}

	public void setDescEscritorio(String descEscritorio) {
		this.descEscritorio = descEscritorio;
	}

	public Endereco getEndereco() {
		return this.endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	//public List<Funcionario> getFuncionarios() {
	//	return this.funcionarios;
	//}

	//public void setFuncionarios(List<Funcionario> funcionarios) {
	//	this.funcionarios = funcionarios;
	//}

	//public Funcionario addFuncionario(Funcionario funcionario) {
	//	getFuncionarios().add(funcionario);
	//	funcionario.setEscritorio(this);

	//	return funcionario;
	//}

	//public Funcionario removeFuncionario(Funcionario funcionario) {
	//	getFuncionarios().remove(funcionario);
	//	funcionario.setEscritorio(null);

	//	return funcionario;
	//}

}