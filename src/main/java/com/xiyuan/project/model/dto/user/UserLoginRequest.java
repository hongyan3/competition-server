package com.xiyuan.project.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    private Long schoolId;

    private String userAccount;

    private String userPassword;

    private static final long serialVersionUID = 1L;
}
