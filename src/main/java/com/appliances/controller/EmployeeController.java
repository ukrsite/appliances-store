package com.appliances.controller;

import com.appliances.aspect.Loggable;
import com.appliances.model.Employee;
import com.appliances.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Displays a paginated and sorted list of employees.
     */
    @Loggable
    @GetMapping({"", "/"})
    public String showEmployees(
            @PageableDefault(sort = "id") Pageable pageable,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("id").ascending() : Sort.by("id").descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Employee> employeePage = employeeService.getPaginated(pageable);

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", employeePage.getNumber());
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("pageSize", employeePage.getSize());
        model.addAttribute("sortBy", pageable.getSort().toString());
        model.addAttribute("sortDir", sortDir);

        return "employee/employees"; // View: employees.html
    }

    /**
     * Displays the form for creating a new employee.
     */
    @Loggable
    @GetMapping("/add")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/newEmployee"; // View: newEmployee.html
    }

    /**
     * Handles the form submission for creating a new employee.
     */
    @Loggable
    @PostMapping("/add-employee")
    public String addEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "employee/newEmployee"; // Return form view with validation errors
        }
        try {
            employeeService.save(employee);
            redirectAttributes.addFlashAttribute("successMessage", "Employee added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add employee: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    /**
     * Displays the form for editing an existing employee.
     */
    @Loggable
    @GetMapping("/edit/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Employee> existingEmployee = employeeService.getById(id);

        if (existingEmployee.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Employee not found.");
            return "redirect:/employees";
        }

        model.addAttribute("employee", existingEmployee.get());
        return "employee/editEmployee"; // View: editEmployee.html
    }

    /**
     * Processes the form submission to update an employee.
     */
    @Loggable
    @PostMapping("/edit/{id}")
    public String editEmployee(
            @PathVariable Long id,
            @Valid @ModelAttribute("employee") Employee updatedEmployee,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "employee/editEmployee"; // Return form view with validation errors
        }

        Optional<Employee> existingEmployee = employeeService.getById(id);
        if (existingEmployee.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Employee not found.");
            return "redirect:/employees";
        }

        Employee employee = existingEmployee.get();
        employee.setName(updatedEmployee.getName());
        employee.setEmail(updatedEmployee.getEmail());
        employee.setPassword(updatedEmployee.getPassword());
        employee.setDepartment(updatedEmployee.getDepartment());

        employeeService.save(employee);
        redirectAttributes.addFlashAttribute("successMessage", "Employee updated successfully!");
        return "redirect:/employees";
    }

    /**
     * Deletes an employee.
     */
    @Loggable
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            employeeService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Employee deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete employee: " + e.getMessage());
        }
        return "redirect:/employees";
    }

    /**
     * Handles exceptions globally for this controller.
     */
    @Loggable
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
        return "error/generalError"; // View: generalError.html
    }
}
