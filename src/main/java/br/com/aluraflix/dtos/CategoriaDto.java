package br.com.aluraflix.dtos;

import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.enums.Cor;
import br.com.aluraflix.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.NoSuchElementException;
import java.util.Optional;


@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class CategoriaDto {
    private Long id;

    @NotNull @NotEmpty @NotBlank @Length(max = 60, min = 3)
    private String titulo;

    @NotNull
    private Cor cor;

    public CategoriaDto(String titulo, Cor cor) {
        this.titulo = titulo;
        this.cor = cor;
    }

}
