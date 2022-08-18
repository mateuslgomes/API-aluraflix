package br.com.aluraflix.controller;

import br.com.aluraflix.controller.form.CreateForm;
import br.com.aluraflix.model.Usuario;
import br.com.aluraflix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid CreateForm form) {
        Usuario usuario = form.gerarUsuario(form, usuarioRepository);
        if (usuario != null) {
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Endereço de Email já cadastrado", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
