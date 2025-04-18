package ru.rostec.prodmanage.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.rostec.prodmanage.department.model.Department;
import ru.rostec.prodmanage.user.repository.UserRepository;
import ru.rostec.prodmanage.user.model.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findUsersByDepartment(Department department) {
        return userRepository.findUserByDepartment(department);
    }

    @Override
    public List<User> searchUserByName(String name) {
        return userRepository.searchUserByName(name);
    }

    @Override
    public List<User> findUsersBySupervisor(User user) {
        return userRepository.findUserBySupervisor(user);
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
