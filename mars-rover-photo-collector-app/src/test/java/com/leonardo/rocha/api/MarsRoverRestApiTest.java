package com.leonardo.rocha.api;

import com.leonardo.rocha.AutoValue.RoverList;
import com.leonardo.rocha.configuration.MarsImageAppConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import retrofit2.Call;
import retrofit2.Retrofit;

import java.io.IOException;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = MarsImageAppConfiguration.class)
public class MarsRoverRestApiTest {
    @Value("#{apiKey}")
    private String apiKey;

    @Autowired
    Retrofit retrofit;

    @Autowired
    MarsRoverRestApi marsRoverRestApi;

    @Before
    public void setUp() throws Exception {
        marsRoverRestApi = retrofit.create(MarsRoverRestApi.class);
    }

    @After
    public void tearDown() throws Exception {
        marsRoverRestApi = null;
    }

    @Test
    public void getRoverList() throws IOException {
        Call<RoverList> call = marsRoverRestApi.getRoverList(apiKey);
        System.out.println(call.execute().body());
    }

    //@Test
    public void getPhotoList() {
    }
}