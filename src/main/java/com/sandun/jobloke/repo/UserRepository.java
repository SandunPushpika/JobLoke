package com.sandun.jobloke.repo;

import com.sandun.jobloke.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApplicationUser,String> {

    @Query("SELECT s From users s where s.Email = ?1")
    Optional<ApplicationUser> findUserByEmail(String email);
}
