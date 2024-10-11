package PRM392.CleaningServices.controller;

import PRM392.CleaningServices.dto.request.ServiceRequest;
import PRM392.CleaningServices.model.Service;
import PRM392.CleaningServices.services.ServiceService;
import PRM392.CleaningServices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private ServiceService serviceService;

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

    @PostMapping("/services/created-service")
    public ResponseEntity<Service> createService(@RequestBody Service servicerequest ) {
        Service createdService = serviceService.createService(servicerequest);
        return ResponseEntity.ok(createdService);
    }

    @GetMapping("/services/{serviceId}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long serviceId) {
        Service services = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(services);
    }

    @GetMapping("/services")
    public ResponseEntity<List<Service>> getService() {
        List<Service> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @PutMapping("/services/{serviceId}")
    public ResponseEntity<Service> updateService(@RequestBody Service servicerequest, @PathVariable Long serviceId) {
        Service updateService = serviceService.updateService(serviceId, servicerequest);
        return ResponseEntity.ok(updateService);
    }

    @DeleteMapping("/services/{serviceId}")
    public ResponseEntity<Service> deleteService(@PathVariable Long serviceId) {
        serviceService.deleteServiceById(serviceId);
        return ResponseEntity.noContent().build();
    }



}
