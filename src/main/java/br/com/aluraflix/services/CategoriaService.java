package br.com.aluraflix.services;

import br.com.aluraflix.dtos.CategoriaDto;
import br.com.aluraflix.interfaces.Metodos;
import br.com.aluraflix.model.Categoria;
import br.com.aluraflix.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CategoriaService implements Metodos {

    public final CategoriaRepository repository;

    @Override
    public ResponseEntity<Categoria> save(Object obj, UriComponentsBuilder builder) {
        Categoria categoria = (Categoria) obj;
        URI uri = builder.path("/categorias/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(categoria);
    }

    @Override
    public Categoria gerar(Object obj) {
        CategoriaDto dto = (CategoriaDto) obj;
        return new Categoria(dto.getTitulo(), dto.getCor());
    }

    @Override
    public ResponseEntity<Categoria> update(Long id, Object obj) {
        CategoriaDto dto = (CategoriaDto) obj;
        if (id == 1) {return null;}
        try  {
            Optional<Categoria> categoriaOptional = repository.findById(id);
            Categoria categoria = categoriaOptional.get();
            categoria.setTitulo(dto.getTitulo());
            categoria.setCor(dto.getCor());
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        if (repository.existsById(id) && id != 1) {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return new ResponseEntity<String>("O ID 1 não pode ser deletado.", HttpStatus.FORBIDDEN);
    }
}
