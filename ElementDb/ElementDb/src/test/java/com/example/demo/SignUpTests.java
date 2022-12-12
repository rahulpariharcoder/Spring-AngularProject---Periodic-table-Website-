package com.example.demo;

import com.example.demo.Domain.ErrorField;
import com.example.demo.Domain.SignUpPage;
import com.example.demo.Persistence.SignUpPageRepository;
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
public class SignUpTests
{
    @Autowired
    ElementService elementService;
    @Autowired
    SignUpPageRepository signUpPageRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void registrationTest1() throws NoSuchAlgorithmException
    {
        SignUpPage a = new SignUpPage("Jakhar","Aman");
        Assert.assertEquals(new ResponseEntity<>(HttpStatus.CREATED),elementService.getRegistration(a));
    }

    @Test
    public void registrationTest2() throws NoSuchAlgorithmException
    {
        SignUpPage a = new SignUpPage("Jakhar","Aman");
        Assert.assertEquals(new ResponseEntity<>(new ErrorField("UserName already Exists"),HttpStatus.CONFLICT), elementService.getRegistration(a));
    }


}
