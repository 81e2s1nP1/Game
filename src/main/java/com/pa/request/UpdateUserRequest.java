package com.pa.request;

import com.pa.dto.UserDTO;
import com.pa.dto.UserDataLoginDTO;

public class UpdateUserRequest {
    private UserDTO userDto;
    private UserDataLoginDTO dataLoginDto;

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public UserDataLoginDTO getDataLoginDto() {
        return dataLoginDto;
    }

    public void setDataLoginDto(UserDataLoginDTO dataLoginDto) {
        this.dataLoginDto = dataLoginDto;
    }
}

