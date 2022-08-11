package br.com.aluraflix.controller;

import br.com.aluraflix.controller.dto.CategoriaDto;
import br.com.aluraflix.controller.dto.VideoDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping
    public List<Categoria> categorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

    @RequestMapping(path = "/{id}")
    public ResponseEntity<CategoriaDto> categoria(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (!categoria.isEmpty()) {
            return ResponseEntity.ok(new CategoriaDto(categoria.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
