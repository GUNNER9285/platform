package com.training.platform.controllers;

import com.training.platform.entities.User;
import com.training.platform.repositories.UserRepository;

import com.training.platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping(value = "")
    public List<User> index() {
        // Change from UserRepository to UserService
        return userService.findAllByJpqlParamsQuery(0, "bangkok");
    }

//    @GetMapping(value = "")
//    public String showWithParam(@RequestParam String id) {
//        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with query string";
//    }

    @PostMapping(value = "")
    public String create(@RequestBody Map<String,Object> inputs) {
        System.out.println("########### POST Param ###########");
        System.out.println(inputs);

        return "Method POST, Function : create => INSERT data to DB";
    }

    @GetMapping(value = "/{id}")
    public String showWithPath(@PathVariable String id) {
        Optional<User> user = userRepository.findById(Integer.parseInt(id));
        System.out.println("############### Find User By ID (In Console) ###############");
        System.out.println(user);

        return "Method Get, Function : show, ID : "+ id +" => SHOW data by id on page with path";
    }

    @PatchMapping(value = "/{id}")
    public String update(@PathVariable String id, @RequestParam Map<String,String> inputs) {
        System.out.println("########### PATCH Param ###########");
        System.out.println(inputs);

        return "Method PATCH, Function : update => ID : " + id + "UPDATE data to DB";
    }

    @DeleteMapping(value = "/{id}")
    public String destroy(@PathVariable String id)  {
        return "Method DELETE, Function : delete, ID : " + id + " => DELETE data to DB";
    }

    // Example for findAllByQuery
    @GetMapping(value = "/test1")
    public List<User> test1() throws Exception {
        return userRepository.findAllByQuery();
    }

    // Example for findAllByParamsQuery
    @GetMapping(value = "/test2")
    public List<User> test2() throws Exception {
        return userRepository.findAllByParamsQuery(0, "nakornpathom");
    }

    // Example for findAllByJpqlQuery
    @GetMapping(value = "/test3")
    public List<User> test3() throws Exception {
        return userRepository.findAllByJpqlQuery();
    }

    // Example for findAllByJpqlParamsQuery
    @GetMapping(value = "/test4")
    public List<User> test4() throws Exception {
        return userRepository.findAllByJpqlParamsQuery(0, "bangkok");
    }

    @RequestMapping(value = "/healthcheck")
    public String healthCheck(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return "Hello World!";
    }


}

