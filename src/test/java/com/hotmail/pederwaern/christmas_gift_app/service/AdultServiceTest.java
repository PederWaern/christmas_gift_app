package com.hotmail.pederwaern.christmas_gift_app.service;

import com.hotmail.pederwaern.christmas_gift_app.DockerBackendApplication;
import com.hotmail.pederwaern.christmas_gift_app.domain.Adult;
import com.hotmail.pederwaern.christmas_gift_app.exception.ControllerExceptionHandler;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DockerBackendApplication.class)
public class AdultServiceTest {

    @Autowired
    private AdultService adultService;

    @Before
    public void setUp(){

    }

    @Test(expected = ControllerExceptionHandler.InvalidRequestBody.class)
    public void saveAdult_nullInputThrowsException() throws Exception {
        Adult adult = new Adult();
        adult.setFirstName(null);
        adultService.saveAdult(adult);
    }



}