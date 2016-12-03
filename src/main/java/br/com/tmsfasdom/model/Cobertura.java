package br.com.tmsfasdom.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Cobertura database table.
 * 
 */
@Entity
@NamedQuery(name="Cobertura.findAll", query="SELECT c FROM Cobertura c")
public class Cobertura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCobertura;

	private String descCobertura;


	//bi-directional many-to-one association to Inspecao_Cobertura
	@OneToMany(mappedBy="inspecao")
	private List<Inspecao_Cobertura> inspecaoCoberturas;

	
	public Cobertura() {
	}

	public int getIdCobertura() {
		return this.idCobertura;
	}

	public void setIdCobertura(int idCobertura) {
		this.idCobertura = idCobertura;
	}

	public String getDescCobertura() {
		return this.descCobertura;
	}

	public void setDescCobertura(String descCobertura) {
		this.descCobertura = descCobertura;
	}
	
	public List<Inspecao_Cobertura> getInspecaoCoberturas() {
		return this.inspecaoCoberturas;
	}

	public void setInspecaoCoberturas(List<Inspecao_Cobertura> inspecaoCoberturas) {
		this.inspecaoCoberturas = inspecaoCoberturas;
	}

	public Inspecao_Cobertura addInspecaoCobertura(Inspecao_Cobertura inspecaoCobertura) {
		getInspecaoCoberturas().add(inspecaoCobertura);
		inspecaoCobertura.setCobertura(this);

		return inspecaoCobertura;
	}

	public Inspecao_Cobertura removeInspecaoCobertura(Inspecao_Cobertura inspecaoCobertura) {
		getInspecaoCoberturas().remove(inspecaoCobertura);
		inspecaoCobertura.setCobertura(null);

		return inspecaoCobertura;
	}


}