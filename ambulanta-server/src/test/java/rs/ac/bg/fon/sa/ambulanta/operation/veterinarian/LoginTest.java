package rs.ac.bg.fon.sa.ambulanta.operation.veterinarian;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

public class LoginTest {

	private Login so;
    private Veterinarian veterinarian;
    private DbBroker dbBroker;
 
    @BeforeEach
    public void setUp() throws Exception {
        so = new Login();
        dbBroker = new DbBroker();
 
        veterinarian = new Veterinarian();
        veterinarian.setFirstname("Marko");
        veterinarian.setLastname("Markovic");
        veterinarian.setBirthday(java.time.LocalDate.of(1990, 5, 20));
        veterinarian.setPhone("0641234567");
        veterinarian.setEmail("marko@gmail.com");
        veterinarian.setPassword("sifra123");
 
        // Ubacivanje veterinara u bazu - add() vraca entitet sa popunjenim generisanim id-jem
        veterinarian = (Veterinarian) dbBroker.add(veterinarian);
        DbConnectionFactory.getInstance().getConnection().commit();
    }
 
    @AfterEach
    public void tearDown() throws Exception {
        // Brisanje istog veterinara iz baze
        dbBroker.delete(veterinarian);
        DbConnectionFactory.getInstance().getConnection().commit();
 
        so = null;
        veterinarian = null;
        dbBroker = null;
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
    public void testExecuteOperationValidCredentials() throws Exception {
        Veterinarian toLogin = new Veterinarian();
        toLogin.setEmail(veterinarian.getEmail());
        toLogin.setPassword(veterinarian.getPassword());
 
        so.execute(toLogin);
 
        assertEquals(veterinarian.getEmail(), so.getVeterinarian().getEmail());
        assertEquals(veterinarian.getPassword(), so.getVeterinarian().getPassword());
    }
 
    @Test
    public void testExecuteOperationInvalidEmail() {
        Veterinarian toLogin = new Veterinarian();
        toLogin.setEmail("xxxx@xxxxxxxxx");
        toLogin.setPassword(veterinarian.getPassword());
 
        assertThrows(Exception.class, () -> so.execute(toLogin));
    }
 
    @Test
    public void testExecuteOperationInvalidPassword() {
        Veterinarian toLogin = new Veterinarian();
        toLogin.setEmail(veterinarian.getEmail());
        toLogin.setPassword("xxxxxxxx");
 
        assertThrows(Exception.class, () -> so.execute(toLogin));
    }


}
