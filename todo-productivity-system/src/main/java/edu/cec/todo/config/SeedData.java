package edu.cec.todo.config;
import edu.cec.todo.model.Category;
import edu.cec.todo.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeedData {
    @Bean CommandLineRunner seedCategories(CategoryRepository repository) {
        return args -> {
            for (String name : new String[]{"School", "Assignments", "Exams", "Projects", "Personal"}) {
                if (repository.findByCategoryNameIgnoreCase(name).isEmpty()) repository.save(new Category(name));
            }
        };
    }
}
