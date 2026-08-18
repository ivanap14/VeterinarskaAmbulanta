package rs.ac.bg.fon.sa.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.sa.ambulanta.domain.Category;
import rs.ac.bg.fon.sa.ambulanta.domain.GenericEntity;
import rs.ac.bg.fon.sa.ambulanta.domain.Specialization;

class SpecializationTest {

	Specialization sp, sp1;
	Category category;
 
	@BeforeEach
	void setUp() throws Exception {
		category = Category.HIRURSKA;
 
		sp = new Specialization();
		sp1 = new Specialization(1L, "Ortopedska hirurgija", category, "Opis specijalizacije");
	}
 
	@AfterEach
	void tearDown() throws Exception {
		sp = null;
		sp1 = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testSpecialization() {
		assertNotNull(sp);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testSpecializationLongStringCategoryString() {
		assertNotNull(sp1);
		assertEquals(1L, sp1.getId());
		assertEquals("Ortopedska hirurgija", sp1.getName());
		assertEquals(category, sp1.getCategory());
		assertEquals("Opis specijalizacije", sp1.getDescription());
	}
 
	@Test
	void testSetId() {
		sp.setId(5L);
		assertEquals(5L, sp.getId());
	}
 
	@Test
	void testSetNameNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> sp.setName(null));
		assertThrows(IllegalArgumentException.class, () -> sp.setName(""));
	}
 
	@Test
	void testSetNameTooLong() {
		assertThrows(IllegalArgumentException.class, () -> sp.setName("a".repeat(51)));
	}
 
	@Test
	void testSetName() {
		sp.setName("Ortopedska hirurgija");
		assertEquals("Ortopedska hirurgija", sp.getName());
	}
 
	@Test
	void testSetCategoryNull() {
		assertThrows(NullPointerException.class, () -> sp.setCategory(null));
	}
 
	@Test
	void testSetCategory() {
		sp.setCategory(category);
		assertEquals(category, sp.getCategory());
	}
 
	@Test
	void testSetDescriptionNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> sp.setDescription(null));
		assertThrows(IllegalArgumentException.class, () -> sp.setDescription(""));
	}
 
	@Test
	void testSetDescriptionTooLong() {
		assertThrows(IllegalArgumentException.class, () -> sp.setDescription("a".repeat(256)));
	}
 
	@Test
	void testSetDescription() {
		sp.setDescription("Opis specijalizacije");
		assertEquals("Opis specijalizacije", sp.getDescription());
	}
 
	@Test
	void testToString() {
		sp.setId(1L);
		sp.setName("Ortopedska hirurgija");
		sp.setCategory(category);
		sp.setDescription("Opis specijalizacije");
 
		assertEquals("Ortopedska hirurgija", sp.toString());
	}
 
	@Test
	void testGetInsertValues() {
		String expected = "'Ortopedska hirurgija','" + category + "','Opis specijalizacije'";
		assertEquals(expected, sp1.getInsertValues());
	}
 
	@Test
	void testSetIdFromRS() {
		sp.setIdFromRS(10L);
		assertEquals(10L, sp.getId());
	}
 
	@Test
	void testGetEntityFromResultSet() throws Exception {
		java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);
 
		org.mockito.Mockito.when(rs.getLong("id")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("name")).thenReturn("Ortopedska hirurgija");
		org.mockito.Mockito.when(rs.getString("category")).thenReturn(category.name());
		org.mockito.Mockito.when(rs.getString("description")).thenReturn("Opis specijalizacije");
 
		GenericEntity result = sp.getEntityFromResultSet(rs);
 
		assertTrue(result instanceof Specialization);
		Specialization resultSpecialization = (Specialization) result;
		assertEquals(1L, resultSpecialization.getId());
		assertEquals("Ortopedska hirurgija", resultSpecialization.getName());
		assertEquals(category, resultSpecialization.getCategory());
		assertEquals("Opis specijalizacije", resultSpecialization.getDescription());
	}


}
