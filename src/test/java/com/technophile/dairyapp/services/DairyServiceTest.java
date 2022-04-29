package com.technophile.dairyapp.services;

import com.technophile.dairyapp.dto.UserDTO;
import com.technophile.dairyapp.model.DiaryApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
public class DairyServiceTest {
    @Autowired
    private DairyService dairy_service;


    @Test
    void testThatDairyCanbeCreated() {
        String title = new String("new diary title");
        String id = new String("626be0c33c35ab17ee1a7304");
        DiaryApp diary = dairy_service.createDiary(title, id);
        assertThat(diary.getTitle()).isEqualTo("new diary title");
        assertThat(diary.getOwner().getId()).isEqualTo("626be0c33c35ab17ee1a7304");

    }

}

