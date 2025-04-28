package ru.rostec.prodmanage.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.user.model.User;
import ru.rostec.prodmanage.user.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("by-department")
    public ResponseEntity<List<User>> findUsersByDepartment(@RequestParam Long id) {
        Department department = new Department();
        department.setId(id);
        return ResponseEntity.ok(userService.findUsersByDepartment(department));
    }

    @GetMapping("/by-name")
    public ResponseEntity<List<User>> searchUserByName(@RequestParam String name) {
        return ResponseEntity.ok(userService.searchUserByName(name));
    }

    @GetMapping("/by-supevisor")
    public ResponseEntity<List<User>> findUsersBySupervisor(@RequestParam Long id) {
        User supervisor = new User();
        supervisor.setId(id);
        return ResponseEntity.ok(userService.findUsersBySupervisor(supervisor));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = new User();
        URI location = URI.create("api/task" + created.getId());
        return ResponseEntity.created(location).body(created);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<User> findAll(@PageableDefault(page = 0, size = 20, sort = "username") Pageable pageable) {
        return userService.findAll(pageable);
    }
}
