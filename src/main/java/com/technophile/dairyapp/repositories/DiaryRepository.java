package com.technophile.dairyapp.repositories;

import com.technophile.dairyapp.model.DiaryApp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends MongoRepository<DiaryApp,String> {

}
