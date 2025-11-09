package com.gtalent.projectSpringJpa.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books") // 預設 model 名
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//IDENTITY - 都使用同一語言, AUIO - 自動轉換
    private int id;


    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 100)
    private String description;



}
