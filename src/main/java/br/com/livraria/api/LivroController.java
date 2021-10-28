package br.com.livraria.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.livraria.modelo.Livro;
import br.com.livraria.service.LivroService;

@RequestMapping("api/v1/teste")
@RestController
public class LivroController {
	
	private final LivroService livroService;
	
	@Autowired
	public LivroController(LivroService livroService) {
		this.livroService = livroService;
	}
	
	@PostMapping
	public void addLivro(@RequestBody Livro livro) {
		livroService.adiciona(livro);
	}
	
	@GetMapping
	public List<Livro> getLivro(@RequestBody Livro livro) {
		return livroService.consultaLivro(livro);
	}
	
	@GetMapping(path = "/{codigoDeBarras}/{usuario}")
	public ResponseEntity<String> emprestaLivro(@PathVariable("codigoDeBarras") int codigoDeBarras, @PathVariable("usuario") String usuario) {
		HttpStatus status = HttpStatus.OK;
		try {
			String livro  = livroService.emprestaLivro(codigoDeBarras, usuario);
			return ResponseEntity.status(status).body(livro);
		}
		catch (NullPointerException e) {
			status = HttpStatus.NOT_FOUND;
			return ResponseEntity.status(status).body("Não foi possivel emprestar o livro");
		}
	}
}
