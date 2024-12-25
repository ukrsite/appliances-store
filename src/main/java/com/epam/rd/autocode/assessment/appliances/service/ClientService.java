package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ClientService {
    Page<Client> getPaginated(Pageable pageable);
    Optional<Client> getById(Long id);
    Client save(Client client);
    void deleteById(Long id);
}
