package com.example.onlineshop.entity.order;

import com.example.onlineshop.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
//import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "user_id")
    private Long userId;
//    @ManyToOne
//    @JoinColumn(name = "user_id", insertable = false, updatable = false)
//    private User user;//раскомментить когда добавят юзера

    @ManyToOne
    @JoinColumn(name = "condition_id", insertable = false, updatable = false)
    private Condition condition;

    @Column(name = "date")
    private LocalDate date; //разобраться с Date LocalDate и тд

    @OneToMany(mappedBy = "order")
    private List<ProductInOrder> productInOrder;//разобраться в каком случае двойные связи

    @Column(name = "comment")
    private String comment;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ProductInOrder> getProductInOrder() {
        return productInOrder;
    }

    public void setProductInOrder(List<ProductInOrder> productInOrder) {
        this.productInOrder = productInOrder;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Order(Long orderId) {
        this.orderId = orderId;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public Order(LocalDate date) {
        this.date = LocalDate.now();
    }

    public Order() {
    }
}

