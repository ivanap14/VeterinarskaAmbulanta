package rs.ac.bg.fon.sa.ambulanta.operation.owner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetAllOwnersTest {

	private GetAllOwners so;
	private DbBroker dbBroker;
	private Owner o;

	@BeforeEach
	public void setUp() throws Exception {
		so = new GetAllOwners();
		dbBroker = new DbBroker();

		o = new Owner();
		o.setFirstname("Pera");
		o.setLastname("Peric");
		o.setJmbg("1234567890123");
		o.setLoyaltyCard(false);
		o.setPhone("0611234567");
		o.setEmail("pera@gmail.com");
		o.setAddress("Bulevar oslobodjenja 1");
		o = (Owner) dbBroker.add(o);
		DbConnectionFactory.getInstance().getConnection().commit();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (o != null && o.getId() != null) {
			dbBroker.delete(o);
			DbConnectionFactory.getInstance().getConnection().commit();
		}
		so = null;
		o = null;
		dbBroker = null;
	}

	@Test
	public void testPreconditions() {
		assertDoesNotThrow(() -> so.preconditions(null));
	}

	@Test
	public void testExecuteOneOwner() throws Exception {
		so.execute(null);

		List<Owner> owners = so.getOwners();
		assertNotNull(owners);
		assertFalse(owners.isEmpty());
		assertTrue(owners.contains(o));
	}

	@Test
	public void testExecuteOperationMultipleOwners() throws Exception {
		Owner o2 = new Owner();
		o2.setFirstname("Mika");
		o2.setLastname("Mikic");
		o2.setJmbg("9876543210123");
		o2.setLoyaltyCard(true);
		o2.setPhone("0629876543");
		o2.setEmail("mika@gmail.com");
		o2.setAddress("Knez Mihailova 5");
		o2 = (Owner) dbBroker.add(o2);
		DbConnectionFactory.getInstance().getConnection().commit();

		try {
			so.execute(null);

			List<Owner> owners = so.getOwners();
			assertNotNull(owners);
			assertTrue(owners.size() >= 2);
			assertTrue(owners.contains(o));
			assertTrue(owners.contains(o2));
		} finally {
			if (o2 != null && o2.getId() != null) {
				dbBroker.delete(o2);
				DbConnectionFactory.getInstance().getConnection().commit();
			}
		}
	}

}
