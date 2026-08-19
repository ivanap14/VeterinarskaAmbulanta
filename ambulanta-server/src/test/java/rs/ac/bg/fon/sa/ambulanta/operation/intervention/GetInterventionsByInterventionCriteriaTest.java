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

class GetInterventionsByInterventionCriteriaTest {

	private GetInterventionsByInterventionCriteria so;
    private DbBroker dbBroker;
    private Owner owner;
    private Animal animal;
    private Veterinarian veterinarian;
    private Intervention intervention;

    @BeforeEach
    public void setUp() throws Exception {
        so = new GetInterventionsByInterventionCriteria();
        dbBroker = new DbBroker();

        owner = (Owner) dbBroker.add(new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@interventioncriteria.com", "Bulevar oslobodjenja 1"));
        veterinarian = (Veterinarian) dbBroker.add(new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@interventioncriteria.com", "sifra123"));
        animal = (Animal) dbBroker.add(new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner));

        intervention = new Intervention(null, LocalDate.of(2025, 8, 18), "Vakcinacija", 10, 5, 10000.0, 7000.0, veterinarian, animal, new ArrayList<>());
        intervention = (Intervention) dbBroker.add(intervention);

        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (intervention != null && intervention.getId() != null) {
            dbBroker.delete(intervention);
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
    public void testExecuteOperationById() throws Exception {
        so.execute(String.valueOf(intervention.getId()));

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertEquals(1, interventions.size());
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationByDate() throws Exception {
        so.execute("2025-08-18Date");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.size() >= 1);
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationByAmountGreaterThan() throws Exception {
        so.execute("5000>");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.size() >= 1);
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationByAmountLessThan() throws Exception {
        so.execute("8000<");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertFalse(interventions.isEmpty());
        assertTrue(interventions.size() >= 1);
        assertTrue(interventions.contains(intervention));
    }

    @Test
    public void testExecuteOperationMultipleByDate() throws Exception {
        Intervention intervention2 = new Intervention(null, LocalDate.of(2025, 8, 18), "Kontrola", 0, 0, 1200.0, 1200.0, veterinarian, animal, new ArrayList<>());
        intervention2 = (Intervention) dbBroker.add(intervention2);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute("2025-08-18Date");

            List<Intervention> interventions = so.getInterventions();

            assertNotNull(interventions);
            assertTrue(interventions.size() >= 2);
            assertTrue(interventions.contains(intervention));
            assertTrue(interventions.contains(intervention2));
        } finally {
            if (intervention2 != null && intervention2.getId() != null) {
                dbBroker.delete(intervention2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

    @Test
    public void testExecuteOperationMultipleByAmountGreaterThan() throws Exception {
        Intervention intervention2 = new Intervention(null, LocalDate.of(2025, 8, 19), "Kontrola", 0, 0, 6000.0, 6000.0, veterinarian, animal, new ArrayList<>());
        intervention2 = (Intervention) dbBroker.add(intervention2);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute("5000>");

            List<Intervention> interventions = so.getInterventions();

            assertNotNull(interventions);
            assertTrue(interventions.size() >= 2);
            assertTrue(interventions.contains(intervention));
            assertTrue(interventions.contains(intervention2));
        } finally {
            if (intervention2 != null && intervention2.getId() != null) {
                dbBroker.delete(intervention2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

    @Test
    public void testExecuteOperationMultipleByAmountLessThan() throws Exception {
        Intervention intervention2 = new Intervention(null, LocalDate.of(2025, 8, 19), "Kontrola", 0, 0, 1200.0, 1200.0, veterinarian, animal, new ArrayList<>());
        intervention2 = (Intervention) dbBroker.add(intervention2);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute("8000<");

            List<Intervention> interventions = so.getInterventions();

            assertNotNull(interventions);
            assertTrue(interventions.size() >= 2);
            assertTrue(interventions.contains(intervention));
            assertTrue(interventions.contains(intervention2));
        } finally {
            if (intervention2 != null && intervention2.getId() != null) {
                dbBroker.delete(intervention2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }
    
    @Test
    public void testExecuteOperationNoMatchingId() throws Exception {
        so.execute("0");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertTrue(interventions.isEmpty());
    }
    
    @Test
    public void testExecuteOperationNoMatchingFutureDate() throws Exception {
        String futureDate = LocalDate.now().plusYears(1).toString() + "Date";

        so.execute(futureDate);

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertTrue(interventions.isEmpty());
    }
    
    @Test
    public void testExecuteOperationNoMatchingAmountLessThan() throws Exception {
        so.execute("0<");

        List<Intervention> interventions = so.getInterventions();

        assertNotNull(interventions);
        assertTrue(interventions.isEmpty());
    }

}
