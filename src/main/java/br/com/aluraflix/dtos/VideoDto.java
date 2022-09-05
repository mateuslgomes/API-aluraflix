package br.com.aluraflix.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Getter
@Setter
public class VideoDto {

    private Long id;

    @NotNull @NotEmpty @NotBlank @Length(max = 50, min = 5)
    private String titulo;

    @Length(max = 255)
    private String descricao;

    @NotNull @NotEmpty @Length(max = 90, min = 35)
    private String url;

    private Long categoriaId;

}
