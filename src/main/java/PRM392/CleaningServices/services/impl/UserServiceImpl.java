package PRM392.CleaningServices.services.impl;

import PRM392.CleaningServices.model.Role;
import PRM392.CleaningServices.model.User;
import PRM392.CleaningServices.repository.RoleRepository;
import PRM392.CleaningServices.repository.UserRepository;
import PRM392.CleaningServices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(String email, String password, String fullName) throws Exception {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email đã tồn tại.");
        }
        String hashedPassword = passwordEncoder.encode(password);
        Role role = roleRepository.findByRoleName("Customer")
                .orElseThrow(() -> new Exception("Role CUSTOMER không tồn tại."));
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setFullName(fullName);
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public void updateUserRole(Long userId, String roleName) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Người dùng không tồn tại."));
        Role role = roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new Exception("Role không tồn tại."));
        user.setRole(role);
        userRepository.save(user);
    }
}
