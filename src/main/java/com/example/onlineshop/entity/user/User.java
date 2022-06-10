package com.example.onlineshop.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

//    @OneToMany (mappedBy = "access_level_id")
//    @Column (name = "access_level_id", nullable = false)
//    private Set <Integer> accessLevelID;

    @Column(name = "access_level_id")
    private Level accessLevel;

    @ManyToOne(fetch = FetchType.LAZY)
//не пишем joincolumn потому что нам не нужна двойная связь. Он автоматически свяжет с сити по айди
    private City city;//customer

    @Column(name = "date_of_birth")
    private String dateOfBirth; //customer

    @Column(name = "discount")
    private double discount; //customer


    public User(Long id, String firstName, String lastName, String login, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }

//    {
//        ArrayList<User> users = new ArrayList<>();
//        users.add(new User(1L, "Romeo", "Montaque", "romeomont", "123"));
//        users.add(new User(2L, "Juliet", "Capuletti", "julietca", "1234"));
//    }

//    public ArrayList<User> index (){
//        return users;
//    }
}
