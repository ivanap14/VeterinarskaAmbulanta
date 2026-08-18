package rs.ac.bg.fon.sa.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.ac.bg.fon.sa.ambulanta.domain.GenericEntity;
import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;

class VeterinarianTest {

	Veterinarian v, v1;
	 
	@BeforeEach
	void setUp() throws Exception {
		v = new Veterinarian();
		v1 = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567",
				"marko@gmail.com", "sifra123");
	}
 
	@AfterEach
	void tearDown() throws Exception {
		v = null;
		v1 = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testVeterinarian() {
		assertNotNull(v);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testVeterinarianLongStringStringLocalDateStringStringString() {
		assertNotNull(v1);
		assertEquals(1L, v1.getId());
		assertEquals("Marko", v1.getFirstname());
		assertEquals("Markovic", v1.getLastname());
		assertEquals(LocalDate.of(1990, 5, 20), v1.getBirthday());
		assertEquals("0641234567", v1.getPhone());
		assertEquals("marko@gmail.com", v1.getEmail());
		assertEquals("sifra123", v1.getPassword());
	}
 
	@Test
	@DisplayName("testEmailPasswordConstructor")
	void testVeterinarianStringString() {
		Veterinarian v2 = new Veterinarian("marko@gmail.com", "sifra123");
 
		assertNotNull(v2);
		assertEquals("marko@gmail.com", v2.getEmail());
		assertEquals("sifra123", v2.getPassword());
	}
 
	@Test
	void testHashCode() {
		Veterinarian v2 = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567",
				"marko@gmail.com", "sifra123");
		Veterinarian v3 = new Veterinarian(2L, "Ana", "Anic", LocalDate.of(1985, 3, 12), "0651234567",
				"ana@gmail.com", "sifra456");
 
		assertEquals(v1.hashCode(), v2.hashCode());
		assertNotEquals(v1.hashCode(), v3.hashCode());
	}
 
	@Test
	void testSetId() {
		v.setId(5L);
		assertEquals(5L, v.getId());
	}
 
	@Test
	void testSetFirstnameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> v.setFirstname(null));
		assertThrows(IllegalArgumentException.class, () -> v.setFirstname(""));
	}
 
	@Test
	void testSetFirstnameInvalid() {
		assertThrows(IllegalArgumentException.class, () -> v.setFirstname("Marko123"));
	}
 
	@Test
	void testSetFirstnameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> v.setFirstname("a".repeat(51)));
	}
 
	@Test
	void testSetFirstname() {
		v.setFirstname("Marko");
		assertEquals("Marko", v.getFirstname());
	}
 
	@Test
	void testSetLastnameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> v.setLastname(null));
		assertThrows(IllegalArgumentException.class, () -> v.setLastname(""));
	}
 
	@Test
	void testSetLastnameInvalid() {
		assertThrows(IllegalArgumentException.class, () -> v.setLastname("Markovic123"));
	}
 
	@Test
	void testSetLastnameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> v.setLastname("a".repeat(51)));
	}
 
	@Test
	void testSetLastname() {
		v.setLastname("Markovic");
		assertEquals("Markovic", v.getLastname());
	}
 
	@Test
	void testSetBirthdayNull() {
		assertThrows(IllegalArgumentException.class, () -> v.setBirthday(null));
	}
 
	@Test
	void testSetBirthdayFuture() {
		assertThrows(IllegalArgumentException.class, () -> v.setBirthday(LocalDate.now().plusDays(1)));
	}
 
	@Test
	void testSetBirthday() {
		v.setBirthday(LocalDate.of(1990, 5, 20));
		assertEquals(LocalDate.of(1990, 5, 20), v.getBirthday());
	}
 
	@Test
	void testSetPhoneNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> v.setPhone(null));
		assertThrows(IllegalArgumentException.class, () -> v.setPhone(""));
	}
 
	@ParameterizedTest
	@CsvSource({
		"12345678",
		"12345678901"
	})
	void testSetPhoneInvalidLength(String phone) {
		assertThrows(IllegalArgumentException.class, () -> v.setPhone(phone));
	}
 
	@Test
	void testSetPhoneNotDigits() {
		assertThrows(IllegalArgumentException.class, () -> v.setPhone("06412a4567"));
	}
 
	@ParameterizedTest
	@CsvSource({
		"064123456",
		"0641234567"
	})
	void testSetPhone(String phone) {
		v.setPhone(phone);
		assertEquals(phone, v.getPhone());
	}
 
	@Test
	void testSetEmailNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> v.setEmail(null));
		assertThrows(IllegalArgumentException.class, () -> v.setEmail(""));
	}
 
	@Test
	void testSetEmailInvalidFormat() {
		assertThrows(IllegalArgumentException.class, () -> v.setEmail("markogmail.com"));
	}
 
	@Test
	void testSetEmailTooLong() {
		String longEmail = "a".repeat(51) + "@gmail.com";
		assertThrows(IllegalArgumentException.class, () -> v.setEmail(longEmail));
	}
 
	@Test
	void testSetEmail() {
		v.setEmail("marko@gmail.com");
		assertEquals("marko@gmail.com", v.getEmail());
	}
 

	@ParameterizedTest
	@CsvSource({
		"1234567"
	})
	void testSetPasswordTooShort(String password) {
		assertThrows(IllegalArgumentException.class, () -> v.setPassword(password));
	}
 
	@Test
	void testSetPasswordTooLong() {
		assertThrows(IllegalArgumentException.class, () -> v.setPassword("a".repeat(61)));
	}
 
	@Test
	void testSetPassword() {
		v.setPassword("sifra123");
		assertEquals("sifra123", v.getPassword());
	}
 
	@Test
	void testToString() {
		v.setId(1L);
		v.setFirstname("Marko");
		v.setLastname("Markovic");
 
		String s = v.toString();
 
		assertEquals("[1] Marko Markovic", s);
	}
 
	@Test
	void testEqualsObject() {
		Veterinarian v2 = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567",
				"marko@gmail.com", "sifra123");
		Veterinarian v3 = new Veterinarian(2L, "Ana", "Anic", LocalDate.of(1985, 3, 12), "0651234567",
				"ana@gmail.com", "sifra456");
 
		assertTrue(v1.equals(v2));
		assertFalse(v1.equals(v3));
	}
 
	@Test
	void testEqualsNull() {
		assertFalse(v1.equals(null));
	}
 
	@Test
	void testEqualsInvalidClass() {
		assertFalse(v1.equals(new String()));
	}
 
	@Test
	void testGetEntityFromResultSet() throws Exception {
		java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);
 
		org.mockito.Mockito.when(rs.getLong("id")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("firstname")).thenReturn("Marko");
		org.mockito.Mockito.when(rs.getString("lastname")).thenReturn("Markovic");
		org.mockito.Mockito.when(rs.getDate("birthday"))
				.thenReturn(java.sql.Date.valueOf(LocalDate.of(1990, 5, 20)));
		org.mockito.Mockito.when(rs.getString("phone")).thenReturn("0641234567");
		org.mockito.Mockito.when(rs.getString("email")).thenReturn("marko@gmail.com");
		org.mockito.Mockito.when(rs.getString("password")).thenReturn("sifra123");
 
		GenericEntity result = v.getEntityFromResultSet(rs);
 
		assertTrue(result instanceof Veterinarian);
		Veterinarian resultVeterinarian = (Veterinarian) result;
		assertEquals(1L, resultVeterinarian.getId());
		assertEquals("Marko", resultVeterinarian.getFirstname());
		assertEquals("Markovic", resultVeterinarian.getLastname());
		assertEquals(LocalDate.of(1990, 5, 20), resultVeterinarian.getBirthday());
		assertEquals("0641234567", resultVeterinarian.getPhone());
		assertEquals("marko@gmail.com", resultVeterinarian.getEmail());
		assertEquals("sifra123", resultVeterinarian.getPassword());
	}

	
	@Test
	void testGetInsertValues() {
		String expected = "'Marko','Markovic','1990-05-20','0641234567','marko@gmail.com','sifra123'";
		assertEquals(expected, v1.getInsertValues());
	}
 
	@Test
	void testSetIdFromRS() {
		v.setIdFromRS(10L);
		assertEquals(10L, v.getId());
	}
 
	@Test
	void testSetAttributeValues() {
		String expected = "firstname='Marko',lastname='Markovic',birthday='1990-05-20',phone='0641234567',"
				+ "email='marko@gmail.com',password='sifra123'";
		assertEquals(expected, v1.setAttributeValues());
	}
 
	@Test
	void testGetQueryCondition() {
		assertEquals("id=1", v1.getQueryCondition());
	}


}
