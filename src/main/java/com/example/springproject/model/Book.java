package com.example.springproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Author cannot be null")
    @NotEmpty(message = "Author cannot be empty")
    private String author;
    private boolean isAvailable;
    @JsonBackReference
    @ManyToOne
    private User user;


}
