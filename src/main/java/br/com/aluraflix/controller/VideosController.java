package br.com.aluraflix.controller;

import br.com.aluraflix.dtos.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import br.com.aluraflix.services.CategoriaService;
import br.com.aluraflix.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideosController {

    private static VideoService videoService;
    private static CategoriaService categoriaService;
    private static VideoRepository videoRepository;
    private static CategoriaRepository categoriaRepository;

    @RequestMapping
    public List<Video> videos(@RequestParam(value = "search", defaultValue = "") String titulo){
        if (Objects.equals(titulo, "")) {
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
        Video video = videoService.gerarVideo(dto);
        return videoService.save(video, uriBuilder);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable Long id, @RequestBody @Valid VideoDto dto) {
        return videoService.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id, VideoDto dto) {
        return ResponseEntity.badRequest().build();
    }
}
