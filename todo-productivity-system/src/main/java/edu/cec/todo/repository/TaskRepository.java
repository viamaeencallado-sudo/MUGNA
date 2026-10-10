package edu.cec.todo.repository;

import edu.cec.todo.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from Task t where " +
      "(:q is null or :q = '' or lower(t.title) like lower(concat('%', :q, '%')) or lower(coalesce(t.description,'')) like lower(concat('%', :q, '%'))) and " +
      "(:status is null or t.status = :status) and (:priority is null or t.priority = :priority) and " +
      "(:categoryId is null or t.category.categoryId = :categoryId) order by " +
      "case t.status when edu.cec.todo.model.TaskStatus.COMPLETED then 1 else 0 end, t.dueDate asc")
    List<Task> searchTasks(@Param("q") String q, @Param("status") TaskStatus status,
       @Param("priority") Priority priority, @Param("categoryId") Long categoryId);
    long countByStatus(TaskStatus status);
}
