package edu.cec.todo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(nullable = false, unique = true, length = 60)
    private String categoryName;

    protected Category() {}
    public Category(String categoryName) { this.categoryName = categoryName; }
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
