package PRM392.CleaningServices.controller;

import PRM392.CleaningServices.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String returnString(){
        return "AdminController";
    }
    @PutMapping("/update-role/{userId}")
    public ResponseEntity<?> updateUserRole(@PathVariable Long userId, @RequestParam String roleName) {
        try {
            userService.updateUserRole(userId, roleName);
            return ResponseEntity.ok("Update Role Successful!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
