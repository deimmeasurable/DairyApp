package com.technophile.dairyapp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Document
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    private String id;
    @NotNull @NotBlank
    private  String email;
    @NotNull @NotBlank
    private  String password;
    @DBRef
    private Set<DiaryApp> dairyApp;

    public  User( String email, String password, String id) {
        this.email = email;
        this.password = password;
        this.id = id;
        dairyApp= new HashSet<>();
    }

    @Override
    public String toString() {
        return String.format("UserId: %s \n Email: %s",id,email);
    }
}
