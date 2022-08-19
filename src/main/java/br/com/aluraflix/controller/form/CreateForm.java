package br.com.aluraflix.controller.form;

import br.com.aluraflix.model.Usuario;
import br.com.aluraflix.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class CreateForm {

    @NotNull @NotEmpty @NotBlank @Size(min = 3, max = 55)
    private String nome;
    @NotEmpty @NotNull @NotBlank @Size(min = 3, max = 55)
    private String email;

    @Size(min = 8, max = 100)
    @NotEmpty @NotNull @NotBlank
    private String senha;

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return this.email;
    }

    public Usuario gerarUsuario(CreateForm form, UsuarioRepository usuarioRepository) {
        Optional<Usuario> email = usuarioRepository.findByEmail(form.getEmail());
        if (nome != null && email.isEmpty() && senha != null) {
            return new Usuario(nome, this.email, new BCryptPasswordEncoder().encode(senha));
        }
        return null;
    }


}
