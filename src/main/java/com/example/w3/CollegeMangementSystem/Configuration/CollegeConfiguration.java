package com.example.w3.CollegeMangementSystem.Configuration;


import com.example.w3.CollegeMangementSystem.auth.AuditorAwareImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="getAuditorAwareImp")
public class CollegeConfiguration {

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

    @Bean
    AuditorAware<String> getAuditorAwareImp(){
        return new AuditorAwareImpl();
    }

}
