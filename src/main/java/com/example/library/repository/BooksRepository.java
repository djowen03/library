package com.example.library.repository;

import com.example.library.model.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {

    Page<Books> findAll(Pageable pageable);
}
