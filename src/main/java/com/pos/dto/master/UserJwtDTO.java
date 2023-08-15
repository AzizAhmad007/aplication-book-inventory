package com.pos.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserJwtDTO {
    private Long userId;
    private Long roleId;
    private String userEmail;
    private RoleJwtDTO jwtDTO;
}
