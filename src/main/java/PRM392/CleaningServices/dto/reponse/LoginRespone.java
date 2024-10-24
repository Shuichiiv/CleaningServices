package PRM392.CleaningServices.dto.reponse;

import PRM392.CleaningServices.model.Role;

public class LoginRespone {
    private String token;
    private String email;
    private Role role; // Thay vì String, sử dụng Role

    public LoginRespone(String token, String email, Role role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }
}
