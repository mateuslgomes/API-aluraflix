package br.com.aluraflix.controller;

import br.com.aluraflix.form.FormCreate;
import br.com.aluraflix.model.Usuario;
import br.com.aluraflix.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    @PostMapping("cadastrar")
    public ResponseEntity<String> cadastrarUsuario(@RequestBody @Valid FormCreate form) {
        Usuario usuario = form.gerarUsuario(form, usuarioRepository);
        if (usuario != null) {
            usuarioRepository.save(usuario);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Endereço de Email já cadastrado", HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
