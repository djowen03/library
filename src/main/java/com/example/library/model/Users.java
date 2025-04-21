package com.example.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;

    String name;

    Date createdDate;

    Date updateDate;

}
