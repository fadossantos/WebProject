package br.com.tmsfasdom.model;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the Usuario database table.
 * 
 */
@Entity
@Table(name="Usuario", uniqueConstraints = @UniqueConstraint(columnNames = { "cpf" }))
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUsuario;
	
	@Transient
	private long expires;

	@Column(name="conta_ativa")
	private boolean contaAtiva;

	@Column(name="conta_expirada")
	private boolean contaExpirada;

	@Column(name="conta_travada")
	private boolean contaTravada;

	@Column(name="credencial_expirada")
	private boolean credencialExpirada;

	private String senha;
	
	private String username;

	public void setUsername(String username) {
		this.username = username;
	}

	//bi-directional many-to-one association to Funcionario
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cpf")
	private Funcionario funcionario;

	//bi-directional many-to-many association to Autorizacao
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="Usuario_Autorizacao"
		, joinColumns={
			@JoinColumn(name="idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idAutorizacao")
			}
		)
	private List<Autorizacao> autorizacaos;

	public Usuario() {
	}


	public long getExpires() {
		return expires;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}
	@JsonIgnore
	public Set<UserRole> getRoles() {
		Set<UserRole> roles = EnumSet.noneOf(UserRole.class);
		if (autorizacaos != null) {
			for (Autorizacao authority : autorizacaos) {
				roles.add(UserRole.valueOf(authority));
			}
		}
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		for (UserRole role : roles) {
			grantRole(role);
		}
	}

	public void grantRole(UserRole role) {
		if (autorizacaos == null) {
			autorizacaos = new ArrayList<Autorizacao>();
		}
		autorizacaos.add(role.asAuthorityFor(this));
	}

	public void revokeRole(UserRole role) {
		if (autorizacaos != null) {
			autorizacaos.remove(role.asAuthorityFor(this));
		}
	}

	public boolean hasRole(UserRole role) {
		return autorizacaos.contains(role.asAuthorityFor(this));
	}
	
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean getContaAtiva() {
		return this.contaAtiva;
	}

	public void setContaAtiva(boolean contaAtiva) {
		this.contaAtiva = contaAtiva;
	}

	public boolean getContaExpirada() {
		return this.contaExpirada;
	}

	public void setContaExpirada(boolean contaExpirada) {
		this.contaExpirada = contaExpirada;
	}

	public boolean getContaTravada() {
		return this.contaTravada;
	}

	public void setContaTravada(boolean contaTravada) {
		this.contaTravada = contaTravada;
	}

	public boolean getCredencialExpirada() {
		return this.credencialExpirada;
	}

	public void setCredencialExpirada(boolean credencialExpirada) {
		this.credencialExpirada = credencialExpirada;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Funcionario getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Autorizacao> getAutorizacaos() {
		return this.autorizacaos;
	}

	public void setAutorizacaos(List<Autorizacao> autorizacaos) {
		this.autorizacaos = autorizacaos;
	}

	@Override
	public List<Autorizacao> getAuthorities() {
		return this.autorizacaos;
	}
	@JsonIgnore
	@Override
	public String getPassword() {
		return this.getSenha();
	}

	@Override
	public String getUsername() {		
		return this.username;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		
		return !this.getContaExpirada();
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return !this.getContaTravada();
	}
	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return !this.getCredencialExpirada();
	}
	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return this.getContaAtiva();
	}

}