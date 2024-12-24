package com.appliances.controller;

import com.appliances.aspect.Loggable;
import com.appliances.model.Client;
import com.appliances.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    /**
     * Displays a paginated and sorted list of clients.
     */
    @Loggable
    @GetMapping({"", "/"})
    public String showClients(
            @PageableDefault(sort = "id") Pageable pageable,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by("id").ascending() : Sort.by("id").descending();
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        Page<Client> clientsPage = service.getPaginated(pageable);

        model.addAttribute("clients", clientsPage.getContent());
        model.addAttribute("currentPage", clientsPage.getNumber());
        model.addAttribute("totalPages", clientsPage.getTotalPages());
        model.addAttribute("pageSize", clientsPage.getSize());
        model.addAttribute("sortBy", pageable.getSort().toString());
        model.addAttribute("sortDir", sortDir);

        return "client/clients"; // View: clients.html
    }

    /**
     * Displays the form for creating a new client.
     */
    @Loggable
    @GetMapping("/add")
    public String newClient(Model model) {
        model.addAttribute("client", new Client());
        return "client/newClient"; // View: newClient.html
    }

    /**
     * Handles the form submission for creating a new client.
     */
    @Loggable
    @PostMapping("/add-client")
    public String addClient(
            @Valid @ModelAttribute("client") Client client,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "client/newClient"; // Return form view with validation errors
        }
        try {
            service.save(client);
            redirectAttributes.addFlashAttribute("successMessage", "Client added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to add client: " + e.getMessage());
        }
        return "redirect:/clients";
    }


    /**
     * Displays the form for editing an existing client.
     */
    @Loggable
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Client> existing = service.getById(id);

        if (existing.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Client not found.");
            return "redirect:/clients";
        }

        model.addAttribute("client", existing.get());
        return "client/editClient"; // View: editClient.html
    }

    /**
     * Processes the form submission to update an client.
     */
    @Loggable
    @PostMapping("/edit/{id}")
    public String editClient(
            @PathVariable Long id,
            @Valid @ModelAttribute("client") Client updated,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "client/editClient"; // Return form view with validation errors
        }

        Optional<Client> existing = service.getById(id);
        if (existing.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Client not found.");
            return "redirect:/clients";
        }

        Client client = existing.get();
        client.setName(updated.getName());
        client.setEmail(updated.getEmail());
        client.setPassword(updated.getPassword());
        client.setCard(updated.getCard());

        service.save(client);
        redirectAttributes.addFlashAttribute("successMessage", "client updated successfully!");
        return "redirect:/clients";
    }

    /**
     * Deletes a client.
     */
    @Loggable
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Client deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to delete client: " + e.getMessage());
        }
        return "redirect:/clients";
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