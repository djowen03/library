package com.example.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "loan")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer loanId;

    Integer userId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable=false, updatable=false)
    Users users;

    Integer bookId;

    @ManyToOne
    @JoinColumn(name = "bookId", insertable=false, updatable=false)
    Books books;

    Date loanDate;

    Date returnDate;

}
