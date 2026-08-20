package rs.ac.bg.fon.sa.ambulanta.operation.vetspec;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddNewVetSpecTest {
	
	private AddNewVetSpec so;
	private DbBroker dbBroker;
	private Veterinarian veterinarian;
	private Specialization specialization;
	private VetSpec vetSpec;
	private String uniqueSuffix;

	@BeforeEach
	public void setUp() throws Exception {
		so = new AddNewVetSpec();
		dbBroker = new DbBroker();
		uniqueSuffix = String.valueOf(System.currentTimeMillis());

		veterinarian = new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko" + uniqueSuffix + "@gmail.com", "sifra123");
		veterinarian = (Veterinarian) dbBroker.add(veterinarian);

		specialization = new Specialization(null, "TestSpec_" + uniqueSuffix, Category.HIRURSKA, "Opis specijalizacije");
		specialization = (Specialization) dbBroker.add(specialization);

		DbConnectionFactory.getInstance().getConnection().commit();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (vetSpec != null) {
			dbBroker.delete(vetSpec);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (specialization != null && specialization.getId() != null) {
			dbBroker.delete(specialization);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (veterinarian != null && veterinarian.getId() != null) {
			dbBroker.delete(veterinarian);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		so = null;
		dbBroker = null;
		veterinarian = null;
		specialization = null;
		vetSpec = null;
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
	public void testPreconditionsVetSpecAlreadyExists() throws Exception {
		vetSpec = new VetSpec(veterinarian, specialization, LocalDate.of(2015, 6, 20), "Fakultet veterinarske medicine");
		dbBroker.add(vetSpec);
		DbConnectionFactory.getInstance().getConnection().commit();

		VetSpec duplicate = new VetSpec(veterinarian, specialization, LocalDate.of(2015, 6, 20), "Fakultet veterinarske medicine");

		assertThrows(Exception.class, () -> so.preconditions(duplicate));
	}

	@Test
	public void testPreconditionsValidObject() {
		vetSpec = new VetSpec(veterinarian, specialization, LocalDate.of(2015, 6, 20), "Fakultet veterinarske medicine");

		assertDoesNotThrow(() -> so.preconditions(vetSpec));
	}

	@Test
	public void testExecuteOperation() throws Exception {
		vetSpec = new VetSpec(veterinarian, specialization, LocalDate.of(2015, 6, 20), "Fakultet veterinarske medicine");

		so.execute(vetSpec);

		assertNotNull(so.getVetSpec());
		assertEquals(veterinarian.getId(), so.getVetSpec().getVeterinarian().getId());
		assertEquals(specialization.getId(), so.getVetSpec().getSpecialization().getId());
	}
	
}
