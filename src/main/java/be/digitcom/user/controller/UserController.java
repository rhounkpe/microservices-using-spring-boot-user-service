package be.digitcom.user.controller;

import be.digitcom.user.entity.User;
import be.digitcom.user.service.UserService;
import be.digitcom.user.valueobject.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = {"", "/"})
    public User save(@RequestBody User user) {
        log.info("Inside UserController#save method");
        return service.save(user);
    }

    // @Deprecated
    @GetMapping("/{id}")
    public User findById(@PathVariable("id")Long id) {
        log.info("Inside UserController#findById method");
        return service.findById(id);
    }

    @GetMapping("/{id}/user-with-department")
    public ResponseTemplateValueObject getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside UserController#getUserWithDepartment method");
        return service.getUserWithDepartment(userId);
    }
}
