package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.CategoriaDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
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
        return categoria.map(value -> ResponseEntity.ok(value)).orElseGet(() -> ResponseEntity.notFound().build());
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
        Categoria categoria = dto.gerarCategoria();
        categoriaRepository.save(categoria);
        URI uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaDto dto) {
        Categoria categoria = dto.update(id, categoriaRepository);
        if (categoria != null) {
            categoriaRepository.save(categoria);
            return ResponseEntity.ok(categoria);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteVideo(@PathVariable Long id, CategoriaDto dto) {
        if (dto.delete(id, categoriaRepository)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
