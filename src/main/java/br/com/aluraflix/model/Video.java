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

}
