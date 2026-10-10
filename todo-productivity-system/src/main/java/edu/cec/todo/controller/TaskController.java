package edu.cec.todo.controller;

import edu.cec.todo.model.*;
import edu.cec.todo.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TaskController {
    private final TaskService taskService;
    private final CategoryService categoryService;
    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService; this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String dashboard(@RequestParam(required=false) String q,
       @RequestParam(required=false) TaskStatus status,
       @RequestParam(required=false) Priority priority,
       @RequestParam(required=false) Long categoryId, Model model) {
        model.addAttribute("tasks", taskService.findAll(q, status, priority, categoryId));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("statuses", TaskStatus.values());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("q", q == null ? "" : q);
        model.addAttribute("selectedStatus", status);
        model.addAttribute("selectedPriority", priority);
        model.addAttribute("selectedCategoryId", categoryId);
        model.addAttribute("pendingCount", taskService.count(TaskStatus.PENDING));
        model.addAttribute("inProgressCount", taskService.count(TaskStatus.IN_PROGRESS));
        model.addAttribute("completedCount", taskService.count(TaskStatus.COMPLETED));
        model.addAttribute("overdueCount", taskService.countOverdue());
        model.addAttribute("dueSoonCount", taskService.countDueSoon());
        return "dashboard";
    }

    @GetMapping("/tasks/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task("", "", null, Priority.MEDIUM, null));
        addFormOptions(model); return "task-form";
    }

    @GetMapping("/tasks/{id}/edit")
    public String editTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.findById(id));
        addFormOptions(model); return "task-form";
    }

    @PostMapping("/tasks/save")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult result,
       @RequestParam(required=false) Long categoryId, Model model, RedirectAttributes redirect) {
        if (result.hasErrors()) { addFormOptions(model); return "task-form"; }
        taskService.save(task, categoryId);
        redirect.addFlashAttribute("success", "Task saved successfully.");
        return "redirect:/";
    }

    @PostMapping("/tasks/{id}/complete")
    public String complete(@PathVariable Long id, RedirectAttributes redirect) {
        taskService.complete(id); redirect.addFlashAttribute("success", "Task marked as completed."); return "redirect:/";
    }

    @PostMapping("/tasks/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        taskService.delete(id); redirect.addFlashAttribute("success", "Task deleted."); return "redirect:/";
    }

    @PostMapping("/categories")
    public String addCategory(@RequestParam String categoryName, RedirectAttributes redirect) {
        try { categoryService.save(categoryName); redirect.addFlashAttribute("success", "Category saved."); }
        catch (IllegalArgumentException ex) { redirect.addFlashAttribute("error", ex.getMessage()); }
        return "redirect:/";
    }

    private void addFormOptions(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("statuses", TaskStatus.values());
    }
}
