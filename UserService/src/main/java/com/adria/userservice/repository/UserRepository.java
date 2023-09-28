package com.adria.userservice.repository;

import com.adria.userservice.entities.Admin;
import com.adria.userservice.entities.Customer;
import com.adria.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.id = :userId AND TYPE(u) = :discriminator")
    Optional<User> findByIdAndDiscriminator(@Param("userId") Long userId, @Param("discriminator") String discriminator);
}
