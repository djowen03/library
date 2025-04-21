package com.example.library.controller;

import com.example.library.dto.PaginationResponse;
import com.example.library.dto.ResponseHandler;
import com.example.library.dto.request.BookRequestDTO;
import com.example.library.model.Books;
import com.example.library.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("")
    public ResponseEntity<Object> getBookList(
            @RequestParam(required = false, value = "page", defaultValue = "0") Integer page,
            @RequestParam(required = false, value = "size", defaultValue = "10") Integer size
    ){
        Pageable pageable = PageRequest.of(page,size);
        Page<Books> data = bookService.getBooks(pageable);
        PaginationResponse paginationResponse = PaginationResponse.builder()
                .page(page+1)
                .size(size)
                .totalData(data.getTotalElements())
                .totalPage(data.getTotalPages())
                .build();

        return ResponseHandler.generateResponseWithPage("Success", HttpStatus.OK, data.getContent(), paginationResponse);
    }

    @PostMapping("add")
    public ResponseEntity<Object> insertBook(@Valid @RequestBody BookRequestDTO request){
        bookService.insertBook(request);

        return ResponseHandler.generateResponse("Success", HttpStatus.OK, "");
    }

}
