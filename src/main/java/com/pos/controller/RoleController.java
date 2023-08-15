package com.pos.controller;

import com.pos.dto.master.MstRoleDTO;
import com.pos.repository.RoleRepository;
import com.pos.service.JwtService;
import com.pos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveItem(@RequestHeader Map<String,String> header, @RequestBody MstRoleDTO request)
    {
        jwtService.filter(header);
        return roleService.saveRole(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){
        jwtService.filter(header);
        return roleService.getAll();
    }

    @DeleteMapping("/delete/{roleId}")
    public ResponseEntity<?> delete(@RequestHeader Map<String,String> header, @PathVariable Long roleId){
        jwtService.filter(header);
        return roleService.delete(roleId);
    }

    @GetMapping("/get_by/{roleId}")
    public ResponseEntity<?> getById(@RequestHeader Map<String,String> header, @PathVariable Long roleId){
        jwtService.filter(header);
        return roleService.getById(roleId);
    }

}
