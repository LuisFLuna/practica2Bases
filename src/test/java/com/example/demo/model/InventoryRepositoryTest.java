package com.example.demo.model;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@Sql(scripts="plants-dataset.sql")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InventoryRepositoryTest {
    @Autowired
    PlantInventoryEntryRepository plantInventoryEntryRepository;

    @Autowired
    PlantInventoryItemRepository plantInventoryItemRepository;

    @Autowired
    PlantReservationRepository plantReservationRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Test
    public void queryPlantCatalog() {
        assertThat(plantInventoryEntryRepository.count()).isEqualTo(14l);
    }

    @Test
    public void queryByName() {
        assertThat(plantInventoryEntryRepository.findByNameContaining("Mini").size()).isEqualTo(2);
    }

    @Test
    public void findAvailableTest() {
        PlantInventoryEntry entry = plantInventoryEntryRepository.findOne(1l);
        PlantInventoryItem item = plantInventoryItemRepository.findOneByPlantInfo(entry);

        assertThat(inventoryRepository.findAvailablePlants(LocalDate.of(2017,2,20), LocalDate.of(2017,2,25))).contains(entry);

        PlantReservation po = new PlantReservation();
        po.setPlant(item);
        po.setSchedule(BusinessPeriod.of(LocalDate.of(2017, 2, 20), LocalDate.of(2017, 2, 25)));
        plantReservationRepository.save(po);

        assertThat(inventoryRepository.findAvailablePlants(LocalDate.of(2017,2,20), LocalDate.of(2017,2,25))).doesNotContain(entry);
    }

}
