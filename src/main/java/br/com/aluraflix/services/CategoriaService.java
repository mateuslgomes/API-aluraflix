package br.com.aluraflix.services;

import br.com.aluraflix.dtos.CategoriaDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.repository.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoriaService {

    public static Categoria gerarCategoria(CategoriaDto dto) {
        return new Categoria(dto.getTitulo(), dto.getCor());
    }

    public static ResponseEntity<Categoria> update(Long id, CategoriaRepository categoriaRepository, CategoriaDto dto) {
        if (id == 1) {return null;}
        try  {
            Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
            Categoria categoria = categoriaOptional.get();
            categoria.setTitulo(dto.getTitulo());
            categoria.setCor(dto.getCor());
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public static ResponseEntity<String> delete(Long id, CategoriaRepository categoriaRepository, CategoriaDto dto) {
        if (categoriaRepository.existsById(id) && id != 1) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<String>("O ID 1 não pode ser deletado.", HttpStatus.FORBIDDEN);
    }

}
