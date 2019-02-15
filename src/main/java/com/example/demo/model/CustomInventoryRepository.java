package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public interface CustomInventoryRepository {
    List<PlantInventoryEntry> findAvailablePlants(String name, LocalDate startDate, LocalDate endDate);
}

// InventoryRepositoryImpl in the class named CustomInventoryRepository.java ??
public class InventoryRepositoryImpl implements CustomInventoryRepository {
    @Autowired
    EntityManager em;

    public List<PlantInventoryEntry> findAvailablePlants(String name, LocalDate startDate, LocalDate endDate) {
        return em.createQuery("select p from PlantInventoryEntry where LOWER(p.name) like ?1", PlantInventoryEntry.class)
                .setParameter(1, name)
                .getResultList();
    }
}