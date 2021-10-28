package br.com.livraria.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.livraria.modelo.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>{
	
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.usuario = ?1")
	public Usuario getUsuario(String usuario);
	
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.usuario = ?1 AND usuario.senha = ?2")
	public Usuario getUsuario(String usuario, String senha);
}
