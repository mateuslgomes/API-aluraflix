package br.com.aluraflix.controller.form;

import br.com.aluraflix.model.Usuario;
import br.com.aluraflix.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CreateForm {


    @NotNull @NotEmpty
    private String nome;
    @NotEmpty @NotNull
    private String email;
    @NotEmpty @NotNull
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario gerarUsuario(CreateForm form, UsuarioRepository usuarioRepository) {
        Optional<Usuario> email = usuarioRepository.findByEmail(form.getEmail());
        if (nome != null && email.isEmpty() && senha != null) {
            return new Usuario(nome, this.email, new BCryptPasswordEncoder().encode(senha));
        }
        return null;
    }


}
