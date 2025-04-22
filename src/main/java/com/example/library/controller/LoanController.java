package com.example.library.controller;

import com.example.library.dto.PaginationResponse;
import com.example.library.dto.ResponseHandler;
import com.example.library.dto.request.LoanRequestDTO;
import com.example.library.dto.response.CountRankLoanResponseDTO;
import com.example.library.model.Loan;
import com.example.library.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping("")
    public ResponseEntity<Object> getLoanList(
            @RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
            @RequestParam(required = false, value = "size", defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Loan> data = loanService.getLoan(pageable);
        PaginationResponse paginationResponse = PaginationResponse.builder()
                .page(page+1)
                .size(size)
                .totalData(data.getTotalElements())
                .totalPage(data.getTotalPages())
                .build();

        return ResponseHandler.generateResponseWithPage("Success", HttpStatus.OK, data.getContent(), paginationResponse);
    }

    @PostMapping("add")
    public ResponseEntity<Object> insertLoan(@Valid @RequestBody LoanRequestDTO request){
        loanService.insertLoan(request);

        return ResponseHandler.generateResponse("Success", HttpStatus.OK, "");
    }

    @GetMapping("count-rank")
    public ResponseEntity<Object> getLoanBook(){
        List<CountRankLoanResponseDTO> countRankLoanResponseDTO = loanService.getLoanCountRank();

        return ResponseHandler.generateResponse("Success", HttpStatus.OK, countRankLoanResponseDTO);
    }
}
