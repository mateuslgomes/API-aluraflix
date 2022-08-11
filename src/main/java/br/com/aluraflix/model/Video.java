package br.com.aluraflix.model;

import javax.persistence.*;

@Entity
@Table(name = "videos")
public class Video {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable=false, length=50)
    private String titulo;

    @Column(name="descricao", length=255)
    private String descricao;

    @Column(name="url", nullable=false, length=90)
    private String url;

    public Video() {}

    public Video(String titulo, String descricao, String url) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getUrl() {
        return url;
    }
}
