package com.pos.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstUserDTO {
    private Long userId;
    private Long roleId;
    private String userName;
    private String userEmail;
    private String password;
}
