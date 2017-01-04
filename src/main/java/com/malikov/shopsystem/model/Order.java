package com.malikov.shopsystem.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Order.DELETE, query = "DELETE FROM Order o WHERE o.id=:id"),
        @NamedQuery(name = Order.BY_CUSTOMER_ID, query = "SELECT o FROM Order o WHERE o.customer.id=:customerId"),
//Order.BY_PRODUCT_ID query may be wrong!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        @NamedQuery(name = Order.BY_PRODUCT_ID, query = "SELECT o FROM Order o JOIN o.products WHERE o.products.id=:productId"),
        @NamedQuery(name = Order.ALL_SORTED, query = "SELECT o FROM Order o ORDER BY o.datePlaced"),
})
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    public static final String DELETE = "Order.delete";
    public static final String ALL_SORTED = "Order.getAllSorted";
    public static final String BY_CUSTOMER_ID = "Order.getByCustomerId";
    public static final String BY_PRODUCT_ID = "Order.getByProductId";

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinTable(
            name = "products_to_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products;


    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "user_id")
    private User user;

//    @Column(name = "date_placed")
//    private LocalDate datePlaced;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;

        Order order = (Order) o;

        if (products != null ? !products.equals(order.products) : order.products != null) return false;
        if (customer != null ? !customer.equals(order.customer) : order.customer != null) return false;
        return user != null ? user.equals(order.user) : order.user == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (products != null ? products.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "products=" + products +
                ", customer=" + customer +
                ", user=" + user +
                '}';
    }
}
