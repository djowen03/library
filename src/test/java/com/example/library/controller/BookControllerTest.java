package com.example.library.controller;

import com.example.library.dto.PaginationResponse;
import com.example.library.dto.request.BookRequestDTO;
import com.example.library.model.Books;
import com.example.library.service.BookService;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Pageable pageable;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void getBookControllerTestSuccess(){

        Books book1 = Books.builder()
                .bookName("TestBook1")
                .author("TestAuthor1")
                .publicationYear("2022")
                .build();
        Books book2 = Books.builder()
                .bookName("TestBook2")
                .author("TestAuthor2")
                .publicationYear("2023")
                .build();

        Page<Books> page = new PageImpl<>(Arrays.asList(book1, book2));;

        when(bookService.getBooks(pageable)).thenReturn(page);

        ResponseEntity<Object> response = bookController.getBookList(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void insertBookControllerTestSuccess(){
        BookRequestDTO validRequest = BookRequestDTO.builder()
                .bookName("Test Book")
                .author("Test")
                .publicationYear("2022")
                .build();

        doNothing().when(bookService).insertBook(validRequest);

        ResponseEntity<Object> response = bookController.insertBook(validRequest);

        verify(bookService, times(1)).insertBook(validRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

}
