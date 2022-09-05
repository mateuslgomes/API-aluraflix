package br.com.aluraflix.form;

import br.com.aluraflix.model.Usuario;
import br.com.aluraflix.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.*;
import java.util.Optional;

@Getter
@Setter
public class FormCreate {

    @NotNull @NotEmpty @NotBlank @Size(min = 3, max = 100)
    private String nome;
    @Email @NotEmpty @NotNull @NotBlank @Size(min = 3, max = 100)
    private String email;

    @Size(min = 8, max = 100)
    @NotEmpty @NotNull @NotBlank
    private String senha;

    public Usuario gerarUsuario(FormCreate form, UsuarioRepository usuarioRepository) {
        Optional<Usuario> email = usuarioRepository.findByEmail(form.getEmail());
        if (nome != null && email.isEmpty() && senha != null) {
            return new Usuario(nome, this.email, new BCryptPasswordEncoder().encode(senha));
        }
        return null;
    }

}
