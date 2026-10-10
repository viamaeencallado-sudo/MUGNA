package edu.cec.todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "app_users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, unique = true, length = 80)
    private String username;
    @Column(length = 255)
    private String password;

    protected User() {}
    public User(String name, String username, String password) {
        this.name = name; this.username = username; this.password = password;
    }
    public Long getUserId() { return userId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String profileLabel() { return name; }
}
