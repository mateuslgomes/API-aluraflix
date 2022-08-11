package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Video;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VideoDto {

    private Long id;

    @NotNull @NotEmpty @Length(max = 50, min = 5)
    private String titulo;

    @Length(max = 255)
    private String descricao;

    @NotNull @NotEmpty @Length(max = 90, min = 5)
    private String url;

    public VideoDto(Video video) {
        this.id = video.getId();
        this.descricao = video.getDescricao();
        this.url = video.getUrl();
        this.titulo = video.getTitulo();
    }

    public VideoDto(){}

    public Video converter() {
        Video video = new Video(titulo=this.titulo, descricao=this.descricao, url=this.url);
        return video;
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
