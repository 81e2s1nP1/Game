package com.pa.dto;

public class UserDTO {
    private String userName;
    private String numberPhone;

    public UserDTO() {}

    public UserDTO(String userName, String numberPhone) {
        this.userName = userName;
        this.numberPhone = numberPhone;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}
}

