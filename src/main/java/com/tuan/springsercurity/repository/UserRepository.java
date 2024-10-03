package com.tuan.springsercurity.repository;

import com.tuan.springsercurity.dtos.response.UserDTO;
import com.tuan.springsercurity.model.User;
import com.tuan.springsercurity.util.constant.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<UserDetails> findByEmail(String userName);

    User findFirstByRole(Role role);

    @Query("SELECT new com.tuan.springsercurity.dtos.response.UserDTO(u.id,u.firstname,u.secondname,u.email) from User u")
    List<UserDTO> findAllUser();
}
