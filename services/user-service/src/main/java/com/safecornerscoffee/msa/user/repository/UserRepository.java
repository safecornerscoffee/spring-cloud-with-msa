package com.safecornerscoffee.msa.user.repository;

import com.safecornerscoffee.msa.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    User findUserByUserId(String userId);
    User findUserByEmail(String email);
}
