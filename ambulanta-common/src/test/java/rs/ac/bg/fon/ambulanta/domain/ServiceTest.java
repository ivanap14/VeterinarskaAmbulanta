package rs.ac.bg.fon.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ServiceTest {

	Service s, s1;
	 
	@BeforeEach
	void setUp() throws Exception {
		s = new Service();
		s1 = new Service(1L, "Vakcinacija", 100.0, "Opis usluge");
	}
 
	@AfterEach
	void tearDown() throws Exception {
		s = null;
		s1 = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testService() {
		assertNotNull(s);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testServiceLongStringDoubleString() {
		assertNotNull(s1);
		assertEquals(1L, s1.getId());
		assertEquals("Vakcinacija", s1.getName());
		assertEquals(100.0, s1.getPrice());
		assertEquals("Opis usluge", s1.getDescription());
	}
 
	@Test
	void testSetId() {
		s.setId(5L);
		assertEquals(5L, s.getId());
	}
 
	@Test
	void testSetNameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> s.setName(null));
		assertThrows(IllegalArgumentException.class, () -> s.setName(""));
	}
 
	@Test
	void testSetNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> s.setName("a".repeat(51)));
	}
 
	@Test
	void testSetName() {
		s.setName("Vakcinacija");
		assertEquals("Vakcinacija", s.getName());
	}
 
	@ParameterizedTest
	@CsvSource({
		"0",
		"-10"
	})
	void testSetPriceInvalid(double price) {
		assertThrows(IllegalArgumentException.class, () -> s.setPrice(price));
	}
 
	@Test
	void testSetPrice() {
		s.setPrice(150.0);
		assertEquals(150.0, s.getPrice());
	}
 
	@Test
	void testSetDescriptionNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> s.setDescription(null));
		assertThrows(IllegalArgumentException.class, () -> s.setDescription(""));
	}
 
	@Test
	void testSetDescriptionTooLong() {
		assertThrows(IllegalArgumentException.class, () -> s.setDescription("a".repeat(256)));
	}
 
	@Test
	void testSetDescription() {
		s.setDescription("Opis usluge");
		assertEquals("Opis usluge", s.getDescription());
	}
 
	@Test
	void testToString() {
		s.setId(1L);
		s.setName("Vakcinacija");
		s.setPrice(100.0);
		s.setDescription("Opis usluge");
 
		assertEquals("Vakcinacija", s.toString());
	}
 
	@Test
	void testSetIdFromRS() {
		s.setIdFromRS(10L);
		assertEquals(10L, s.getId());
	}
 
	@Test
	void testGetEntityFromResultSet() throws Exception {
		java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);
 
		org.mockito.Mockito.when(rs.getLong("id")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("name")).thenReturn("Vakcinacija");
		org.mockito.Mockito.when(rs.getDouble("price")).thenReturn(100.0);
		org.mockito.Mockito.when(rs.getString("description")).thenReturn("Opis usluge");
 
		GenericEntity result = s.getEntityFromResultSet(rs);
 
		assertTrue(result instanceof Service);
		Service resultService = (Service) result;
		assertEquals(1L, resultService.getId());
		assertEquals("Vakcinacija", resultService.getName());
		assertEquals(100.0, resultService.getPrice());
		assertEquals("Opis usluge", resultService.getDescription());
	}


}
