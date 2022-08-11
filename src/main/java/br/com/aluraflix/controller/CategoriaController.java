package br.com.aluraflix.controller;

import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @RequestMapping
    public List<Categoria> videos() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias;
    }

}
