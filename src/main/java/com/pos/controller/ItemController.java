package com.pos.controller;

import com.pos.dto.master.MstItemDTO;
import com.pos.repository.ItemRepository;
import com.pos.service.ItemService;
import com.pos.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepo;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> saveItem(@RequestHeader Map<String,String> header, @RequestBody MstItemDTO request)
    {
        jwtService.filter(header);
        return itemService.saveItem(request);
    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(@RequestHeader Map<String,String> header){
        jwtService.filter(header);
        return itemService.getAll();
    }

    @DeleteMapping("/delete/{itemId}")
    public ResponseEntity<?> delete(@RequestHeader Map<String,String> header, @PathVariable Long itemId){
        jwtService.filter(header);
        return itemService.delete(itemId);
    }

    @GetMapping("/get_by/{itemId}")
    public ResponseEntity<?> getById(@RequestHeader Map<String,String> header, @PathVariable Long itemId){
        jwtService.filter(header);
        return itemService.getById(itemId);
    }

}
