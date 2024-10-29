package com.kyle.urlshrinker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private boolean active;
    private LocalDateTime lastAccessed;
    private int usageCount;

    // Lombok handles the getters, setters and constructors
}
