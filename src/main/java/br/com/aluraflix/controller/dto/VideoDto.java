package br.com.aluraflix.controller.dto;

import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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

    public Video convert() {
        if (url.substring(0, 31).equals("https://www.youtube.com/watch?v")) {
            Video video = new Video(titulo = this.titulo, descricao = this.descricao, url = this.url);
            return video;
        }
        return null;
    }

    public Video update(Long id, VideoRepository videoRepository) {
        Optional<Video> videoOptional = videoRepository.findById(id);
        Video video = videoOptional.get();
        video.setDescricao(this.descricao);
        video.setTitulo(this.titulo);
        video.setUrl(this.url);
        videoRepository.save(video);
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

    public void delete(Long id, VideoRepository videoRepository) {
        videoRepository.deleteById(id);
    }
}