package com.pos.controller;

import com.pos.dto.master.MstUserDTO;
import com.pos.repository.UserRepository;
import com.pos.service.JwtService;
import com.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@RequestHeader Map<String,String> header, @RequestBody MstUserDTO request)
    {
        jwtService.filter(header);
        return userService.saveUser(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){
        jwtService.filter(header);
        return userService.getAll();
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> delete(@RequestHeader Map<String,String> header, @PathVariable Long userId){
        jwtService.filter(header);
        return userService.delete(userId);
    }

    @GetMapping("/get_by/{userId}")
    public ResponseEntity<?> getById(@RequestHeader Map<String,String> header, @PathVariable Long userId){
        jwtService.filter(header);
        return userService.getById(userId);
    }

}
