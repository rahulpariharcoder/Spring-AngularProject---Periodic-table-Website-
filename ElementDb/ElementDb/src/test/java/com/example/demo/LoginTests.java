package com.example.demo;

import com.example.demo.Domain.ErrorField;
import com.example.demo.Domain.LoginPage;
import com.example.demo.Domain.SignUpPage;
import com.example.demo.Service.ElementService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginTests
{
    @Autowired
    ElementService elementService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void login1Test() throws NoSuchAlgorithmException
    {
        LoginPage a = new LoginPage("Jakhar", "Aman");
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.OK),elementService.getExists(a));
    }

    @Test
    public void login2Test() throws NoSuchAlgorithmException
    {
        LoginPage a = new LoginPage("Jakhar","dsjv");
        Assert.assertEquals(new ResponseEntity<>(new ErrorField("Incorrect Password"),HttpStatus.BAD_REQUEST),elementService.getExists(a));
    }

    @Test
    public void login3Test() throws NoSuchAlgorithmException
    {
        LoginPage a = new LoginPage("Adbvkjdf","dsjv");
        Assert.assertEquals(new ResponseEntity<>(new ErrorField("Please Register before Login"),HttpStatus.NOT_FOUND),elementService.getExists(a));
    }
}
