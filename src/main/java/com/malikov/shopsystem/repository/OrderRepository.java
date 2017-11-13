package com.malikov.shopsystem.repository;

import com.malikov.shopsystem.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Transactional(readOnly = true)
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, QueryDslPredicateExecutor {

    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.id=:customerId")
    Collection<Order> getByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT o FROM Order o JOIN o.orderItems oi WHERE oi.product.id=:productId")
    Collection<Order> getByProductId(@Param("productId") Long productId);

/*
    @Modifying
    @Transactional
    @Query("UPDATE Order o SET o.status = :status WHERE o.id = :orderId")
    void updateStatus(@Param("orderId") Long orderId, @Param("status") OrderStatus status);
*/

}
