package rs.ac.bg.fon.sa.ambulanta.operation.specialization;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetAllSpecializationsTest {

	private GetAllSpecializations so;
	private DbBroker dbBroker;
	private Specialization sp;

	@BeforeEach
	public void setUp() throws Exception {
		so = new GetAllSpecializations();
		dbBroker = new DbBroker();

		sp = new Specialization();
		sp.setName("TestSpec_" + System.currentTimeMillis());
		sp.setCategory(Category.HIRURSKA);
		sp.setDescription("Opis specijalizacije");
		sp = (Specialization) dbBroker.add(sp);

		DbConnectionFactory.getInstance().getConnection().commit();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (sp != null && sp.getId() != null) {
			dbBroker.delete(sp);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
		so = null;
		sp = null;
		dbBroker = null;
	}

	@Test
	public void testPreconditions() {
		assertDoesNotThrow(() -> so.preconditions(null));
	}

	@Test
	public void testExecuteOneSpecialization() throws Exception {
		so.execute(null);

		List<Specialization> specializations = so.getSpecializations();

		assertNotNull(specializations);
		assertFalse(specializations.isEmpty());
		assertTrue(specializations.contains(sp));
	}

	@Test
	public void testExecuteOperationMultipleSpecializations() throws Exception {
		Specialization sp2 = new Specialization();
		sp2.setName("TestSpec2_" + System.currentTimeMillis());
		sp2.setCategory(Category.OPSTA);
		sp2.setDescription("Drugi opis specijalizacije");
		sp2 = (Specialization) dbBroker.add(sp2);
		DbConnectionFactory.getInstance().getConnection().commit();

		try {
			so.execute(null);

			List<Specialization> specializations = so.getSpecializations();

			assertNotNull(specializations);
			assertTrue(specializations.size() >= 2);
			assertTrue(specializations.contains(sp));
			assertTrue(specializations.contains(sp2));
		} finally {
			if (sp2 != null && sp2.getId() != null) {
				dbBroker.delete(sp2);
				DbConnectionFactory.getInstance().getConnection().commit();
			}
		}
	}

}
