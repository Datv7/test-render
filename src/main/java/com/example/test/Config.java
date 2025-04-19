package com.example.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
@Configuration

public class Config {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) 
	        .csrf(t->t.disable()); 
	    return http.build();
	}
	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration=new CorsConfiguration();
		configuration.setAllowCredentials(true);  // Cho phép gửi cookies và thông tin xác thực
        configuration.addAllowedOrigin("http://192.168.1.20:5173");
        configuration.addAllowedOrigin("http://127.0.0.1:5502");
        configuration.addAllowedMethod("*");  // Cho phép tất cả các phương thức HTTP
        configuration.addAllowedHeader("*");  // Cho phép tất cả các headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);  // Áp dụng CORS cho tất cả các endpoint
        return source;
	}
	@Bean
	public CorsFilter corsFilter(CorsConfigurationSource configurationSource) {
		return new CorsFilter(configurationSource);
	}
	@Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "df0yq3rqf",
            "api_key", "643259922891475",
            "api_secret", "8j55rf_L5SAXaXFmgnYBS04sEjM",
            "secure", true // Bắt buộc để sinh HTTPS
        ));
    }
}
