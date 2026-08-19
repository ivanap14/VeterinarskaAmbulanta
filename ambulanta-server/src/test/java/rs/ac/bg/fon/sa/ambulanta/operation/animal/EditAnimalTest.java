package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditAnimalTest {

	private EditAnimal so;
	private DbBroker dbBroker;
	private Owner owner;
	private Animal animal;

	@BeforeEach
	public void setUp() throws Exception {
		so = new EditAnimal();
		dbBroker = new DbBroker();

		owner = new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@gmail.com", "Bulevar oslobodjenja 1");
		owner = (Owner) dbBroker.add(owner);

		animal = new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner);
		animal = (Animal) dbBroker.add(animal);

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
	public void testPreconditionsAnimalDoesNotExist() {
		Animal invalidAnimal = new Animal(0L, "Nepostojeca", Species.PAS, 2020, Gender.MUSKI, owner);

		assertThrows(Exception.class, () -> so.preconditions(invalidAnimal));
	}

	@Test
	public void testPreconditionsValidObject() {
		assertDoesNotThrow(() -> so.preconditions(animal));
		assertEquals(animal.getId(), so.getAnimal().getId());
	}

	@Test
	public void testExecuteOperation() throws Exception {
		so.execute(animal);

		assertNotNull(so.getAnimal());
		assertEquals(animal.getId(), so.getAnimal().getId());
	}

	@Test
	public void testExecuteOperationWithEditedData() throws Exception {
		Animal editedAnimal = new Animal(animal.getId(), "Reks", Species.MACKA, 2019, Gender.ZENSKI, owner);

		so.execute(editedAnimal);

		assertNotNull(so.getAnimal());
		assertEquals(animal.getId(), so.getAnimal().getId());
		assertEquals("Reks", so.getAnimal().getName());
		assertEquals(Species.MACKA, so.getAnimal().getSpecies());
		assertEquals(2019, so.getAnimal().getYearOfBirth());
		assertEquals(Gender.ZENSKI, so.getAnimal().getGender());
	}

}
