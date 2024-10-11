package PRM392.CleaningServices.services;

import PRM392.CleaningServices.model.User;

public interface UserService {

    User registerUser(String email, String password, String fullName) throws Exception;
    void updateUserRole(Long userId, String roleName) throws Exception;
}
