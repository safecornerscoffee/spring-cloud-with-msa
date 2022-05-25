package com.safecornerscoffee.msa.user.repository;

import com.safecornerscoffee.msa.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUserId(String userId);
    User findUserByEmail(String email);
}
