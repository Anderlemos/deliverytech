package com.deliverytech.delivery_api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração responsável por criar
 * o bean do ModelMapper.
 *
 * O ModelMapper será usado para converter:
 * DTO -> Entity
 * Entity -> DTO
 */
@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}