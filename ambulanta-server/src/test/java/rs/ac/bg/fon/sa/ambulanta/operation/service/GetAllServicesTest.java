package rs.ac.bg.fon.sa.ambulanta.operation.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

class GetAllServicesTest {

	 private GetAllServices so;
	    private DbBroker dbBroker;
	    private Service s;

	    @BeforeEach
	    public void setUp() throws Exception {
	        so = new GetAllServices();
	        dbBroker = new DbBroker();

	        s = new Service();
	        s.setName("Vakcinacija");
	        s.setPrice(100.0);
	        s.setDescription("Opis usluge");
	        s = (Service) dbBroker.add(s);
	        DbConnectionFactory.getInstance().getConnection().commit();
	    }

	    @AfterEach
	    public void tearDown() throws Exception {
	        if (s != null && s.getId() != null) {
	            dbBroker.delete(s);
	            DbConnectionFactory.getInstance().getConnection().commit();
	        }
	        so = null;
	        s = null;
	        dbBroker = null;
	    }

	    @Test
	    public void testPreconditions() {
	        assertDoesNotThrow(() -> so.preconditions(null));
	    }

	    @Test
	    public void testExecuteOneService() throws Exception {
	        so.execute(null);
	        List<Service> services = so.getServices();

	        assertNotNull(services);
	        assertFalse(services.isEmpty());
	        assertTrue(services.contains(s));
	    }

	    @Test
	    public void testExecuteOperationMultipleServices() throws Exception {
	        Service s2 = new Service();
	        s2.setName("Šišanje 1");
	        s2.setPrice(50.0);
	        s2.setDescription("Drugi opis usluge");
	        s2 = (Service) dbBroker.add(s2);
	        DbConnectionFactory.getInstance().getConnection().commit();

	        try {
	            so.execute(null);
	            List<Service> services = so.getServices();

	            assertNotNull(services);
	            assertTrue(services.size() >= 2);
	            assertTrue(services.contains(s));
	            assertTrue(services.contains(s2));
	        } finally {
	            if (s2 != null && s2.getId() != null) {
	                dbBroker.delete(s2);
	                DbConnectionFactory.getInstance().getConnection().commit();
	            }
	        }
	    }
}
