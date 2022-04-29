package com.technophile.dairyapp.services;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

@EnableAutoConfiguration
@DataMongoTest
@ComponentScan(basePackages ={"com.technophile.dairyapp"})

public class DataConfig {
}
