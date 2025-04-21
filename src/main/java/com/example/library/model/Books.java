package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bookId;

    String bookName;

    String author;

    String publicationYear;

    Date createdDate;

    Date updatedDate;
}
