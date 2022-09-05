package br.com.aluraflix.services;

import br.com.aluraflix.dtos.VideoDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VideoService {

    public final static Long CATEGORIA_LIVRE = 1L;

    public static boolean UrlIsValid(String dto) {
        return dto.substring(0, 31).equals("https://www.youtube.com/watch?v");
    }

    public static Video gerarVideo(CategoriaRepository categoriaRepository, VideoDto dto) {
        if (UrlIsValid(dto.getUrl()) && dto.getCategoriaId() == null) {
            Optional<Categoria> categoria = categoriaRepository.findById(CATEGORIA_LIVRE);
            return new Video(dto.getTitulo(), dto.getDescricao(), dto.getUrl(),
                    categoria.get());

        } else if (UrlIsValid(dto.getUrl()) && dto.getCategoriaId() != null) {
            Optional<Categoria> categoria = categoriaRepository.findById(dto.getCategoriaId());
            return new Video(dto.getTitulo(), dto.getDescricao(), dto.getUrl(),
                    categoria.get());
        }
        return null;
    }

    public static ResponseEntity<Video> update(Long id, VideoDto dto, VideoRepository videoRepository, CategoriaRepository categoriaRepository) {
        try  {
            Optional<Video> videoOptional = videoRepository.findById(id);
            Optional<Categoria> categoria = categoriaRepository.findById(dto.getCategoriaId());
            if (VideoService.UrlIsValid(dto.getUrl())) {
                Video video = videoOptional.get();
                video.setCategoria(categoria.get());
                video.setDescricao(dto.getDescricao());
                video.setTitulo(dto.getTitulo());
                video.setUrl(dto.getUrl());
                return ResponseEntity.ok(video);
            }
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }


    public static ResponseEntity<String> deleteById(Long id, VideoRepository videoRepository) {
        try {
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
