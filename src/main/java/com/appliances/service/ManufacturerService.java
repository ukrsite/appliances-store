package com.appliances.service;

import com.appliances.model.Manufacturer;

public interface ManufacturerService {
    Iterable<Manufacturer> getAllManufacturers();
    Manufacturer getManufacturer(long id);
    Manufacturer save(Manufacturer manufacturer);
}
