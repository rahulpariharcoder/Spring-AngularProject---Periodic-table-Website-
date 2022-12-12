package com.example.demo.Controller;

import com.example.demo.Domain.Element;
import com.example.demo.Domain.LoginPage;
import com.example.demo.Domain.SignUpPage;
import com.example.demo.Persistence.SignUpPageRepository;
import com.example.demo.Service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

@RequestMapping
@RestController
@CrossOrigin(origins="*")
public class ControllerElement
{
    @Autowired
    ElementService elementService;
    @Autowired
    SignUpPageRepository signUpPageRepository;

    @PostMapping("/post")
    public ResponseEntity<?> checkElement(@Valid @RequestBody Element element)
    {
            return elementService.checkElement(element);
    }

    @GetMapping("/all")
    public Set<Element> getAll()
    {
        return elementService.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> getRegistration(@Valid @RequestBody SignUpPage details) throws NoSuchAlgorithmException
    {
        return elementService.getRegistration(details);
    }

    @PostMapping("/login")
    public ResponseEntity<?> getLogin(@Valid @RequestBody LoginPage loginPage) throws NoSuchAlgorithmException
    {
        return elementService.getExists(loginPage);
    }

    @GetMapping("/sign")
    public Set<SignUpPage> getAll1() {
        return signUpPageRepository.findAll(Sort.by("userName"));
    }


}

















    //    @PostMapping("/post")
//    public ResponseEntity<?> checkElement(@RequestBody Element element)
//    {
//        Optional<Element> element1 = elementService.findByAtomicNumber(element);
//        Optional<Element> element2 = elementService.findByWeight(element);
//        Optional<Element> element3 = elementService.findBySymbol(element);
//        Optional<Element> element4 = elementService.findByName(element);
//        if(element1.isPresent() || element2.isPresent() || element3.isPresent() || element4.isPresent()){
//
//                return ResponseEntity<?>(HttpStatus.BAD_REQUEST);
//
//        }
//
//        return new ResponseEntity<>(elementService.saveElement(element),HttpStatus.OK);
//
//
//
//    }

//
//    @GetMapping("/count")
//    public Long getCount() {
//        return elementService.countAll();
//    }
//}
