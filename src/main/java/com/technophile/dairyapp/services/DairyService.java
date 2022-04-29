package com.technophile.dairyapp.services;

import com.technophile.dairyapp.model.DiaryApp;

public interface DairyService {
    DiaryApp createDiary(String title, String id);
}
