package com.leonardo.rocha.configuration;

import com.leonardo.rocha.api.MarsRoverRestApi;
import com.leonardo.rocha.api.UrlImageDownloader;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class MarsImageAppConfiguration {

    @Bean
    public String apiKey(@Value("${apiKey:cLetoNqdRqUTnurOcI3aCTYvMpwp49AbYrzu2wnZ}") String apiKey) {
        return apiKey;
    }

    @Value("${baseUrl:https://api.nasa.gov}")
    String baseUrl;

//    @Bean
//    public String datesFileName(@Value("${datesFileName:src/test/resources/imageDates.txt}") String datesFileName) {
//        return datesFileName;
//    }
    
    @Bean
    public Resource datesFileName(){
    	return new ClassPathResource("imageDates.txt", this.getClass().getClassLoader());

    }

    @Bean
    public String baseUrl(@Value("${baseUrl:https://api.nasa.gov}") String baseUrl) {
        return baseUrl;
    }

    @Bean
    public String photoDir(@Value("${photoDir:/tmp/photos}") String photoDir) {
        return photoDir;
    }

    @Bean
    public Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    @Bean
    public OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }

    @Bean
    public MarsRoverRestApi getMarsRoverRestApi() {
        return getRetrofit().create(MarsRoverRestApi.class);
    }

    @Bean
    @Primary
    public UrlImageDownloader getUrlImageDownloader(){
        return new UrlImageDownloader();
    }

}
