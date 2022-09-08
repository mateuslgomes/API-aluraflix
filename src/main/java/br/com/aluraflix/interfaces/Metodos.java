package br.com.aluraflix.interfaces;

import br.com.aluraflix.dtos.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.http.ResponseEntity;

public interface Metodos {

    public Object update(Long id, VideoDto dto);

    public Object deleteById(Long id);

}
