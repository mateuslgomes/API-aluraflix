package br.com.aluraflix.controller;

import br.com.aluraflix.dtos.CategoriaDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import br.com.aluraflix.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;
    private final CategoriaRepository categoriaRepository;
    private final VideoRepository videoRepository;

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
        List<Video> videos = videoRepository.findByCategoria(id);
        if (!videos.isEmpty()) {
            return ResponseEntity.ok(videos);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Categoria> saveCategoria (@RequestBody @Valid CategoriaDto dto, UriComponentsBuilder uriBuilder) {
        Categoria categoria = service.gerar(dto);
        return service.save(categoria, uriBuilder);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id, CategoriaDto dto) {
        return service.deleteById(id);
    }
}
