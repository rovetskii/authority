package com.authority.demo.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = {"controllers", "services", "repositories"})
@EnableTransactionManagement
public class ApplicationConfig  {

}
