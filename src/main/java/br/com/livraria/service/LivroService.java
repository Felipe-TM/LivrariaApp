package br.com.livraria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.livraria.DAO.LivroRepository;
import br.com.livraria.DAO.UsuarioRepository;
import br.com.livraria.modelo.Livro;
import br.com.livraria.modelo.Usuario;


@Service
public class LivroService {
	
	private final LivroRepository livroRepository;
	private final UsuarioRepository usarioRepository;
	
	@Autowired
	public LivroService(LivroRepository livroRepository, UsuarioRepository usuarioRepository) {
		this.livroRepository = livroRepository;
		this.usarioRepository = usuarioRepository;
	}
	
	public void adiciona(Livro livro) {
		 livroRepository.save(livro);
	}
	
	public List<Livro> consultaLivro(Livro livro) {
		return livroRepository.getLivroPorCodigoDeBarras(livro.getCodigoDeBarras());
	}
	
	public String emprestaLivro(int codigoDeBarras, String usuarioNome) {
		Usuario usuario = usarioRepository.getUsuario(usuarioNome);
		return livroRepository.emprestaLivro(codigoDeBarras, usuario);
	}
	
	
}
