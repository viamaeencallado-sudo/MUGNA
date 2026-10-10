package edu.cec.todo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    @NotBlank(message = "Please enter a task title.")
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String title;
    @Size(max = 1000)
    @Column(length = 1000)
    private String description;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TaskStatus status = TaskStatus.PENDING;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Priority priority = Priority.MEDIUM;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    private LocalDateTime reminderAt;
    private LocalDateTime createdAt = LocalDateTime.now();

    protected Task() {}
    public Task(String title, String description, LocalDate dueDate, Priority priority, Category category) {
        this.title = title; this.description = description; this.dueDate = dueDate;
        this.priority = priority == null ? Priority.MEDIUM : priority; this.category = category;
    }
    public Long getTaskId() { return taskId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status == null ? TaskStatus.PENDING : status; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority == null ? Priority.MEDIUM : priority; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public LocalDateTime getReminderAt() { return reminderAt; }
    public void setReminderAt(LocalDateTime reminderAt) { this.reminderAt = reminderAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public boolean isOverdue() {
        return dueDate != null && dueDate.isBefore(LocalDate.now()) && status != TaskStatus.COMPLETED;
    }
    public boolean isDueSoon() {
        return dueDate != null && !dueDate.isBefore(LocalDate.now()) &&
            !dueDate.isAfter(LocalDate.now().plusDays(1)) && status != TaskStatus.COMPLETED;
    }
    // Polymorphic behavior: task urgency can be presented differently by priority.
    public String getUrgencyMessage() {
        return switch (priority == null ? Priority.MEDIUM : priority) {
            case HIGH -> "Handle this first";
            case MEDIUM -> "Plan time for this";
            case LOW -> "Complete when available";
        };
    }
}
