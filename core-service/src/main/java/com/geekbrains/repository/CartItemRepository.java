package com.geekbrains.repository;

import com.geekbrains.model.CartItem;
import com.geekbrains.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CartItemRepository extends JpaRepository<CartItem,String> {


    @Modifying
    @Query("Update CartItem c SET c.quantity =:quantity WHERE c.productName =:name")
    void updateQuantity(@Param("quantity") int quantity, @Param("name") String name);

    @Query("select c FROM CartItem c WHERE c.user =:user")
    List<CartItem> findAllCartItemOfUser(User user);
}
