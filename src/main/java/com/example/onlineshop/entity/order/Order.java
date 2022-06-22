package com.example.onlineshop.entity.order;
import com.example.onlineshop.entity.user.User;
import javax.persistence.*;

import java.util.List;


@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")//, insertable = false, updatable = false)
    private User user;//раскомментить когда добавят юзера

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "condition_id")//, insertable = false, updatable = false)
    private Condition condition;

    @Column(name = "date")
    private String date; //разобраться с Date LocalDate и тд

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //mappedBy = "order" , cascade = CascadeType.ALL
    @JoinColumn(name = "order_Id")
    private List<ProductInOrder> productInOrder;//разобраться в каком случае двойные связи

    @Column(name = "comment")
    private String comment;

    public Order() {

    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}

