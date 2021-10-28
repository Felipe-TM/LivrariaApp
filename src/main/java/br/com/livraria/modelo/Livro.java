package br.com.livraria.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "livros")
public class Livro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Transient
	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'as' HH:mm:ss ");
	@Transient
	private Date dataAtual = new Date(System.currentTimeMillis());
	
	private String nome;
	private String autor;
	private String descricao;
	@Column(name = "codigo_de_barras")
	private int codigoDeBarras;
	@Column(name = "data_aquisacao")
	private String dataAquisicao = formatter.format(dataAtual);

	@Enumerated(EnumType.STRING)
	@Column(name = "estado_do_livro")
	private Estado estadoDoLivro;
	
	@OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
	private List<HistoricoEmprestimo> historicoDeEmprestimos = new ArrayList<HistoricoEmprestimo>();

	public Livro() {
	}

	public Livro(@JsonProperty("nome") String nome, @JsonProperty("autor") String autor,
			@JsonProperty("codigoDeBarras") int codigoDeBarras) {

		this.nome = nome;
		this.autor = autor;
		this.codigoDeBarras = codigoDeBarras;
		this.estadoDoLivro = Estado.ESTOQUE;
		this.dataAquisicao = formatter.format(dataAtual);
	}

	public Livro(String nome, String autor, String descricao, int codigoDeBarras) {

		this.nome = nome;
		this.autor = autor;
		this.descricao = descricao;
		this.codigoDeBarras = codigoDeBarras;
		this.estadoDoLivro = Estado.ESTOQUE;
		this.dataAquisicao = formatter.format(dataAtual);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(int codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Estado getEstadoDoLivro() {
		return estadoDoLivro;
	}

	public void setEstadoDoLivro(Estado estadoDoLivro) {
		this.estadoDoLivro = estadoDoLivro;
	}

	public List<HistoricoEmprestimo> getHistoricoDeEmprestimos() {
		return historicoDeEmprestimos;
	}

	public void setHistoricoDeEmprestimos(HistoricoEmprestimo emprestimo) {
		this.historicoDeEmprestimos.add(emprestimo);
		emprestimo.setLivro(this);
	}

	public String getDataAtual() {
		return formatter.format(dataAtual);
	}

	@Override
	public String toString() {
		return "Livro: " + this.nome + " Autor: " + this.autor + " cd: " + this.codigoDeBarras;
	}

}
