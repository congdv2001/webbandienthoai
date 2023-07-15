package com.demo.repository;

import com.demo.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountReponsitory extends JpaRepository<Account,String> {
    Optional<Account>findByUsernameAndPassword(String un,String pwd);

}

