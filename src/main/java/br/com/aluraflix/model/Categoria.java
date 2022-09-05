package br.com.aluraflix.model;

import br.com.aluraflix.enums.Cor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    public Categoria(String titulo, Cor cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

}
