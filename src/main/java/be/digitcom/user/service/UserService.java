package be.digitcom.user.service;

import be.digitcom.user.entity.User;
import be.digitcom.user.repository.UserRepository;
import be.digitcom.user.valueobject.Department;
import be.digitcom.user.valueobject.ResponseTemplateValueObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {
    private final UserRepository repository;
    private final RestTemplate restTemplate;

    public UserService(UserRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public User save(User user) {
        log.info("Inside UserService#save method");
        return repository.save(user);
    }

    public User findById(Long id) {
        log.info("Inside UserService#findById method");
        return repository.findById(id).orElseThrow();
    }

    public ResponseTemplateValueObject getUserWithDepartment(Long userId) {
        log.info("Inside UserService#getUserWithDepartment method");
        ResponseTemplateValueObject templateValueObject = new ResponseTemplateValueObject();
        User user = repository.findById(userId).orElseThrow();

        Department department = restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/" + user.getDepartmentId(), Department.class);
        templateValueObject.setDepartment(department);
        templateValueObject.setUser(user);

        return templateValueObject;
    }
}
