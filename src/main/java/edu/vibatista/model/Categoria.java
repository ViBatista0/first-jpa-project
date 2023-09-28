package edu.vibatista.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    private CategoriaId id;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.id = new CategoriaId(nome, "Acessórios");
    }

    public String getNome() {
        return this.id.getNome();
    }


    @Override
    public String toString() {
        return "\nCategoria: " + this.id.getNome();
    }
}
