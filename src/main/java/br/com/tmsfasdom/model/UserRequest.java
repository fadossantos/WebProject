package br.com.tmsfasdom.model;

public class UserRequest {
	
	String cpf;
	String senha;
	String[] imei;
	
	
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRequest(String cpf, String senha, String[] imei) {
		super();
		this.cpf = cpf;
		this.senha = senha;
		this.imei = imei;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String[] getImei() {
		return imei;
	}
	
	public void setImei(String[] imei) {
		this.imei = imei;
	}

	
}
