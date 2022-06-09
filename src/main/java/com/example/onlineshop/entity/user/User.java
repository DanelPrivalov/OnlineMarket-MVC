package com.example.onlineshop.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

//@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "last_name")//, nullable = false)
    private String lastName;
    @Column(name = "first_name")//, nullable = false)
    private String firstName;

  

    @Column(name = "login")//, nullable = false)
    private String login;

    @Column(name = "password")//, nullable = false)
    private String password;

    @Column
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    //    @OneToMany (mappedBy = "access_level_id")
//    @Column (name = "access_level_id", nullable = false)
//    private Set <Integer> accessLevelID;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "access_level_id")
    private Level accessLevel;

    @ManyToOne(fetch = FetchType.LAZY)
//не пишем joincolumn потому что нам не нужна двойная связь. Он автоматически свяжет с сити по айди
    private City city;//customer

    @Column(name = "date_of_birth")
    private String dateOfBirth; //customer

    @Column(name = "discount")
    private double discount; //customer

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Level getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(Level accessLevel) {
        this.accessLevel = accessLevel;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


}
