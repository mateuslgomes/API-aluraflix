package br.com.aluraflix.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "categoria")
    private Categoria categoria;

    public Video(String titulo, String descricao, String url, Categoria categoria) {
        this.categoria = categoria;
        this.titulo = titulo;
        this.descricao = descricao;
        this.url = url;
    }
}
