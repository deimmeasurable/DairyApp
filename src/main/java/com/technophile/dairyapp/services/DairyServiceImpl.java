package com.technophile.dairyapp.services;

import com.technophile.dairyapp.exceptions.DiaryAppException;
import com.technophile.dairyapp.model.DiaryApp;
import com.technophile.dairyapp.model.User;
import com.technophile.dairyapp.repositories.DiaryRepository;
import com.technophile.dairyapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DairyServiceImpl implements DairyService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    @Override
    public DiaryApp createDiary(String title, String id) {
        User user = userRepository.findUserById(id).orElseThrow(()->new DiaryAppException("user not found"));
        DiaryApp dairy_app=new DiaryApp(title, user);


        return diaryRepository.save(dairy_app);
    }
}
