package br.com.livraria.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "historico_emprestimos")
public class HistoricoEmprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "data_emprestimo")
	private String dataEmprestimo;
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_de_transacao")
	private Transacao tipoDeTrancao;
	@ManyToOne
	private Livro livro;
	@ManyToOne
	private Usuario usuario;

	public HistoricoEmprestimo(String dataEmprestimo, Transacao transacao, Usuario usuario) {
		this.dataEmprestimo = dataEmprestimo;
		this.tipoDeTrancao = transacao;
		this.usuario = usuario;

	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public HistoricoEmprestimo() {
	}

	public Long getId() {
		return id;
	}

	public String getDataEmprestimo() {
		return dataEmprestimo;
	}

}
