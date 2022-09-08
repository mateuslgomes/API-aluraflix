package br.com.aluraflix.interfaces;

import org.springframework.web.util.UriComponentsBuilder;

public interface Metodos {

    public Object save(Object obj, UriComponentsBuilder builder);

    public Object gerar(Object obj);

    public Object update(Long id, Object obj);

    public Object deleteById(Long id);

}
