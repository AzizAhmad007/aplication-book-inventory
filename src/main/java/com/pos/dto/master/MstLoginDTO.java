package com.pos.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstLoginDTO {
    private String userEmail;
    private String password;
}
