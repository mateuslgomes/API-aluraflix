package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Cor;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public Categoria update(Long id, CategoriaRepository categoriaRepository) {
        try  {
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
            Categoria categoria = categoriaOptional.get();
            categoria.setTitulo(this.titulo);
            categoria.setCor(this.cor);
            return categoria;
        }
        catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean delete(Long id, CategoriaRepository categoriaRepository) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
