package br.com.aluraflix.dtos;

import br.com.aluraflix.enums.Cor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
@Setter
public class CategoriaDto {
    @NotNull @NotEmpty @NotBlank @Length(max = 60, min = 3)
    private String titulo;
    @NotNull
    private Cor cor;
}
