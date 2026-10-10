package edu.cec.todo.service;

import edu.cec.todo.model.*;
import edu.cec.todo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository tasks;
    private final CategoryRepository categories;
    public TaskService(TaskRepository tasks, CategoryRepository categories) {
        this.tasks = tasks; this.categories = categories;
    }
    public List<Task> findAll(String q, TaskStatus status, Priority priority, Long categoryId) {
        return tasks.searchTasks(q, status, priority, categoryId);
    }
    public Task findById(Long id) {
        return tasks.findById(id).orElseThrow(() -> new IllegalArgumentException("Task not found: " + id));
    }
    @Transactional public Task save(Task task, Long categoryId) {
        task.setCategory(categoryId == null ? null : categories.findById(categoryId)
            .orElseThrow(() -> new IllegalArgumentException("Selected category was not found.")));
        return tasks.save(task);
    }
    @Transactional public void delete(Long id) { tasks.deleteById(id); }
    @Transactional public void complete(Long id) {
        Task task = findById(id); task.setStatus(TaskStatus.COMPLETED); tasks.save(task);
    }
    public List<Category> categories() { return categories.findAll(); }
    public long count(TaskStatus status) { return tasks.countByStatus(status); }
    public long countOverdue() { return tasks.findAll().stream().filter(Task::isOverdue).count(); }
    public long countDueSoon() { return tasks.findAll().stream().filter(Task::isDueSoon).count(); }
}
