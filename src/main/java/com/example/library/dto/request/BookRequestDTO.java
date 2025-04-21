package com.example.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequestDTO {

    @NotEmpty(message = "bookName is required.")
    String bookName;

    @NotEmpty(message = "author is required.")
    String author;

    @NotEmpty(message = "publicationYear is required.")
    @Pattern(regexp = "[0-9]+", message = "publicationYear must be a number")
    @Size(min = 4, max = 4, message = "publicationYear need to be 4 digit")
    String publicationYear;

}
