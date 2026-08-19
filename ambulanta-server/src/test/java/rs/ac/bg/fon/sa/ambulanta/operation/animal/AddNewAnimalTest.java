package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddNewAnimalTest {

	private AddNewAnimal so;
	private DbBroker dbBroker;
	private Owner owner;
	private Animal animal;

	@BeforeEach
	public void setUp() throws Exception {
		so = new AddNewAnimal();
		dbBroker = new DbBroker();
		owner = new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@gmail.com", "Bulevar oslobodjenja 1");
		owner = (Owner) dbBroker.add(owner);
		DbConnectionFactory.getInstance().getConnection().commit();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (animal != null && animal.getId() != null) {
			dbBroker.delete(animal);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
		if (owner != null && owner.getId() != null) {
			dbBroker.delete(owner);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
		so = null;
		dbBroker = null;
		owner = null;
		animal = null;
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
		animal = new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner);
		assertDoesNotThrow(() -> so.preconditions(animal));
	}

	@Test
	public void testExecuteOperation() throws Exception {
		animal = new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner);
		so.execute(animal);
		assertNotNull(so.getAnimal());
		assertNotNull(so.getAnimal().getId());
		assertEquals(animal.getId(), so.getAnimal().getId());
	}

}
