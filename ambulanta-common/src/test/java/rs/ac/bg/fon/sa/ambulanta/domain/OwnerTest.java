package rs.ac.bg.fon.sa.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.ac.bg.fon.sa.ambulanta.domain.GenericEntity;
import rs.ac.bg.fon.sa.ambulanta.domain.Owner;

class OwnerTest {

	Owner o, o1;
	 
	@BeforeEach
	void setUp() throws Exception {
		o = new Owner();
		o1 = new Owner(1L, "Petar", "Petrovic", "1597536548526", false, "063945612", "petar@gmail.com",
				"Sarajevska 39");
	}
 
	@AfterEach
	void tearDown() throws Exception {
		o = null;
		o1 = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testOwner() {
		assertNotNull(o);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testOwnerLongStringStringStringBooleanStringStringString() {
		assertNotNull(o1);
		assertEquals(1L, o1.getId());
		assertEquals("Petar", o1.getFirstname());
		assertEquals("Petrovic", o1.getLastname());
		assertEquals("1597536548526", o1.getJmbg());
		assertEquals(false, o1.getLoyaltyCard());
		assertEquals("063945612", o1.getPhone());
		assertEquals("petar@gmail.com", o1.getEmail());
		assertEquals("Sarajevska 39", o1.getAddress());
	}
 
	@Test
	void testSetId() {
		o.setId(5L);
		assertEquals(5L, o.getId());
	}
 
	@Test
	void testSetFirstnameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> o.setFirstname(null));
		assertThrows(IllegalArgumentException.class, () -> o.setFirstname(""));
	}
 
	@Test
	void testSetFirstnameInvalid() {
		assertThrows(IllegalArgumentException.class, () -> o.setFirstname("Petar123"));
	}
 
	@Test
	void testSetFirstnameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> o.setFirstname("a".repeat(51)));
	}
 
	@Test
	void testSetFirstname() {
		o.setFirstname("Petar");
		assertEquals("Petar", o.getFirstname());
	}
 
	@Test
	void testSetLastnameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> o.setLastname(null));
		assertThrows(IllegalArgumentException.class, () -> o.setLastname(""));
	}
 
	@Test
	void testSetLastnameInvalid() {
		assertThrows(IllegalArgumentException.class, () -> o.setLastname("Petrovic123"));
	}
 
	@Test
	void testSetLastnameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> o.setLastname("a".repeat(51)));
	}
 
	@Test
	void testSetLastname() {
		o.setLastname("Petrovic");
		assertEquals("Petrovic", o.getLastname());
	}
 
	@Test
	void testSetJmbgNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> o.setJmbg(null));
		assertThrows(IllegalArgumentException.class, () -> o.setJmbg(""));
	}
 
	@ParameterizedTest
	@CsvSource({
		"12345",
		"123456789012345"
	})
	void testSetJmbgInvalidLength(String jmbg) {
		assertThrows(IllegalArgumentException.class, () -> o.setJmbg(jmbg));
	}
 
	@Test
	void testSetJmbgNotDigits() {
		assertThrows(IllegalArgumentException.class, () -> o.setJmbg("159753654852a"));
	}
 
	@Test
	void testSetJmbg() {
		o.setJmbg("1597536548526");
		assertEquals("1597536548526", o.getJmbg());
	}
 
	@Test
	void testSetLoyaltyCardNull() {
		assertThrows(NullPointerException.class, () -> o.setLoyaltyCard(null));
	}
 
	@Test
	void testSetLoyaltyCard() {
		o.setLoyaltyCard(true);
		assertEquals(true, o.getLoyaltyCard());
	}
 
	@Test
	void testSetPhoneNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> o.setPhone(null));
		assertThrows(IllegalArgumentException.class, () -> o.setPhone(""));
	}
 
	@ParameterizedTest
	@CsvSource({
		"06345678",
		"06345678901"
	})
	void testSetPhoneInvalidLength(String phone) {
		assertThrows(IllegalArgumentException.class, () -> o.setPhone(phone));
	}
 
	@Test
	void testSetPhoneNotDigits() {
		assertThrows(IllegalArgumentException.class, () -> o.setPhone("06412a4567"));
	}
 
	@ParameterizedTest
	@CsvSource({
		"063945612",
		"0639456123"
	})
	void testSetPhone(String phone) {
		o.setPhone(phone);
		assertEquals(phone, o.getPhone());
	}
 
	@Test
	void testSetEmailNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> o.setEmail(null));
		assertThrows(IllegalArgumentException.class, () -> o.setEmail(""));
	}
 
	@Test
	void testSetEmailInvalidFormat() {
		assertThrows(IllegalArgumentException.class, () -> o.setEmail("petargmail.com"));
	}
 
	@Test
	void testSetEmailTooLong() {
		String longEmail = "a".repeat(51) + "@gmail.com";
		assertThrows(IllegalArgumentException.class, () -> o.setEmail(longEmail));
	}
 
	@Test
	void testSetEmail() {
		o.setEmail("petar@gmail.com");
		assertEquals("petar@gmail.com", o.getEmail());
	}
 
	@Test
	void testSetAddressNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> o.setAddress(null));
		assertThrows(IllegalArgumentException.class, () -> o.setAddress(""));
	}
 
	@Test
	void testSetAddressTooLong() {
		assertThrows(IllegalArgumentException.class, () -> o.setAddress("a".repeat(101)));
	}
 
	@Test
	void testSetAddress() {
		o.setAddress("Sarajevska 39");
		assertEquals("Sarajevska 39", o.getAddress());
	}
 
	@Test
	void testToString() {
		o.setId(1L);
		o.setFirstname("Petar");
		o.setLastname("Petrovic");
		o.setJmbg("1597536548526");
 
		String expected = "[1597536548526] Petar Petrovic";
		assertEquals(expected, o.toString());
	}
 
	@Test
	void testGetInsertValues() {
		String expected = "'Petar','Petrovic','1597536548526',false,'063945612','petar@gmail.com','Sarajevska 39'";
		assertEquals(expected, o1.getInsertValues());
	}
 
	@Test
	void testSetAttributeValues() {
		String expected = "firstname='Petar',lastname='Petrovic',jmbg='1597536548526',"
				+ "loyaltyCard=false,phone='063945612',email='petar@gmail.com',address='Sarajevska 39'";
		assertEquals(expected, o1.setAttributeValues());
	}
 
	@Test
	void testGetQueryCondition() {
		assertEquals("id=1", o1.getQueryCondition());
	}
 
	@Test
	void testSetIdFromRS() {
		o.setIdFromRS(10L);
		assertEquals(10L, o.getId());
	}
 
	@Test
	void testGetEntityFromResultSet() throws Exception {
		java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);
 
		org.mockito.Mockito.when(rs.getLong("id")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("firstname")).thenReturn("Petar");
		org.mockito.Mockito.when(rs.getString("lastname")).thenReturn("Petrovic");
		org.mockito.Mockito.when(rs.getString("jmbg")).thenReturn("1597536548526");
		org.mockito.Mockito.when(rs.getBoolean("loyaltyCard")).thenReturn(false);
		org.mockito.Mockito.when(rs.getString("phone")).thenReturn("063945612");
		org.mockito.Mockito.when(rs.getString("email")).thenReturn("petar@gmail.com");
		org.mockito.Mockito.when(rs.getString("address")).thenReturn("Sarajevska 39");
 
		GenericEntity result = o.getEntityFromResultSet(rs);
 
		assertTrue(result instanceof Owner);
		Owner resultOwner = (Owner) result;
		assertEquals(1L, resultOwner.getId());
		assertEquals("Petar", resultOwner.getFirstname());
		assertEquals("Petrovic", resultOwner.getLastname());
		assertEquals("1597536548526", resultOwner.getJmbg());
		assertEquals(false, resultOwner.getLoyaltyCard());
		assertEquals("063945612", resultOwner.getPhone());
		assertEquals("petar@gmail.com", resultOwner.getEmail());
		assertEquals("Sarajevska 39", resultOwner.getAddress());
	}


}
