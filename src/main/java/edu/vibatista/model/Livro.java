package edu.vibatista.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
@Entity
@Table(name = "livros")
public class Livro extends Produto {
    private String autor;
    private Integer numeroPaginas;

    public Livro() {
    }

    public Livro(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, Integer numeroPaginas) {
        super(nome, descricao, preco, categoria);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(Integer numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}
