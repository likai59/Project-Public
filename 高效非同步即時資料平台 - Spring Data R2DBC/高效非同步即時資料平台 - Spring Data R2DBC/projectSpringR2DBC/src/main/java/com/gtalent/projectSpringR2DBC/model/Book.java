package com.gtalent.projectSpringR2DBC.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("books")
public class Book {
    @Id
    private int id;
    private String title;
    private String description;


    public Book(String title, String description) {
        this.title = title;
        this.description = description;
    }


}
