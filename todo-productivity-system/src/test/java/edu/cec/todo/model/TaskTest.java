package edu.cec.todo.model;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class TaskTest {
    @Test void priorityProvidesDifferentUrgencyMessages() {
        Task high = new Task("Exam", "", LocalDate.now().plusDays(1), Priority.HIGH, null);
        Task low = new Task("Read", "", LocalDate.now().plusDays(4), Priority.LOW, null);
        assertEquals("Handle this first", high.getUrgencyMessage());
        assertEquals("Complete when available", low.getUrgencyMessage());
    }
    @Test void overdueExcludesCompletedTasks() {
        Task task = new Task("Old task", "", LocalDate.now().minusDays(1), Priority.MEDIUM, null);
        assertTrue(task.isOverdue());
        task.setStatus(TaskStatus.COMPLETED);
        assertFalse(task.isOverdue());
    }
}
