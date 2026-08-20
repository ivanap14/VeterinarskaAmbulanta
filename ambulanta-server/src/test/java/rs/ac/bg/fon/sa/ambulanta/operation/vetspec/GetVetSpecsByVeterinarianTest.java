package rs.ac.bg.fon.sa.ambulanta.operation.vetspec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

class GetVetSpecsByVeterinarianTest {

	private GetVetSpecsByVeterinarian so;
	private DbBroker dbBroker;
	private Veterinarian veterinarian;
	private Specialization specialization;
	private VetSpec vetSpec;
	private String uniqueSuffix;

	@BeforeEach
	public void setUp() throws Exception {
		so = new GetVetSpecsByVeterinarian();
		dbBroker = new DbBroker();
		uniqueSuffix = String.valueOf(System.currentTimeMillis());

		veterinarian = new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko" + uniqueSuffix + "@getvetspec.com", "sifra123");
		veterinarian = (Veterinarian) dbBroker.add(veterinarian);

		specialization = new Specialization(null, "TestSpec_" + uniqueSuffix, Category.HIRURSKA, "Opis specijalizacije");
		specialization = (Specialization) dbBroker.add(specialization);

		vetSpec = new VetSpec(veterinarian, specialization, LocalDate.of(2015, 6, 20), "Fakultet veterinarske medicine - Beograd");
		dbBroker.add(vetSpec);

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
	public void testPreconditions() {
		assertDoesNotThrow(() -> so.preconditions(null));
	}

	@Test
	public void testExecuteOperationOneVetSpec() throws Exception {
		so.execute(veterinarian);

		List<VetSpec> vetSpecs = so.getVetSpecs();

		assertNotNull(vetSpecs);
		assertFalse(vetSpecs.isEmpty());
		assertEquals(1, vetSpecs.size());
		assertTrue(vetSpecs.contains(vetSpec));
	}

	@Test
	public void testExecuteOperationMultipleVetSpecs() throws Exception {
		Specialization specialization2 = new Specialization(null, "TestSpec2_" + uniqueSuffix, Category.OPSTA, "Drugi opis");
		specialization2 = (Specialization) dbBroker.add(specialization2);
		DbConnectionFactory.getInstance().getConnection().commit();

		VetSpec vetSpec2 = new VetSpec(veterinarian, specialization2, LocalDate.of(2018, 9, 1), "Fakultet veterinarske medicine - Niš");
		dbBroker.add(vetSpec2);
		DbConnectionFactory.getInstance().getConnection().commit();

		try {
			so.execute(veterinarian);

			List<VetSpec> vetSpecs = so.getVetSpecs();

			assertNotNull(vetSpecs);
			assertTrue(vetSpecs.size() >= 2);
			assertTrue(vetSpecs.contains(vetSpec));
			assertTrue(vetSpecs.contains(vetSpec2));
		} finally {
			dbBroker.delete(vetSpec2);
			DbConnectionFactory.getInstance().getConnection().commit();
			dbBroker.delete(specialization2);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
	}

	@Test
	public void testExecuteOperationNoMatchingVeterinarian() throws Exception {
		Veterinarian otherVet = new Veterinarian(null, "Jovan", "Jovanovic", LocalDate.of(1985, 3, 10), "0651234567", "jovan" + uniqueSuffix + "@getvetspec.com", "sifra456");
		otherVet = (Veterinarian) dbBroker.add(otherVet);
		DbConnectionFactory.getInstance().getConnection().commit();

		try {
			so.execute(otherVet);

			List<VetSpec> vetSpecs = so.getVetSpecs();

			assertNotNull(vetSpecs);
			assertTrue(vetSpecs.isEmpty());
		} finally {
			dbBroker.delete(otherVet);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
	}

}
