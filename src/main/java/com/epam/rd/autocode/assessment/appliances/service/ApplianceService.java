package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Appliance;

public interface ApplianceService {
    Iterable<Appliance> getAllAppliances();
    Appliance getAppliance(long id);
    Appliance save(Appliance product);
}
