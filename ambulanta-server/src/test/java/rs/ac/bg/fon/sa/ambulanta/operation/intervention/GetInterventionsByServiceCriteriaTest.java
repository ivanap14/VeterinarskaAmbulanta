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

class GetInterventionsByServiceCriteriaTest {

	private GetInterventionsByServiceCriteria so;
    private DbBroker dbBroker;
    private Owner owner;
    private Animal animal;
    private Veterinarian veterinarian;
    private Service service;
    private Intervention intervention;
    private InterventionItem interventionItem;

    @BeforeEach
    public void setUp() throws Exception {
        so = new GetInterventionsByServiceCriteria();
        dbBroker = new DbBroker();

        owner = (Owner) dbBroker.add(new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@servicecriteria.com", "Bulevar oslobodjenja 1"));
        veterinarian = (Veterinarian) dbBroker.add(new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@servicecriteria.com", "sifra123"));
        animal = (Animal) dbBroker.add(new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner));

        service = (Service) dbBroker.add(new Service(null, "Vakcinacija", 100.0, "Vakcinacija psa"));

        intervention = new Intervention(null, LocalDate.of(2025, 8, 18), "Vakcinacija", 10, 5, 1000.0, 850.0, veterinarian, animal, new ArrayList<>());
        intervention = (Intervention) dbBroker.add(intervention);

        interventionItem = new InterventionItem(intervention, 1, 1000.0, 1, 1000.0, service);
        dbBroker.add(interventionItem);

        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (interventionItem != null && intervention != null && intervention.getId() != null) {
            dbBroker.delete(interventionItem);
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
        interventionItem = null;
        intervention = null;
        service = null;
        animal = null;
        veterinarian = null;
        owner = null;
        dbBroker = null;
    }

    @Test
    public void testPreconditions() {
        assertDoesNotThrow(() -> so.preconditions(null));
    }

    @Test
    public void testExecuteOperationByServiceCriteriaFull() throws Exception {
        so.execute("Vakcinacija");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.size() >= 1);
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationByServiceNamePartial() throws Exception {
        so.execute("Vakc");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.size() >= 1);
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationMultipleInterventionsByServiceCriteria() throws Exception {
        Intervention intervention2 = new Intervention(null, LocalDate.of(2025, 8, 19), "Druga vakcinacija", 0, 0, 1000.0, 1000.0, veterinarian, animal, new ArrayList<>());
        intervention2 = (Intervention) dbBroker.add(intervention2);

        InterventionItem interventionItem2 = new InterventionItem(intervention2, 1, 1000.0, 1, 1000.0, service);
        dbBroker.add(interventionItem2);

        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute("Vakcinacija");

            List<Intervention> interventions = so.getInterventions();

            assertNotNull(interventions);
            assertTrue(interventions.size() >= 2);
            assertTrue(interventions.contains(intervention));
            assertTrue(interventions.contains(intervention2));
        } finally {
            if (interventionItem2 != null) {
                dbBroker.delete(interventionItem2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }

            if (intervention2 != null && intervention2.getId() != null) {
                dbBroker.delete(intervention2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

    @Test
    public void testExecuteOperationNoMatchingService() throws Exception {
        so.execute("NepostojecaUsluga");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertTrue(interventions.isEmpty());
    }

}
