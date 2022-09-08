package br.com.aluraflix.services;

import br.com.aluraflix.dtos.VideoDto;
import br.com.aluraflix.interfaces.Metodos;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VideoService implements Metodos {

    private final VideoRepository repository;

    private final CategoriaRepository categoriaRepository;

    private final static Long CATEGORIA_LIVRE = 1L;

    private static boolean urlIsValid(String dto) {
        return dto.substring(0, 31).equals("https://www.youtube.com/watch?v");
    }

    public Video gerarVideo(VideoDto dto) {
        if (urlIsValid(dto.getUrl()) && dto.getCategoriaId() == null) {
            Optional<Categoria> categoria = categoriaRepository.findById(CATEGORIA_LIVRE);
            return new Video(dto.getTitulo(), dto.getDescricao(), dto.getUrl(),
                    categoria.get());

        } else if (urlIsValid(dto.getUrl()) && dto.getCategoriaId() != null) {
            Optional<Categoria> categoria = categoriaRepository.findById(dto.getCategoriaId());
            return new Video(dto.getTitulo(), dto.getDescricao(), dto.getUrl(),
                    categoria.get());
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        Optional<Video> video = repository.findById(id);
        if (video.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    private static Video updateVideo(Video video, Categoria categoria, VideoDto dto) {
        video.setDescricao(dto.getDescricao());
        video.setTitulo(dto.getTitulo());
        video.setUrl(dto.getUrl());
        return video;
    }

    @Override
    public ResponseEntity<Video> update(Long id, VideoDto dto) {
        Optional<Video> videoOptional = repository.findById(id);
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(dto.getCategoriaId());
        if (categoriaOptional.isPresent() && videoOptional.isPresent() && urlIsValid(dto.getUrl())) {
            return ResponseEntity.ok(updateVideo(videoOptional.get(), categoriaOptional.get(), dto));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Video> save(Video video, UriComponentsBuilder builder) {
        if (video != null) {
            URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(video);
        }
        return ResponseEntity.badRequest().build();
    }
}
