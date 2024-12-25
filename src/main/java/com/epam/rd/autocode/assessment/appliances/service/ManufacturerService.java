package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.Manufacturer;

public interface ManufacturerService {
    Iterable<Manufacturer> getAllManufacturers();
    Manufacturer getManufacturer(long id);
    Manufacturer save(Manufacturer manufacturer);
}
