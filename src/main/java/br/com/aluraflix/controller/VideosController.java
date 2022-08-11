package br.com.aluraflix.controller;

import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        System.out.println(id);
        Optional<Video> video =  videoRepository.findById(id);
        return video;
    }

}
