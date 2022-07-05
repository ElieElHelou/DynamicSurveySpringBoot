package com.example.practice.repositories;

import com.example.practice.domains.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("SELECT st FROM Token st WHERE st.id = ?1")
    Optional<Token> findTokenById(long id);

}
