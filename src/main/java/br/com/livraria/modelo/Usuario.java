package br.com.livraria.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String usuario;
	private String senha;

	public Usuario() {
	}

	public Usuario(String username, String password) {
		super();
		this.usuario = username;
		this.senha = password;
	}

	@Override
	public String toString() {
		return "Usuário: " + usuario;
	}

	public String getUsername() {
		return usuario;
	}

	public String getPassword() {
		return senha;
	}

}
