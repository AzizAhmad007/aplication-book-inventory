package com.pos.service;

import com.pos.common.ResponseDTO;
import com.pos.dto.master.MstItemDTO;
import com.pos.model.MstItem;
import com.pos.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pos.common.Checker.isNullStr;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepo;

    public ResponseEntity<?> saveItem(MstItemDTO requestDTO){
        if (requestDTO.getItemId() == null) {
            return createItem(requestDTO);
        }
        return updateItem(requestDTO);
    }

    private ResponseEntity<?> createItem(MstItemDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstItem itemEntity = new MstItem();
        MstItem item = itemRepo.findByItemId(requestDTO.getItemId());
        if (item == null){
            if (isNullStr(requestDTO.getItemName())){
                    itemEntity.setItemName(requestDTO.getItemName());

                    itemRepo.save(itemEntity);

                    response.setCode("201");
                    response.setData(itemEntity);
                    response.setMessage("Karyawan has been saved successfully");
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }
            response.setCode("204");
            response.setMessage("Item Name cannot be empty");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.setCode("409");
        response.setMessage("Data Item already exists");
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private ResponseEntity<?> updateItem(MstItemDTO requestDTO){
        ResponseDTO response = new ResponseDTO();
        MstItem itemEntity = new MstItem();
        MstItem itemList = itemRepo.findByItemId(requestDTO.getItemId());

        if (itemList == null){
            response.setCode("204");
            response.setMessage("data not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MstItem item = itemRepo.findByItemId(requestDTO.getItemId());

        if (requestDTO.getItemId() == null){
            itemEntity.setItemId(item.getItemId());
        } else {
            itemEntity.setItemId(requestDTO.getItemId());
        }
        if (isNullStr(requestDTO.getItemName())){
            itemEntity.setItemName(requestDTO.getItemName());
        } else {
            itemEntity.setItemName(item.getItemName());
        }

        itemRepo.save(itemEntity);

        response.setCode("201");
        response.setData(itemEntity);
        response.setMessage("Karyawan has been saved successfully");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAll(){
        ResponseDTO response = new ResponseDTO();
        List<MstItem> itemList = itemRepo.findAll();

        response.setCode("200");
        response.setData(itemList);
        response.setMessage("Get All Data successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> getById(Long itemId){
        ResponseDTO response = new ResponseDTO();
        MstItem item = itemRepo.findByItemId(itemId);
        if (item == null){
            response.setCode("204");
            response.setMessage("Item ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.setCode("200");
        response.setData(item);
        response.setMessage("Get Data By Item Id successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long itemId){
        ResponseDTO response = new ResponseDTO();
        MstItem item = itemRepo.findByItemId(itemId);
        if (item == null){
            response.setCode("204");
            response.setMessage("Item ID not found");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        itemRepo.delete(item);
        response.setCode("200");
        response.setData(null);
        response.setMessage("Item ID successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
