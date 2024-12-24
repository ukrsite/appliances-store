package com.appliances.service;

import com.appliances.model.Appliance;

public interface ApplianceService {
    Iterable<Appliance> getAllAppliances();
    Appliance getAppliance(long id);
    Appliance save(Appliance product);
}
