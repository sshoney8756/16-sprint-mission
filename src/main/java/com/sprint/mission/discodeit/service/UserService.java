package com.sprint.mission.discodeit.service;

import com.sprint.mission.discodeit.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User create(String name, String email, String password);

    User update(UUID id, String newName, String newEmail, String newPassword);

    Optional<User> findById(UUID id);

    List<User> findAll();

    void delete(UUID id);
}
