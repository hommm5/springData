package com.example.spring_auto_mapping_exr.repositories;

import com.example.spring_auto_mapping_exr.domain.entities.Game;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<Game> findGameById (Long id);
    @Transactional
    void deleteGameById(Long id);
    List<Game> findAll();

}
