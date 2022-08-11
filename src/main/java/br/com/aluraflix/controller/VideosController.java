package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
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
    public Optional<Video> video(@PathVariable Long id) {
        Optional<Video> video =  videoRepository.findById(id);
        return video;
    }

    @PostMapping
    public ResponseEntity<VideoDto> saveVideo (@RequestBody @Valid VideoDto videoDto, UriComponentsBuilder uriBuilder) {
        Video video = videoDto.converter();
        videoRepository.save(video);
        URI uri = uriBuilder.path("/videos/{id}").buildAndExpand(video.getId()).toUri();
        return ResponseEntity.created(uri).body(new VideoDto(video));
    }

}
