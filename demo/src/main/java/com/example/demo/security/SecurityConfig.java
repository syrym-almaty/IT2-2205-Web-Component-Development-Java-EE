package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Настройка CORS
//                .authorizeHttpRequests(authz -> authz
//                        .requestMatchers("/api/students/**").hasRole("ADMIN") // Ограничение доступа для администраторов
//                        .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
//                )
//                ; // Используйте formLogin() для настройки аутентификации с формой входа
//
//        return http.build();
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080")); // Укажите допустимые источники
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH")); // Укажите допустимые методы
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Укажите допустимые заголовки
//        configuration.setAllowCredentials(true); // Позволяет отправлять учетные данные
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration); // Примените конфигурацию ко всем запросам
//
//        return source;
//    }
}
