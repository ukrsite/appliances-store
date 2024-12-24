package com.appliances.service.impl;

import com.appliances.aspect.Loggable;
import com.appliances.model.Client;
import com.appliances.repository.ClientRepository;
import com.appliances.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    public Iterable<Client> getAll() {
        return repository.findAll();
    }

    @Loggable
    public Page<Client> getPaginated(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Loggable
    public Optional<Client> getById(Long id) {
        return Optional.of(repository.getReferenceById(id));
    }

    @Loggable
    public Client save(Client client) {
        return repository.save(client);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
