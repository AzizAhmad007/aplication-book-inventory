package com.pos.service;

import com.pos.common.ResponseDTO;
import com.pos.dto.master.MstUserDTO;
import com.pos.model.MstRole;
import com.pos.model.MstUser;
import com.pos.repository.RoleRepository;
import com.pos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.List;

import static com.pos.common.Checker.isNullStr;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private RoleService roleService;

    public ResponseEntity<?> saveUser(MstUserDTO requestDTO){
        if (requestDTO.getUserId() == null){
            return createUser(requestDTO);
        }
        return updateUser(requestDTO);
    }

    private ResponseEntity<?> createUser(MstUserDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUser userEntity = new MstUser();
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        MstRole role = roleRepo.findByRoleId(requestDTO.getRoleId());

        if (role.getRoleId() == null){
            response.setCode("204");
            response.setMessage("Role Id cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (user == null){
            if (isNullStr(requestDTO.getUserName())){
                //Cek format Email
                if (!isValidEmail(requestDTO.getUserEmail())){
                    response.setCode("204");
                    response.setMessage("Email tidak valid");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                //Cek apakah email sudah ada di database
                MstUser existingUser = userRepo.findByUserId(requestDTO.getUserId());
                if (existingUser != null){
                    response.setCode("204");
                    response.setMessage("Email sudah ada");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                // Cek data tidak boleh null atau kosong
                if (isNullStr(requestDTO.getUserEmail())){
                    if (isNullStr(requestDTO.getPassword())){
                        userEntity.setRoleId(requestDTO.getRoleId());
                        userEntity.setUserName(requestDTO.getUserName());
                        userEntity.setUserEmail(requestDTO.getUserEmail());
                        userEntity.setPassword(passEncript(requestDTO.getPassword()));

                        userRepo.save(userEntity);

                        response.setCode("201");
                        response.setData(userEntity);
                        response.setMessage("Karyawan has been saved successfully");
                        return new ResponseEntity<>(response, HttpStatus.CREATED);
                    }
                    response.setCode("204");
                    response.setMessage("Password cannot be empty");
                    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
                }
                response.setCode("204");
                response.setMessage("User Email cannot be empty");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("204");
            response.setMessage("User Name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Spesification already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private boolean isValidEmail(String emailKaryawan){
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return emailKaryawan.matches(emailRegex);
    }

    private ResponseEntity<?> updateUser(MstUserDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstUser userEntity = new MstUser();
        MstUser userList = userRepo.findByUserId(requestDTO.getUserId());

        if (userList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstUser user = userRepo.findByUserId(requestDTO.getUserId());
        if (requestDTO.getUserId() == null){
            userEntity.setUserId(user.getUserId());
        } else {
            userEntity.setUserId(requestDTO.getUserId());
        }
        if (requestDTO.getRoleId() == null){
            userEntity.setRoleId(user.getRoleId());
        } else {
            userEntity.setRoleId(requestDTO.getRoleId());
        }
        if (isNullStr(requestDTO.getUserName())){
            userEntity.setUserName(requestDTO.getUserName());
        } else {
            userEntity.setUserName(user.getUserName());
        }

        if (isNullStr(requestDTO.getUserEmail())){
            userEntity.setUserEmail(requestDTO.getUserEmail());
        } else {
            userEntity.setUserEmail(user.getUserEmail());
        }
        if (isNullStr(requestDTO.getPassword())){
            userEntity.setPassword(passEncript(requestDTO.getPassword()));
        } else {
            userEntity.setPassword(user.getPassword());
        }
        userRepo.save(userEntity);

        response.setCode("201");
        response.setData(userEntity);
        response.setMessage("Karyawan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstUser> userList = userRepo.findAll();

        response.setCode("200");
        response.setData(userList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long userId){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(userId);
        if (user == null){
            response.setCode("204");
            response.setMessage("Role ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(user);
        response.setMessage("Get Data By Role Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long userId){
        ResponseDTO response = new ResponseDTO();
        MstUser user = userRepo.findByUserId(userId);
        if (user == null){
            response.setCode("204");
            response.setMessage("Role ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        userRepo.delete(user);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Role ID successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private String passEncript(String pass){
        MessageDigest md;
        String passEnc;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] bytes = md.digest();
            StringBuilder s = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                s.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passEnc = s.toString();
        } catch (Exception e) {
            return "Password Encryption Error";
        }
        return passEnc;
    }
}
