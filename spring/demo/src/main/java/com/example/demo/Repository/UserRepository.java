package com.example.demo.Repository;

import com.example.demo.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User findUserByUsername(@Param("username") String username);
}
