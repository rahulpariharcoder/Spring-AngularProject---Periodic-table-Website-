package com.example.demo.Service;

import com.example.demo.Domain.Element;
import com.example.demo.Domain.ErrorField;
import com.example.demo.Domain.LoginPage;
import com.example.demo.Domain.SignUpPage;
import com.example.demo.Persistence.ElementRepository;
import com.example.demo.Persistence.SignUpPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ElementService
{
    @Autowired
    ElementRepository elementRepository;

    @Autowired
    SignUpPageRepository signUpPageRepository;

    public Set<Element> getAll()
    {
        return elementRepository.findAll(Sort.by("atomicNumber"));
    }

    public ResponseEntity<?> saveSingleElement(Element element)
    {
        Set<Element> elementSet = getAll();
        if (elementSet.isEmpty())
        {
//            getFormat(element);
            elementRepository.save(convertSymbol(element));
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
//        Set<Element> elementSet1 = getAll();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//    public Element getFormat(Element element) {
//        DecimalFormat df = new DecimalFormat("#.####");
//        Double weight = Double.valueOf(df.format(element.getWeight()));
//        element.setWeight(weight);
//        return element;
//    }
    public ResponseEntity<?> checkElement(Element element)
    {
        if(saveSingleElement(element).equals(new ResponseEntity<>(HttpStatus.CREATED))) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        Set<Element> elementSet = getAll();
        ResponseEntity<?> x1 = null;
        for (Element elements : elementSet)
        {
            if (elements.getAtomicNumber() == element.getAtomicNumber())
            {
                return new ResponseEntity<>(new ErrorField("Atomic Number Already Exists"), HttpStatus.CONFLICT);
            }

            else if (elements.getWeight().equals(element.getWeight()))
            {
                return new ResponseEntity<>(new ErrorField("Atomic Weight Already Exists"), HttpStatus.CONFLICT);
            }
            else if (elements.getSymbol().equals(element.getSymbol()))
            {
                return new ResponseEntity<>(new ErrorField("Atomic Symbol Already Exists"), HttpStatus.CONFLICT);
            }
            else if (elements.getName().equals(element.getName()))
            {
                return new ResponseEntity<>(new ErrorField("Atomic Name Already Exists"), HttpStatus.CONFLICT);
            }
        }
//        getFormat(element);
        return new ResponseEntity<>(elementRepository.save(convertSymbol(element)), HttpStatus.CREATED);
    }
    public Element convertSymbol(Element element)
    {
        String converted = element.getSymbol().substring(0, 1).toUpperCase() + element.getSymbol().substring(1).toLowerCase();
        return new Element(element.getAtomicNumber(), element.getWeight(), converted, element.getName());
    }
    public ResponseEntity<?> getRegistration(SignUpPage details) throws NoSuchAlgorithmException
    {
        final Optional<SignUpPage> element = signUpPageRepository.findByUserName(details.getUserName());
        details.setPassword(getSHA256(details.getPassword()));
        if (element.isPresent())
        {
            return new ResponseEntity<>(new ErrorField("UserName already Exists"), HttpStatus.CONFLICT);}
        signUpPageRepository.save(details);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    public String getSHA256(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(input.getBytes());
        byte[] sha256 = md.digest();

        StringBuffer str = new StringBuffer();

        for(byte b : sha256){
            str.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return str.toString();
    }

    public ResponseEntity<?> getExists(LoginPage loginPage) throws NoSuchAlgorithmException
    {
        loginPage.setPassword(getSHA256(loginPage.getPassword()));
        if (signUpPageRepository.findByUserName(loginPage.getUserName()).isPresent())
        {
            if (loginPage.getPassword().equals(signUpPageRepository.findByUserName(loginPage.getUserName()).get().getPassword()))
            {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(new ErrorField("Incorrect Password"), HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(new ErrorField("Please Register before Login"), HttpStatus.NOT_FOUND);

    }
}

