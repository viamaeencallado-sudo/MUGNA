package edu.cec.todo.service;
import edu.cec.todo.model.Category;
import edu.cec.todo.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    public CategoryService(CategoryRepository repository) { this.repository = repository; }
    public List<Category> findAll() { return repository.findAll(); }
    public Category save(String name) {
        String clean = name == null ? "" : name.trim();
        if (clean.isBlank()) throw new IllegalArgumentException("Category name is required.");
        return repository.findByCategoryNameIgnoreCase(clean).orElseGet(() -> repository.save(new Category(clean)));
    }
}
