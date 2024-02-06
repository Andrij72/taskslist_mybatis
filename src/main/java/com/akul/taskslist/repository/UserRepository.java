package com.akul.taskslist.repository;

import com.akul.taskslist.domain.user.Role;
import com.akul.taskslist.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserById(Long id);

    Optional<User> findUserByUsername(String username);
    void update(User user);
    void create(User user);
    void insertUserRole(Long userId, Role role);
    boolean isTaskOwner(Long userId, Long taskId);
    void delete(Long id);
}
