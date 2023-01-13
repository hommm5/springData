package com.example.spring_auto_mapping_exr.repositories;

import com.example.spring_auto_mapping_exr.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        User findUserByEmail (String email);
}
