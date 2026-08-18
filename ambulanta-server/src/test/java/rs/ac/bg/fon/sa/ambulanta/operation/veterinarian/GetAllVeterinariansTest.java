package rs.ac.bg.fon.sa.ambulanta.operation.veterinarian;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

class GetAllVeterinariansTest {

	private GetAllVeterinarians so;
    private DbBroker dbBroker;
    private Veterinarian v;

    @BeforeEach
    public void setUp() throws Exception {
        so = new GetAllVeterinarians();
        dbBroker = new DbBroker();

        v = new Veterinarian();
        v.setFirstname("Marko");
        v.setLastname("Markovic");
        v.setBirthday(LocalDate.of(1990, 5, 20));
        v.setPhone("0641234567");
        v.setEmail("marko@gmail.com");
        v.setPassword("sifra123");
        v = (Veterinarian) dbBroker.add(v);
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {
        if (v != null && v.getId() != null) {
            dbBroker.delete(v);
            DbConnectionFactory.getInstance().getConnection().commit();
        }
        so = null;
        v = null;
        dbBroker = null;
    }

    @Test
    public void testPreconditions() {
        assertDoesNotThrow(() -> so.preconditions(null));
    }

    @Test
    public void testExecuteOneVeterinarian() throws Exception {
        so.execute(null);
        List<Veterinarian> veterinarians = so.getVeterinarians();

        assertNotNull(veterinarians);
        assertFalse(veterinarians.isEmpty());
        assertTrue(veterinarians.contains(v));
    }

    @Test
    public void testExecuteOperationMultipleVeterinarians() throws Exception {
        Veterinarian v2 = new Veterinarian();
        v2.setFirstname("Ilija");
        v2.setLastname("Ilic");
        v2.setBirthday(LocalDate.of(1985, 3, 12));
        v2.setPhone("0651275567");
        v2.setEmail("ilija@gmail.com");
        v2.setPassword("sifra456");
        v2 = (Veterinarian) dbBroker.add(v2);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute(null);
            List<Veterinarian> veterinarians = so.getVeterinarians();

            assertNotNull(veterinarians);
            assertTrue(veterinarians.size() >= 2);
            assertTrue(veterinarians.contains(v));
            assertTrue(veterinarians.contains(v2));
        } finally {
            if (v2 != null && v2.getId() != null) {
                dbBroker.delete(v2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

}
