package org.zoominfo.drive.dao;//package org.example.dao;

import org.springframework.data.repository.CrudRepository;
import org.zoominfo.drive.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByName(String username);

}
