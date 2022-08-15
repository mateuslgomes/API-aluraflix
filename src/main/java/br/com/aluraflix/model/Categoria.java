package br.com.aluraflix.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="titulo", nullable=false, length=25)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(name="cor", nullable=false)
    private Cor cor;
    public Categoria(){}
   @OneToMany(mappedBy = "categoria")
    private List<Video> video;

    public void setVideos(List<Video> videos) {
        this.video = videos;
    }

    public Categoria(String titulo, Cor cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Cor getCor() {
        return cor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
