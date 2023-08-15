package com.pos.service;

import com.google.gson.Gson;
import com.pos.exception.JwtException;
import com.pos.exception.NotFoundRole;
import com.pos.dto.master.RoleJwtDTO;
import com.pos.dto.master.UserJwtDTO;
import com.pos.model.MstRole;
import com.pos.model.MstUser;
import com.pos.repository.RoleRepository;
import com.pos.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class JwtService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    public UserJwtDTO filter(Map<String,String> header){
        // log.info(String.format("%s",new Gson().toJson(header)));
        String bearer = header.get("authorization");
        String split[] = bearer.split(" ");
        bearer = split[1];
        log.info(bearer);
        MstUser user = userRepo.findByToken(bearer);
        if(user==null){
            throw new JwtException();
        }
        Long idrole = user.getRoleId();
        Optional<MstRole> u = roleRepo.findById(idrole);
        if(!u.isPresent()){
            throw new NotFoundRole();
        }
        RoleJwtDTO role = new RoleJwtDTO();
        role.setRoleName(u.get().getRoleName());
        role.setRoleId(u.get().getRoleId());

        UserJwtDTO rt = UserJwtDTO.builder()
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .jwtDTO(role)
                .roleId(user.getRoleId()).build();
        log.info(String.format("%s",new Gson().toJson(rt)));

        return rt;

    }
    private String getBearerToken(Map<String,String> bearer){
        return bearer.get("Authorization");
    }
}
