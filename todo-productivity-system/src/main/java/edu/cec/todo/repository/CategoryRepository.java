package edu.cec.todo.repository;
import edu.cec.todo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByCategoryNameIgnoreCase(String categoryName);
}
