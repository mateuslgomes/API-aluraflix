package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Cor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CategoriaDto {
    private Long id;

    @NotNull @NotEmpty @Length(max = 60, min = 3)
    private String titulo;

    @NotNull
    private Cor cor;

    public CategoriaDto(String titulo, Cor cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public CategoriaDto() {}

    public CategoriaDto(Categoria categoria) {
        this.id = categoria.getId();
        this.titulo = categoria.getTitulo();
        this.cor = categoria.getCor();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public Categoria gerarCategoria() {
        return new Categoria(this.titulo, this.getCor());
    }
}
