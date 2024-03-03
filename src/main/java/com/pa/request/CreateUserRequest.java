package com.pa.request;

import com.pa.dto.UserDTO;
import com.pa.dto.UserDataLoginDTO;

public class CreateUserRequest {
    private UserDTO userDTO;
    private UserDataLoginDTO userDataLoginDTO;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public UserDataLoginDTO getUserDataLoginDTO() {
        return userDataLoginDTO;
    }

    public void setUserDataLoginDTO(UserDataLoginDTO userDataLoginDTO) {
        this.userDataLoginDTO = userDataLoginDTO;
    }
}

