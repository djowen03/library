package com.example.library.service;

import com.example.library.model.Books;
import com.example.library.model.Loan;
import com.example.library.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GetLoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    private Pageable pageable;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void getLoanTestSuccess(){
        ArrayList<Loan> loanArrayList = new ArrayList<>();
        loanArrayList.add(Loan.builder()
                .bookId(1)
                .loanId(1)
                .loanDate(Date.valueOf(LocalDate.now()))
                .build());

        Page<Loan> page = new PageImpl<>(loanArrayList,pageable,loanArrayList.size());

        when(loanRepository.findAll(pageable)).thenReturn(page);

        Page<Loan> result = loanService.getLoan(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertTrue(result.getContent().size() > 0);
        assertEquals(1, result.getContent().get(0).getBookId());
    }


}
