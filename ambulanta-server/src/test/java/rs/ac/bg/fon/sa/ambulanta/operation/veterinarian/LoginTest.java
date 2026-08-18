package rs.ac.bg.fon.sa.ambulanta.operation.veterinarian;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;

public class LoginTest {

	private Login so;
	private Veterinarian veterinarian, veterinarian2;
	
	@BeforeEach
	public void setUp() throws Exception {
		so = new Login();
		veterinarian = new Veterinarian();
		veterinarian2 = new Veterinarian();
	}

	@AfterEach
	public void tearDown() throws Exception {
		so = null;
		veterinarian = null;
		veterinarian2 = null;
	}

	@Test
	public void testPreconditionsNullObject() {
		assertThrows(java.lang.Exception.class, ()->so.preconditions(null));
	}
	
	@Test
	public void testPreconditionsInvalidObject() {
		assertThrows(java.lang.Exception.class, ()->so.preconditions(new String()));
	}
	

	@ParameterizedTest(name = "{0}")
	@CsvSource({
	    "Pogresna sifra, pera@gmail.com, xxxxxxxx",
	    "Pogresan email, xxxx@xxxxxxxxx, pera1234",
	    "Ispravna sifra i email, pera@gmail.com, pera1234",
	})
	public void testExecuteOperation(String opis, String email, String password) {
		veterinarian2.setEmail(email);
		veterinarian2.setPassword(password);
		
		try {
			
			so.execute(veterinarian2);
		
			assertEquals(email, so.getVeterinarian().getEmail());
			assertEquals(password, so.getVeterinarian().getPassword());
		
		} catch (Exception e) {
			assertEquals("Korisničko ime i/ili šifra nisu ispravni!", e.getMessage());
		}
	}

}
