package com.example.library.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReturnLoanDTO {

    @Pattern(regexp = "[0-9]+", message = "loanId must be a number")
    @NotEmpty(message = "loanId is required.")
    String loanId;
}
