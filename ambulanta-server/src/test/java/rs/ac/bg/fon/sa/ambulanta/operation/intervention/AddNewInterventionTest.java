package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;
import rs.ac.bg.fon.sa.ambulanta.domain.*;

class AddNewInterventionTest {

	private AddNewIntervention so;
	private DbBroker dbBroker;
	private Owner owner;
	private Animal animal;
	private Veterinarian veterinarian;
	private Intervention intervention;

	@BeforeEach
	public void setUp() throws Exception {
		so = new AddNewIntervention();
		dbBroker = new DbBroker();

		owner = new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@gmail.com", "Bulevar oslobodjenja 1");
		owner = (Owner) dbBroker.add(owner);

		animal = new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner);
		animal = (Animal) dbBroker.add(animal);

		veterinarian = new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@gmail.com", "sifra123");
		veterinarian = (Veterinarian) dbBroker.add(veterinarian);

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

		if (owner != null && owner.getId() != null) {
			dbBroker.delete(owner);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (veterinarian != null && veterinarian.getId() != null) {
			dbBroker.delete(veterinarian);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		so = null;
		dbBroker = null;
		owner = null;
		animal = null;
		veterinarian = null;
		intervention = null;
	}

	@Test
	public void testPreconditionsNullObject() {
		assertThrows(Exception.class, () -> so.preconditions(null));
	}

	@Test
	public void testPreconditionsInvalidObject() {
		assertThrows(Exception.class, () -> so.preconditions(new String()));
	}

	@Test
	public void testPreconditionsValidObject() {
		intervention = new Intervention(null, LocalDate.now(), "Kontrola", 10, 5, 1000, 850, veterinarian, animal, new ArrayList<>());
		assertDoesNotThrow(() -> so.preconditions(intervention));
	}

	@Test
	public void testExecuteOperation() throws Exception {
		intervention = new Intervention(null, LocalDate.now(), "Kontrola", 10, 5, 1000, 850, veterinarian, animal, new ArrayList<>());

		so.execute(intervention);

		assertNotNull(so.getIntervention());
		assertNotNull(so.getIntervention().getId());
		assertEquals(intervention.getId(), so.getIntervention().getId());
	}

}
