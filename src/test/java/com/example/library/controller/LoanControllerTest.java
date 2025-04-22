package com.example.library.controller;

import com.example.library.dto.request.LoanRequestDTO;
import com.example.library.model.Loan;
import com.example.library.service.LoanService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class LoanControllerTest {

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void getLoanControllerTestSuccess(){

        Loan loan = Loan.builder()
                .bookId(1)
                .loanId(1)
                .loanDate(Date.valueOf(LocalDate.now()))
                .build();

        Page<Loan> page = new PageImpl<>(Arrays.asList(loan));

        when(loanService.getLoan(pageable)).thenReturn(page);

        ResponseEntity<Object> response = loanController.getLoanList(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void insertLoanControllerTestSuccess(){
        LoanRequestDTO validRequest = LoanRequestDTO.builder()
                .bookId("1")
                .userId("1")
                .loanDate("2024-03-13")
                .build();

        doNothing().when(loanService).insertLoan(validRequest);

        ResponseEntity<Object> response = loanController.insertLoan(validRequest);

        verify(loanService, times(1)).insertLoan(validRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
