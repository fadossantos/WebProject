package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;


/**
 * The persistent class for the Autorizacao database table.
 * 
 */
@Entity
@NamedQuery(name="Autorizacao.findAll", query="SELECT a FROM Autorizacao a")
public class Autorizacao implements Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAutorizacao;

	@Column(name="desc_autorizacao")
	private String descAutorizacao;

	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="autorizacaos", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Usuario> usuarios;

	public Autorizacao() {
	}

	public int getIdAutorizacao() {
		return this.idAutorizacao;
	}

	public void setIdAutorizacao(int idAutorizacao) {
		this.idAutorizacao = idAutorizacao;
	}

	public String getDescAutorizacao() {
		return this.descAutorizacao;
	}

	public void setDescAutorizacao(String descAutorizacao) {
		this.descAutorizacao = descAutorizacao;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@JsonIgnore
	@Override
	public String getAuthority() {
			return this.descAutorizacao;
	}
	
	public void setAuthority(String authority) {
		this.descAutorizacao = authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Autorizacao))
			return false;

		Autorizacao ua = (Autorizacao) obj;
		return ua.getAuthority() == this.getAuthority() || ua.getAuthority().equals(this.getAuthority());
	}
	
	@Override
	public int hashCode() {
		return getAuthority() == null ? 0 : getAuthority().hashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ": " + getAuthority();
	}
	
}