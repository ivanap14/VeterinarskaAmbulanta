package rs.ac.bg.fon.sa.ambulanta.operation.specialization;

import static org.junit.jupiter.api.Assertions.*;
import rs.ac.bg.fon.sa.ambulanta.domain.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

class AddNewSpecializationTest {

	private AddNewSpecialization so;
	private DbBroker dbBroker;
	private Specialization specialization;
	private String uniqueName;

	@BeforeEach
	public void setUp() throws Exception {
		so = new AddNewSpecialization();
		dbBroker = new DbBroker();
		uniqueName = "TestSpec_" + System.currentTimeMillis();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (specialization != null && specialization.getId() != null) {
			dbBroker.delete(specialization);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
		so = null;
		dbBroker = null;
		specialization = null;
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
	public void testPreconditionsNameAlreadyExists() throws Exception {
		specialization = new Specialization(null, uniqueName, Category.HIRURSKA, "Opis specijalizacije");
		specialization = (Specialization) dbBroker.add(specialization);
		DbConnectionFactory.getInstance().getConnection().commit();

		Specialization duplicate = new Specialization(null, uniqueName, Category.HIRURSKA, "Neki drugi opis");

		assertThrows(Exception.class, () -> so.preconditions(duplicate));
	}

	@Test
	public void testPreconditionsValidObject() {
		specialization = new Specialization(null, uniqueName, Category.HIRURSKA, "Opis specijalizacije");

		assertDoesNotThrow(() -> so.preconditions(specialization));
	}

	@Test
	public void testExecuteOperation() throws Exception {
		specialization = new Specialization(null, uniqueName, Category.HIRURSKA, "Opis specijalizacije");

		so.execute(specialization);

		assertNotNull(so.getSpecialization());
		assertNotNull(so.getSpecialization().getId());
		assertEquals(specialization.getId(), so.getSpecialization().getId());
	}

}
