package PRM392.CleaningServices.services;

import PRM392.CleaningServices.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers() throws Exception;
    User registerUser(String email, String password, String fullName) throws Exception;
    void updateUserRole(Long userId, String roleName) throws Exception;
    List<User> getUsersByRole(String roleName) throws Exception;
}
