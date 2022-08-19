package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;
import java.util.Optional;

public class VideoDto {

    private Long id;

    @NotNull @NotEmpty @NotBlank @Length(max = 50, min = 5)
    private String titulo;

    @Length(max = 255)
    private String descricao;

    @NotNull @NotEmpty @Length(max = 90, min = 35)
    private String url;

    private Long categoriaId;

    public final static Long CATEGORIA_LIVRE = 1L;

    public VideoDto(){}

    private Boolean validarUrl() {
        return url.substring(0, 31).equals("https://www.youtube.com/watch?v");
    }

    public Video gerarVideo(CategoriaRepository categoriaRepository) {
        if (validarUrl() && this.categoriaId == null) {
            Optional<Categoria> categoria = categoriaRepository.findById(CATEGORIA_LIVRE);
            return new Video(titulo = this.titulo, descricao = this.descricao, url = this.url,
                   categoria.get());

        } if (validarUrl() && this.categoriaId != null) {
            Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
            return new Video(titulo = this.titulo, descricao = this.descricao, url = this.url,
                    categoria.get());
        }
        return null;
    }

    public Video update(Long id, VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
        try  {
            Optional<Video> videoOptional = videoRepository.findById(id);
            Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
            if (validarUrl()) {
                Video video = videoOptional.get();
                video.setCategoria(categoria.get());
                video.setDescricao(this.descricao);
                video.setTitulo(this.titulo);
                video.setUrl(this.url);
                return video;
            }
        }
        catch (NoSuchElementException e) {
            return null;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
