package com.example.demo;

import com.example.demo.Domain.Element;
import com.example.demo.Service.ElementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class saveElementTests
{
    @Autowired
    ElementService elementService;

    @Test
    public void contextLoads() {
    }
    @Test
    public void emptyTest() {
        Element a = new Element(1, "1.008", "h", "Hydrogen");
        Element b = new Element(1, "1.008", "H", "Hydrogen");

        Assert.assertEquals(new ResponseEntity<>(HttpStatus.CREATED), elementService.checkElement(a));
    }
}
