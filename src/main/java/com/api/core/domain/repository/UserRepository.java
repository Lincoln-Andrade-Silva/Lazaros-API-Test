package com.api.core.domain.repository;

import com.api.core.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(nativeQuery = true, value = "delete from tb_user_profile tup where tup.user_id = :id")
    void deleteUserProfilesByUserId(Long id);
}
