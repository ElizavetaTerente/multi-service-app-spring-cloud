package com.geekrains.repository;

import com.geekrains.model.CartItem;
import com.geekrains.model.Order;
import com.geekrains.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o FROM Order o WHERE o.user =:user")
    Set<Order> findAllOrdersOfUser(User user);

}
