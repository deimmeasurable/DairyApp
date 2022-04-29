package com.technophile.dairyapp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@Document

@NoArgsConstructor

public class DiaryApp {
    @Id
    private String id;
    private String title;
    private LocalDateTime createdTime;
    @DBRef
    private User owner;

    public DiaryApp( String title, User owner) {
        this.owner = owner;
        this.createdTime =LocalDateTime.now();
        this.title=title;
    }
}
