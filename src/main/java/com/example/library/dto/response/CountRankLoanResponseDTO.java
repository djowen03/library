package com.example.library.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountRankLoanResponseDTO {

    String bookName;
    Integer loanCount;
    Integer rankLoanBook;

}
