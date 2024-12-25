package com.epam.rd.autocode.assessment.appliances.service.impl;

import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.repository.ApplianceRepository;
import com.epam.rd.autocode.assessment.appliances.service.ApplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplianceServiceImpl implements ApplianceService {

    @Autowired
    ApplianceRepository applianceRepository;

    @Override
    public Iterable<Appliance> getAllAppliances() {
        return applianceRepository.findAll();
    }

    @Override
    public Appliance getAppliance(long id) {
        return applianceRepository.getReferenceById(id);
    }

    @Override
    public Appliance save(Appliance product) {
        return applianceRepository.save(product);
    }
}
