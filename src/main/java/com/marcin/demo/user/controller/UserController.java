package com.marcin.demo.user.controller;

import com.marcin.demo.model.User;
import com.marcin.demo.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {
    @Autowired private IUserService userService;

    @GetMapping("/all")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping("/add")
    public User createUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/users/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User userRequest) {
        return userService.findById(userId).map(user -> {
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            return userService.save(user);
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }


    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.findById(userId).map(post -> {
            userService.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    /*@RequestMapping(value = "/allJSON", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "/allXML", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<User> findAllXML() {
        return userService.findAll();
    }

    @GetMapping(value = "/{email}")
    public User findByName(@PathVariable final String email) {
        return userService.findByEmail(email).get();
    }

    @PostMapping(value = "/save")
    public User save(@RequestBody final User users) {
        userService.save(users);
        return userService.findByEmail(users.getEmail()).get();
    }


    @RequestMapping(value = "/saveList", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public List<User> saveXMLList(@RequestBody final List<User> users) {
        List<User> usersList = new ArrayList<>();
        for (User user : users) {
            userService.save(user);
            usersList.add(userService.findByEmail(user.getName()).get());
        }
        return usersList;
    }*/
}
