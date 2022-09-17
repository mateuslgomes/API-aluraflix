package br.com.aluraflix.services;

import br.com.aluraflix.dtos.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@Service
public class VideoService {

    private VideoRepository repository;

    private CategoriaRepository categoriaRepository;

    private final static Long CATEGORIA_LIVRE = 1L;

    private static boolean urlIsValid(String url) {
        return url.substring(0, 31).equals("https://www.youtube.com/watch?v");
    }

    public ResponseEntity<String> deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<Video> update(Long id, VideoDto dto) {
        if (repository.existsById(id)) {
            repository.save(new Video(id, dto.getTitulo(), dto.getDescricao(),
                    dto.getUrl(), categoriaRepository.findById(dto.getCategoriaId()).get()));
        }
        return ResponseEntity.notFound().build();
    }
    public ResponseEntity<Video> save(VideoDto dto, UriComponentsBuilder builder) {
        if (urlIsValid(dto.getUrl()) && dto.getCategoriaId() != null && categoriaRepository.existsById(dto.getCategoriaId())) {
            Video video = new Video(dto.getTitulo(), dto.getDescricao(), dto.getUrl(), categoriaRepository.findById(dto.getCategoriaId()).get());
            URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            repository.save(video);
            return ResponseEntity.created(uri).body(video);

        } else if (dto.getCategoriaId() == null && urlIsValid(dto.getUrl())) {
            Video video = new Video(dto.getTitulo(), dto.getDescricao(), dto.getUrl(), categoriaRepository.findById(dto.getCategoriaId()).get());
            URI uri = builder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            repository.save(video);
            return ResponseEntity.created(uri).body(video);
        }
            return ResponseEntity.badRequest().build();
        }

    public List<Video> findAll() {
        return repository.findAll();
    }

    public List<Video> findByTitulo(String titulo) {
        return repository.findByTitulo(titulo);
    }

    public ResponseEntity<Video> findById(Long id) {
        if (repository.existsById(id)) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return ResponseEntity.notFound().build();
    }
}
