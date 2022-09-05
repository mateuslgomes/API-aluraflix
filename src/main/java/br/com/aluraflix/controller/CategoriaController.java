package br.com.aluraflix.controller;

import br.com.aluraflix.dtos.CategoriaDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import br.com.aluraflix.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    VideoRepository videoRepository;

    @RequestMapping
    public List<Categoria> categorias() {
        return categoriaRepository.findAll();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Categoria> categoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "/{id}/videos")
    public ResponseEntity<List<Video>> videoByCategoria(@PathVariable Long id) {
        List<Video> video = videoRepository.findByCategoria(id);
        if (!video.isEmpty()) {
            return ResponseEntity.ok(video);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> saveCategoria (@RequestBody @Valid CategoriaDto dto, UriComponentsBuilder uriBuilder) {
        Categoria categoria = CategoriaService.gerarCategoria(dto);
        categoriaRepository.save(categoria);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDto dto) {
        return CategoriaService.update(id, categoriaRepository, dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id, CategoriaDto dto) {
        return CategoriaService.delete(id, categoriaRepository, dto);
    }
}
