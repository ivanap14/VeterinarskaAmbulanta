package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

class GetAllInterventionsTest {

	 private GetAllInterventions so;
	    private DbBroker dbBroker;
	    private Owner owner;
	    private Animal animal;
	    private Veterinarian veterinarian;
	    private Intervention intervention;

	    @BeforeEach
	    public void setUp() throws Exception {
	        so = new GetAllInterventions();
	        dbBroker = new DbBroker();

	        owner = (Owner) dbBroker.add(new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@getall.com", "Bulevar oslobodjenja 1"));
	        veterinarian = (Veterinarian) dbBroker.add(new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@getall.com", "sifra123"));
	        animal = (Animal) dbBroker.add(new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner));

	        intervention = new Intervention(null, LocalDate.of(2025, 8, 18), "Redovna vakcinacija",10, 5, 1000, 850, veterinarian, animal, new ArrayList<>());
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
	        owner = null;
	        veterinarian = null;
	        dbBroker = null;
	    }

	    @Test
	    public void testPreconditions() {
	        assertDoesNotThrow(() -> so.preconditions(null));
	    }

	    @Test
	    public void testExecuteOneIntervention() throws Exception {
	        so.execute(null);
	        List<Intervention> interventions = so.getInterventions();

	        assertNotNull(interventions);
	        assertFalse(interventions.isEmpty());
	        assertTrue(interventions.contains(intervention));
	    }

	    @Test
	    public void testExecuteOperationMultipleInterventions() throws Exception {
	        Intervention intervention2 = new Intervention(null, LocalDate.of(2025, 8, 19), "Kontrola", 0, 0, 1200, 1200, veterinarian, animal, new ArrayList<>());
	        intervention2 = (Intervention) dbBroker.add(intervention2);
	        DbConnectionFactory.getInstance().getConnection().commit();

	        try {
	            so.execute(null);
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

}
