package br.com.aluraflix.controller;

import br.com.aluraflix.dtos.CategoriaDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.services.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaService service;

    @RequestMapping
    public ResponseEntity<List<Categoria>> categorias() {
        return service.findAll();
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<Categoria> categoria(@PathVariable Long id) {
        return service.findById(id);
    }

    @RequestMapping(path = "/{id}/videos")
    public ResponseEntity<List<Video>> findByCategoria(@PathVariable Long id) {
        return service.findByCategoria(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Categoria save(@RequestBody @Valid CategoriaDto dto) {
        return service.save(dto);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody @Valid CategoriaDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, CategoriaDto dto) {
        return service.deleteById(id);
    }
}
