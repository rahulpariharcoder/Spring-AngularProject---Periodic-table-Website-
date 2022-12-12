package com.example.demo;

import com.example.demo.Domain.Element;
import com.example.demo.Domain.ErrorField;
import com.example.demo.Service.ElementService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.junit.Assert;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
class DemoApplicationTests {

    @Autowired
    ElementService elementService;

	@Test
	void contextLoads() {
	}


    @Test
    public void convertorTest() {
        Element a = new Element(1,"1.008","h","Hydrogen");
        Element b = new Element(1,"1.008","H","Hydrogen");
        Assert.assertEquals(b,elementService.convertSymbol(a));
    }


    @Test
    public void atomicNumberTest() {
        Element a = new Element(1,"1.008","H","Hydrogen");

        Assert.assertEquals(new ResponseEntity<>(new ErrorField("Atomic Number Already Exists"),HttpStatus.CONFLICT), elementService.checkElement(a));

    }
    @Test
    public void atomicWeightTest() {
        Element a = new Element(69,"1.008","H","Hydrogen");

        Assert.assertEquals(new ResponseEntity<>(new ErrorField("Atomic Weight Already Exists"),HttpStatus.CONFLICT), elementService.checkElement(a));

    }

    @Test
    public void atomicSymbolTest() {
        Element a = new Element(69,"1.038","H","Hydrogen");

        Assert.assertEquals(new ResponseEntity<>(new ErrorField("Atomic Symbol Already Exists"),HttpStatus.CONFLICT), elementService.checkElement(a));

    }

    @Test
    public void atomicNameTest() {
        Element a = new Element(69,"1.038","Hp","Hydrogen");

        Assert.assertEquals(new ResponseEntity<>(new ErrorField("Atomic Name Already Exists"),HttpStatus.CONFLICT), elementService.checkElement(a));

    }

    @Test
    public void request2Test() {
//        Element b = new Element(33,2.1081,"hr","Hygdra");
//        Element a = new Element(33,2.1081,"Hr","Hygdra");
        Element b = new Element(38,"2.9081","hk","Hyygdra");
        Element a = new Element(38,"2.9081","Hk","Hyygdra");
        Assert.assertEquals(new ResponseEntity<>(a,HttpStatus.CREATED),elementService.checkElement(b));
    }
}
