package br.com.aluraflix.repository;

import br.com.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    @Query(value = "SELECT * FROM videos v WHERE v.categoria = :id", nativeQuery = true)
    List<Video> findByCategoria(Long id);

    @Query(value = "SELECT * FROM videos v WHERE v.titulo LIKE :titulo%", nativeQuery = true)
    List<Video> findByTitulo(String titulo);
}
