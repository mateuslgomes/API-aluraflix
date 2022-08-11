package br.com.aluraflix.repository;

import br.com.aluraflix.controller.VideosController;
import br.com.aluraflix.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {}
