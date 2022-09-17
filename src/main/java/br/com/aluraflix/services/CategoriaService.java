package br.com.aluraflix.services;

import br.com.aluraflix.dtos.CategoriaDto;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.model.Video;
import br.com.aluraflix.repository.CategoriaRepository;
import br.com.aluraflix.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoriaService{

    public CategoriaRepository repository;
    public VideoRepository videoRepository;

    public Categoria save(CategoriaDto dto) {
        return repository.save(new Categoria(dto.getTitulo(), dto.getCor()));
    }

    public ResponseEntity<Categoria> update(Long id, CategoriaDto dto) {
        if (repository.existsById(id) && id!=1) {
            Categoria categoria = repository.findById(id).get();
            categoria.setTitulo(dto.getTitulo());
            categoria.setCor(dto.getCor());
            return ResponseEntity.ok(repository.save(categoria));
        }
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<String> deleteById(Long id) {
        if (repository.existsById(id)) {
            if (id != 1) {
                repository.deleteById(id);
                return ResponseEntity.ok().build();
            }
        }
        return new ResponseEntity<String>("O ID 1 não pode ser deletado.", HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    public ResponseEntity<Categoria> findById(Long id) {
        if (repository.existsById(id)) {
            return ResponseEntity.ok(repository.findById(id).get());
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity<List<Video>> findByCategoria(Long id) {
       return ResponseEntity.ok(videoRepository.findByCategoria(id));
    }
}
