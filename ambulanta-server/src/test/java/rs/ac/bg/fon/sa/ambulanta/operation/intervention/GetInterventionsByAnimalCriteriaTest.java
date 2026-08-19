package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.*;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetInterventionsByAnimalCriteriaTest {

	private GetInterventionsByAnimalCriteria so;
    private DbBroker dbBroker;
    private Owner owner;
    private Animal animal;
    private Animal animal2;
    private Veterinarian veterinarian;
    private Intervention intervention;
    private Intervention intervention2;

    @BeforeEach
    public void setUp() throws Exception {
        so = new GetInterventionsByAnimalCriteria();
        dbBroker = new DbBroker();

        owner = (Owner) dbBroker.add(new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@criteria.com", "Bulevar oslobodjenja 1"));
        veterinarian = (Veterinarian) dbBroker.add(new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@criteria.com", "sifra123"));
        animal = (Animal) dbBroker.add(new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner));
        animal2 = (Animal) dbBroker.add(new Animal(null, "Lara", Species.MACKA, 2021, Gender.ZENSKI, owner));

        intervention = new Intervention(null, LocalDate.of(2025, 8, 18), "Vakcinacija", 10, 5, 1000, 850, veterinarian, animal, new ArrayList<>());
        intervention = (Intervention) dbBroker.add(intervention);

        intervention2 = new Intervention(null, LocalDate.of(2025, 8, 19), "Kontrola", 10, 5, 1000, 850, veterinarian, animal2, new ArrayList<>());
        intervention2 = (Intervention) dbBroker.add(intervention2);

        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (intervention2 != null && intervention2.getId() != null) {
            dbBroker.delete(intervention2);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (intervention != null && intervention.getId() != null) {
            dbBroker.delete(intervention);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        if (animal2 != null && animal2.getId() != null) {
            dbBroker.delete(animal2);
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
        intervention = null;
        intervention2 = null;
        animal = null;
        animal2 = null;
        veterinarian = null;
        owner = null;
        dbBroker = null;
    }

    @Test
    public void testPreconditions() {
        assertDoesNotThrow(() -> so.preconditions(null));
    }

    @Test
    public void testExecuteOperationByAnimalId() throws Exception {
        so.execute(String.valueOf(animal.getId()));

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.contains(intervention));
        assertTrue(interventions.size() >= 1);
    }

    @Test
    public void testExecuteOperationBySpecies() throws Exception {
        so.execute("pasSpecies");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.size() >= 1);
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationMultipleInterventionsByAnimal() throws Exception {
        Intervention intervention3 = new Intervention(null, LocalDate.of(2025, 8, 20), "Pregled", 0, 0, 2000.0, 2000.0, veterinarian, animal, new ArrayList<>());
        intervention3 = (Intervention) dbBroker.add(intervention3);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute(String.valueOf(animal.getId()));

            List<Intervention> interventions = so.getInterventions();

            assertNotNull(interventions);
            assertTrue(interventions.size() >= 2);
            assertTrue(interventions.contains(intervention));
            assertTrue(interventions.contains(intervention3));
        } finally {
            if (intervention3 != null && intervention3.getId() != null) {
                dbBroker.delete(intervention3);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

    @Test
    public void testExecuteOperationMultipleInterventionsBySpecies() throws Exception {
        Intervention intervention3 = new Intervention(null, LocalDate.of(2025, 8, 20), "Pregled psa", 0, 0, 1700.0, 1700.0, veterinarian, animal, new ArrayList<>());
        intervention3 = (Intervention) dbBroker.add(intervention3);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute("pasSpecies");

            List<Intervention> interventions = so.getInterventions();

            assertNotNull(interventions);
            assertTrue(interventions.size() >= 2);
            assertTrue(interventions.contains(intervention));
            assertTrue(interventions.contains(intervention3));
        } finally {
            if (intervention3 != null && intervention3.getId() != null) {
                dbBroker.delete(intervention3);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }
    
    @Test
    public void testExecuteOperationNoMatchingAnimalId() throws Exception {
        so.execute("0");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertTrue(interventions.isEmpty());
    }
    
    @Test
    public void testExecuteOperationNoMatchingSpecies() throws Exception {
        so.execute("nepostojecaSpecies");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertTrue(interventions.isEmpty());
    }

}
