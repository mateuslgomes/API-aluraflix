package br.com.aluraflix.controller;

import br.com.aluraflix.dtos.VideoDto;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.services.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/videos")
public class VideosController {

    private VideoService service;

    @RequestMapping
    public List<Video> videos(@RequestParam(value = "search", defaultValue = "") String titulo){
        if (Objects.equals(titulo, "")) {
            return service.findAll();
        }
        return service.findByTitulo(titulo);
    }

    @RequestMapping("/free")
    public List<Video> videosFree(){
        return service.findAll();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Video> video(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<Video> save(@RequestBody @Valid VideoDto dto, UriComponentsBuilder builder) {
         return service.save(dto, builder);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Video> update(@PathVariable Long id, @RequestBody @Valid VideoDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, VideoDto dto) {
        return service.deleteById(id);
    }
}
