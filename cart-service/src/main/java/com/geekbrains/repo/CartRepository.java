package com.geekbrains.repo;

import com.geekbrains.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findAllByUsername(String username);
    boolean existsByUsernameAndTitle(String username, String title);
    CartItem findFirstByUsernameAndTitle(String username, String title);
}
