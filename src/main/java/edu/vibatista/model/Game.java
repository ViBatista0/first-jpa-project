package edu.vibatista.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "games")
public class Game extends Produto {
    private String estudio;
    private String classificacaoIndicativa;

    public Game() {
    }

    public Game(String nome, String descricao, BigDecimal preco, Categoria categoria, String estudio, String classificacaoIndicativa) {
        super(nome, descricao, preco, categoria);
        this.estudio = estudio;
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }
}
