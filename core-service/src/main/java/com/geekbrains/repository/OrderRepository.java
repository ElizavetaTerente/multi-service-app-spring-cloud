package com.geekbrains.repository;

import com.geekbrains.model.Order;
import com.geekbrains.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o FROM Order o WHERE o.user =:user")
    Set<Order> findAllOrdersOfUser(User user);

}
