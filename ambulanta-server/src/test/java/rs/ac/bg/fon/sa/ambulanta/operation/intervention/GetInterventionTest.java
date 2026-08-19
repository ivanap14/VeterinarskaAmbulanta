package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;


class GetInterventionTest {

	private GetIntervention so;
    private DbBroker dbBroker;
    private Owner owner;
    private Animal animal;
    private Veterinarian veterinarian;
    private Service service;
    private Intervention intervention;
    private InterventionItem item;

    @BeforeEach
    public void setUp() throws Exception {
        so = new GetIntervention();
        dbBroker = new DbBroker();

        owner = (Owner) dbBroker.add(new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@getintervention.com", "Bulevar oslobodjenja 1"));
        veterinarian = (Veterinarian) dbBroker.add(new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@getintervention.com", "sifra123"));
        animal = (Animal) dbBroker.add(new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner));
        service = (Service) dbBroker.add(new Service(null, "Vakcinacija", 100.0, "Opis usluge"));

        intervention = new Intervention(null, LocalDate.of(2025, 8, 18), "Redovna vakcinacija", 10, 5, 1000.0, 850.0, veterinarian, animal, new ArrayList<>());
        intervention = (Intervention) dbBroker.add(intervention);

        item = new InterventionItem(intervention, 1, 1000.0, 1, 1000.0, service);
        dbBroker.add(item);

        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (item != null) {
            dbBroker.delete(item);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (intervention != null && intervention.getId() != null) {
            dbBroker.delete(intervention);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (service != null && service.getId() != null) {
            dbBroker.delete(service);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (animal != null && animal.getId() != null) {
            dbBroker.delete(animal);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (veterinarian != null && veterinarian.getId() != null) {
            dbBroker.delete(veterinarian);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (owner != null && owner.getId() != null) {
            dbBroker.delete(owner);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        so = null;
        item = null;
        intervention = null;
        service = null;
        animal = null;
        veterinarian = null;
        owner = null;
        dbBroker = null;
    }

    @Test
    public void testPreconditions() {
        assertDoesNotThrow(() -> so.preconditions(intervention));
    }

    @Test
    public void testExecuteOperation() throws Exception {
        so.execute(intervention);

        Intervention result = so.getIntervention();

        assertNotNull(result);
        assertEquals(intervention.getId(), result.getId());
        assertEquals(intervention.getDate(), result.getDate());
        assertEquals(intervention.getNotes(), result.getNotes());
        assertEquals(intervention.getVeterinarian().getId(), result.getVeterinarian().getId());
        assertEquals(intervention.getAnimal().getId(), result.getAnimal().getId());
    }

    @Test
    public void testExecuteOperationLoadsInterventionItems() throws Exception {
        so.execute(intervention);

        Intervention result = so.getIntervention();
        List<InterventionItem> items = result.getInterventionItems();

        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(1, items.size());
        assertEquals(item.getRb(), items.get(0).getRb());
        assertEquals(item.getService().getId(), items.get(0).getService().getId());
        assertEquals(item.getPrice(), items.get(0).getPrice());
        assertEquals(item.getQuantity(), items.get(0).getQuantity());
        assertEquals(item.getAmount(), items.get(0).getAmount());
    }
    
    @Test
    public void testExecuteOperationMultipleInterventionItems() throws Exception {
        Service service2 = (Service) dbBroker.add(new Service(null, "Pregled", 2000.0, "Opis pregleda"));
        InterventionItem item2 = new InterventionItem(intervention, 2, 2000.0, 1, 2000.0, service2);
        dbBroker.add(item2);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute(intervention);

            Intervention result = so.getIntervention();
            List<InterventionItem> items = result.getInterventionItems();

            assertNotNull(items);
            assertEquals(2, items.size());

            assertEquals(item.getRb(), items.get(0).getRb());
            assertEquals(item.getPrice(), items.get(0).getPrice());
            assertEquals(item.getQuantity(), items.get(0).getQuantity());
            assertEquals(item.getAmount(), items.get(0).getAmount());
            assertEquals(item.getService().getId(), items.get(0).getService().getId());

            assertEquals(item2.getRb(), items.get(1).getRb());
            assertEquals(item2.getPrice(), items.get(1).getPrice());
            assertEquals(item2.getQuantity(), items.get(1).getQuantity());
            assertEquals(item2.getAmount(), items.get(1).getAmount());
            assertEquals(item2.getService().getId(), items.get(1).getService().getId());
        } finally {
            dbBroker.delete(item2);
            DbConnectionFactory.getInstance().getConnection().commit();
            dbBroker.delete(service2);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

}
}
