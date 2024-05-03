package com.app.controlefinanceiro.model.user;

import com.app.controlefinanceiro.model.expense.Expense;
import com.app.controlefinanceiro.model.income.Income;
import com.app.controlefinanceiro.model.userRole.UserRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    //Relacionamento um(User)-para-muitos(Expense)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Income> incomes = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER) //anotação para que os dados UserRole seja carregado juntamente com a entidade User(principal)
    @JoinTable(name = "user_role",
              joinColumns = @JoinColumn(name = "user_id"),
              inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<UserRole> roles = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
