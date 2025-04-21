package com.example.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoanRequestDTO {

    @Pattern(regexp = "[0-9]+", message = "userId must be a number")
    @NotEmpty(message = "userId is required.")
    String userId;


    @Pattern(regexp = "[0-9]+", message = "bookId must be a number")
    @NotEmpty(message = "bookId is required.")
    String bookId;

    @NotEmpty(message = "loanDate is required.")
    String loanDate;
}
