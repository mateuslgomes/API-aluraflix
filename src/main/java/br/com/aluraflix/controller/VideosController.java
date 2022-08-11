package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping
    public List<Video> videos() {
        List<Video> videos = videoRepository.findAll();
        return videos;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<VideoDto> video(@PathVariable Long id) {
        Optional<Video> video = videoRepository.findById(id);
        if (!video.isEmpty()) {
            return ResponseEntity.ok(new VideoDto(video.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<VideoDto> saveVideo (@RequestBody @Valid VideoDto dto, UriComponentsBuilder uriBuilder) {
        Video video = dto.gerarVideo();
        if (video != null) {
            videoRepository.save(video);
            URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
            return ResponseEntity.created(uri).body(new VideoDto(video));
        }
            return ResponseEntity.badRequest().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<VideoDto> updateVideo(@PathVariable Long id, @RequestBody @Valid VideoDto dto) {
        Video video = dto.update(id, videoRepository);
        if (video != null) {
            videoRepository.save(video);
            return ResponseEntity.ok(new VideoDto(video));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id, VideoDto dto) {
        if (dto.delete(id, videoRepository)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
