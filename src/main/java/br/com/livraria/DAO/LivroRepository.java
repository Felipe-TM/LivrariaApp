package br.com.livraria.DAO;

import java.util.Date;
import java.util.List;
import java.lang.NullPointerException;
import java.text.SimpleDateFormat;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.livraria.modelo.Estado;
import br.com.livraria.modelo.HistoricoEmprestimo;
import br.com.livraria.modelo.Livro;
import br.com.livraria.modelo.Transacao;
import br.com.livraria.modelo.Usuario;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long>{

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'as' HH:mm:ss ");
	Date dataAtual = new Date(System.currentTimeMillis());

	@Query("SELECT livro FROM Livro livro WHERE livro.codigoDeBarras = ?1")
	public List<Livro> getLivroPorCodigoDeBarras(int codigoDeBarras);

	@Query("SELECT livro FROM Livro livro WHERE livro.codigoDeBarras = ?1 and livro.estadoDoLivro = ?2")
	public List<Livro> getLivrosEmEstoque(int codigoDeBarras, Estado liEstado);

	default String emprestaLivro(int codigoDeBarras, Usuario usuario) {
		List<Livro> livros = getLivrosEmEstoque(codigoDeBarras, Estado.ESTOQUE);
		if (livros.isEmpty()) {
			throw new NullPointerException();
		} else {
			Livro livro = livros.get(0);
			livro.setEstadoDoLivro(Estado.EMPRESTADO);
			HistoricoEmprestimo historicoDeEmprestimo = new HistoricoEmprestimo(formatter.format(dataAtual), Transacao.EMPRESTIMO,
					usuario);
			livro.setHistoricoDeEmprestimos(historicoDeEmprestimo);
			save(livro);
			return livro.toString();
		}
	}
}
