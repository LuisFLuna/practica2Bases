package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantInventoryEntryRepository extends JpaRepository<PlantInventoryEntry, Long> {

    List<PlantInventoryEntry> findByNameContaining(String str);

    @Query("select p from PlantInventoryEntry where LOWER(p.name) like ?1")

    List<PlantInventoryEntry> finderMethod(String name);
    @Query(value="select * from plant_inventory_entry where LOWER(name) like ?1")

    List<PlantInventoryEntry> finderMethodV2(String name);
}
