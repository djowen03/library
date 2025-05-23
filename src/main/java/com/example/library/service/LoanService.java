package com.example.library.service;

import com.example.library.dto.request.LoanRequestDTO;
import com.example.library.dto.response.CountRankLoanResponseDTO;
import com.example.library.exception.CustomValidationException;
import com.example.library.model.Loan;
import com.example.library.repository.BooksRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    UsersRepository usersRepository;

    public Page<Loan> getLoan(Pageable pageable){
        Page<Loan> loanList = loanRepository.findAll(pageable);
        return loanList;
    }

    public void insertLoan(LoanRequestDTO request){

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate loanDate;
        try {
            loanDate = LocalDate.parse(request.getLoanDate(),dtf);
        }
        catch (Exception ex){
            throw new CustomValidationException("Not a Date Format (yyyy-mm-dd)");
        }

        usersRepository.findById(Integer.parseInt(request.getUserId()))
                .orElseThrow(() -> new CustomValidationException("User Not Found"));

        booksRepository.findById(Integer.parseInt(request.getBookId()))
                .orElseThrow(() -> new CustomValidationException("Book Not Found"));


        Loan loan = Loan.builder()
                .userId(Integer.parseInt(request.getUserId()))
                .bookId(Integer.parseInt(request.getBookId()))
                .loanDate(Date.valueOf(loanDate))
                .build();

        loanRepository.save(loan);
    }

    public List<CountRankLoanResponseDTO> getLoanCountRank(){
        List<CountRankLoanResponseDTO> countRankLoanResponseDTO = loanRepository.getLoanWithFavoriteAndRank();

        return countRankLoanResponseDTO;
    }

    public void returnLoan(Integer loanId){

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new CustomValidationException("Loan Not Found"));

        if (loan.getReturnDate() == null) {
            LocalDate localDate = LocalDate.now();
            loan.setReturnDate(Date.valueOf(localDate));
            loanRepository.save(loan);
        }
        else{
            System.out.println("Loan id: " + loanId + " already return the book");
        }
    }
}
