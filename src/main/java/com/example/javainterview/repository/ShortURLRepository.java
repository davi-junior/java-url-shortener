package com.example.javainterview.repository;

import com.example.javainterview.model.ShortURL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ShortURLRepository extends JpaRepository<ShortURL, Long> {

    @Query(value = "SELECT * " +
            "FROM shortURL s " +
            "WHERE s.originalURL = :url ",
            nativeQuery = true)
    Optional<ShortURL> findByURL(
            @Param("url") String url
    );

    @Query(value = "SELECT * " +
            "FROM shortURL s " +
            "ORDER BY s.number_of_access DESC " +
            "LIMIT 100 ",
            nativeQuery = true)
    List<ShortURL> getTop100();
}
