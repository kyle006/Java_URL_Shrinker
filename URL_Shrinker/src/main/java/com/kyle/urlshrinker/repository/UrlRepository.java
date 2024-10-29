package com.kyle.urlshrinker.repository;

import com.kyle.urlshrinker.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url, Long> {

    @Query("SELECT u FROM Url u WHERE u.shortUrl = :shortCode")
    Optional<Url> findUrl(@Param("shortCode") String shortCode);
}