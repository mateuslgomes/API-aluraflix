package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
public class VideosController {
    @Autowired
    VideoRepository videoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping
    public List<Video> videos(@RequestParam(required = false, value = "search") String titulo){
        if (titulo == null) {
            Sort sort = Sort.by("id").descending();
            return videoRepository.findAll();
        }
        return videoRepository.findByTitulo(titulo);
    }

    @RequestMapping("/free")
    public List<Video> videosFree(){
        return videoRepository.findAll();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Video> video(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Video> saveVideo (@RequestBody @Valid VideoDto dto, UriComponentsBuilder uriBuilder) {
        Video video = dto.gerarVideo(categoriaRepository);
        if (video != null) {
            videoRepository.save(video);
            URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            return ResponseEntity.created(uri).body(video);
        }
            return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody @Valid VideoDto dto) {
        Video video = dto.update(id, videoRepository, categoriaRepository);
        if (video != null) {
            videoRepository.save(video);
            return ResponseEntity.ok(video);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id, VideoDto dto) {
        try {
            videoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        catch (EmptyResultDataAccessException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
