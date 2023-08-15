package com.pos.service;

import com.pos.common.ResponseDTO;
import com.pos.dto.master.MstRoleDTO;
import com.pos.model.MstRole;
import com.pos.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pos.common.Checker.isNullStr;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

    public ResponseEntity<?> saveRole(MstRoleDTO requestDTO){
        if (requestDTO.getRoleId() == null){
            return createRole(requestDTO);
        }
        return updateRole(requestDTO);
    }

    private ResponseEntity<?> createRole(MstRoleDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstRole roleEntity = new MstRole();
        MstRole role = roleRepo.findByRoleId(requestDTO.getRoleId());
        if (role == null){
            if (isNullStr(requestDTO.getRoleName())){
                roleEntity.setRoleName(requestDTO.getRoleName());

                roleRepo.save(roleEntity);

                response.setCode("201");
                response.setData(roleEntity);
                response.setMessage("Role has been saved successfully");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            response.setCode("204");
            response.setMessage("Role Name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Item already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateRole(MstRoleDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstRole roleEntity = new MstRole();
        MstRole roleList = roleRepo.findByRoleId(requestDTO.getRoleId());

        if (roleList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstRole role = roleRepo.findByRoleId(requestDTO.getRoleId());

        if (requestDTO.getRoleId() == null){
            roleEntity.setRoleId(role.getRoleId());
        } else {
            roleEntity.setRoleId(requestDTO.getRoleId());
        }
        if (isNullStr(requestDTO.getRoleName())){
            roleEntity.setRoleName(requestDTO.getRoleName());
        } else {
            roleEntity.setRoleName(role.getRoleName());
        }

        roleRepo.save(roleEntity);

        response.setCode("201");
        response.setData(roleEntity);
        response.setMessage("Role has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstRole> roleList = roleRepo.findAll();

        response.setCode("200");
        response.setData(roleList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long roleId){
        ResponseDTO response = new ResponseDTO();
        MstRole role = roleRepo.findByRoleId(roleId);
        if (role == null){
            response.setCode("204");
            response.setMessage("Role ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(role);
        response.setMessage("Get Data By Role Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long roleId){
        ResponseDTO response = new ResponseDTO();
        MstRole role = roleRepo.findByRoleId(roleId);
        if (role == null){
            response.setCode("204");
            response.setMessage("Role ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        roleRepo.delete(role);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Role ID successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
