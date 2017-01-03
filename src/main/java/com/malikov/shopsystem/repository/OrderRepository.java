package com.malikov.shopsystem.repository;

import com.malikov.shopsystem.model.Order;

import java.util.Collection;

public interface OrderRepository extends AbstractRepository<Order> {

    Collection<Order> getByCustomerId(int customerId);

    Collection<Order> getByProductId(int productId);

}
