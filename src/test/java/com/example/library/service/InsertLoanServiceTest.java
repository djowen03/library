package com.example.library.service;

import com.example.library.dto.request.LoanRequestDTO;
import com.example.library.exception.CustomValidationException;
import com.example.library.model.Books;
import com.example.library.model.Users;
import com.example.library.repository.BooksRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

public class InsertLoanServiceTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private BooksRepository booksRepository;
    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanService loanService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void insertLoanTestSuccess(){
        LoanRequestDTO successRequest = LoanRequestDTO.builder()
                .userId("1")
                .bookId("1")
                .loanDate("2025-03-14")
                .build();

        Users mockUser = new Users();
        Books mockBook = new Books();

        when(usersRepository.findById(1)).thenReturn(Optional.of(mockUser));
        when(booksRepository.findById(1)).thenReturn(Optional.of(mockBook));

        loanService.insertLoan(successRequest);

        verify(loanRepository, times(1)).save(argThat(loan ->
                loan.getUserId() == 1 &&
                        loan.getBookId() == 1
        ));

    }

    @Test
    public void insertLoanTestInvalidDate() {

        LoanRequestDTO invalidDateRequest = LoanRequestDTO.builder()
                .userId("1")
                .bookId("1")
                .loanDate("14-03-2025")
                .build();

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> {
            loanService.insertLoan(invalidDateRequest);
        });

        assertEquals("Not a Date Format (yyyy-mm-dd)", exception.getMessage());
    }

    @Test
    public void insertLoanTestInvalidUser() {

        LoanRequestDTO userNotFoundRequest = LoanRequestDTO.builder()
                .userId("10")
                .bookId("1")
                .loanDate("2025-03-14")
                .build();
        when(usersRepository.findById(10)).thenReturn(Optional.empty());

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> {
            loanService.insertLoan(userNotFoundRequest);
        });

        assertEquals("User Not Found", exception.getMessage());
    }

    @Test
    public void insertLoanTestInvalidBook() {

        LoanRequestDTO bookNotFoundRequest = LoanRequestDTO.builder()
                .userId("1")
                .bookId("10")
                .loanDate("2025-03-14")
                .build();

        Users mockUser = new Users();
        when(usersRepository.findById(1)).thenReturn(Optional.of(mockUser));

        when(booksRepository.findById(999)).thenReturn(Optional.empty());

        CustomValidationException exception = assertThrows(CustomValidationException.class, () -> {
            loanService.insertLoan(bookNotFoundRequest);
        });

        assertEquals("Book Not Found", exception.getMessage());
    }
}
