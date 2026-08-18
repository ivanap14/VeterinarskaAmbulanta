package rs.ac.bg.fon.sa.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import rs.ac.bg.fon.sa.ambulanta.domain.Animal;
import rs.ac.bg.fon.sa.ambulanta.domain.Gender;
import rs.ac.bg.fon.sa.ambulanta.domain.GenericEntity;
import rs.ac.bg.fon.sa.ambulanta.domain.Intervention;
import rs.ac.bg.fon.sa.ambulanta.domain.InterventionItem;
import rs.ac.bg.fon.sa.ambulanta.domain.Owner;
import rs.ac.bg.fon.sa.ambulanta.domain.Species;
import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;

class InterventionTest {

	Intervention i, i1;
	Owner o;
	Animal a;
	Veterinarian v;
	List<InterventionItem> items;
 
	@BeforeEach
	void setUp() throws Exception {
		o = new Owner(1L, "Petar", "Petrovic", "1597536548526", false, "063945612", "petar@gmail.com",
				"Sarajevska 39");
		a = new Animal(1L, "Bobi", Species.PAS, 2020, Gender.MUSKI, o);
		v = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567",
				"marko@gmail.com", "sifra123");
		items = new ArrayList<>();
 
		i = new Intervention();
		i1 = new Intervention(1L, LocalDate.of(2024, 1, 10), "Redovni pregled", 10, 5, 1000.0, 900.0, v, a,
				items);
	}
 
	@AfterEach
	void tearDown() throws Exception {
		i = null;
		i1 = null;
		o = null;
		a = null;
		v = null;
		items = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testIntervention() {
		assertNotNull(i);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testInterventionLongLocalDateStringIntIntDoubleDoubleVeterinarianAnimalList() {
		assertNotNull(i1);
		assertEquals(LocalDate.of(2024, 1, 10), i1.getDate());
		assertEquals("Redovni pregled", i1.getNotes());
		assertEquals(10, i1.getDiscountForLoyalty());
		assertEquals(5, i1.getDiscountForNumberOfServices());
		assertEquals(1000.0, i1.getTotalAmountWithoutDiscount());
		assertEquals(900.0, i1.getTotalAmountWithDiscount());
		assertEquals(v, i1.getVeterinarian());
		assertEquals(a, i1.getAnimal());
		assertEquals(items, i1.getInterventionItems());
	}
 
	@Test
	void testHashCode() {
		Intervention i2 = new Intervention(1L, LocalDate.of(2024, 1, 10), "Redovni pregled", 10, 5, 1000.0,
				900.0, v, a, items);
		Intervention i3 = new Intervention(2L, LocalDate.of(2024, 2, 15), "Vakcinacija", 0, 0, 500.0, 500.0,
				v, a, items);
 
		assertEquals(i1.hashCode(), i2.hashCode());
		assertNotEquals(i1.hashCode(), i3.hashCode());
	}
 
	@Test
	void testSetId() {
		i.setId(5L);
		assertEquals(5L, i.getId());
	}
 
	@Test
	void testSetDateNull() {
		assertThrows(NullPointerException.class, () -> i.setDate(null));
	}
 
	@Test
	void testSetDateFuture() {
		assertThrows(IllegalArgumentException.class, () -> i.setDate(LocalDate.now().plusDays(1)));
	}
 
	@Test
	void testSetDate() {
		i.setDate(LocalDate.of(2024, 1, 10));
		assertEquals(LocalDate.of(2024, 1, 10), i.getDate());
	}
 
	@Test
	void testSetNotesTooLong() {
		assertThrows(IllegalArgumentException.class, () -> i.setNotes("a".repeat(256)));
	}
 
	@Test
	void testSetNotes() {
		i.setNotes("Redovan pregled");
		assertEquals("Redovan pregled", i.getNotes());
	}
 
	@Test
	void testSetNotesNull() {
		i.setNotes(null);
		assertNull(i.getNotes());
	}
 
	@ParameterizedTest
	@CsvSource({
		"-1",
		"101"
	})
	void testSetDiscountForLoyaltyInvalid(int discount) {
		assertThrows(IllegalArgumentException.class, () -> i.setDiscountForLoyalty(discount));
	}
 
	@Test
	void testSetDiscountForLoyalty() {
		i.setDiscountForLoyalty(15);
		assertEquals(15, i.getDiscountForLoyalty());
	}
 
	@ParameterizedTest
	@CsvSource({
		"-1",
		"101"
	})
	void testSetDiscountForNumberOfServicesInvalid(int discount) {
		assertThrows(IllegalArgumentException.class, () -> i.setDiscountForNumberOfServices(discount));
	}
 
	@Test
	void testSetDiscountForNumberOfServices() {
		i.setDiscountForNumberOfServices(20);
		assertEquals(20, i.getDiscountForNumberOfServices());
	}
 
	@Test
	void testSetTotalAmountWithoutDiscountNegative() {
		assertThrows(IllegalArgumentException.class, () -> i.setTotalAmountWithoutDiscount(-100));
	}
 
	@Test
	void testSetTotalAmountWithoutDiscount() {
		i.setTotalAmountWithoutDiscount(1000);
		assertEquals(1000, i.getTotalAmountWithoutDiscount());
	}
 
	@Test
	void testSetTotalAmountWithDiscountNegative() {
		assertThrows(IllegalArgumentException.class, () -> i.setTotalAmountWithDiscount(-100));
	}
 
	@Test
	void testSetTotalAmountWithDiscount() {
		i.setTotalAmountWithDiscount(900);
		assertEquals(900, i.getTotalAmountWithDiscount());
	}
 
	@Test
	void testSetVeterinarianNull() {
		assertThrows(NullPointerException.class, () -> i.setVeterinarian(null));
	}
 
	@Test
	void testSetVeterinarian() {
		i.setVeterinarian(v);
		assertEquals(v, i.getVeterinarian());
	}
 
	@Test
	void testSetAnimalNull() {
		assertThrows(NullPointerException.class, () -> i.setAnimal(null));
	}
 
	@Test
	void testSetAnimal() {
		i.setAnimal(a);
		assertEquals(a, i.getAnimal());
	}
 
	@Test
	void testSetInterventionItems() {
		i.setInterventionItems(items);
		assertEquals(items, i.getInterventionItems());
	}
 
	@Test
	void testEqualsObject() {
		Intervention i2 = new Intervention(1L, LocalDate.of(2024, 1, 10), "Redovni pregled", 10, 5, 1000.0,
				900.0, v, a, items);
		Intervention i3 = new Intervention(2L, LocalDate.of(2024, 2, 15), "Vakcinacija", 0, 0, 500.0, 500.0,
				v, a, items);
 
		assertTrue(i1.equals(i2));
		assertFalse(i1.equals(i3));
	}
 
	@Test
	void testEqualsNull() {
		assertFalse(i1.equals(null));
	}
 
	@Test
	void testEqualsInvalidClass() {
		assertFalse(i1.equals(new String()));
	}
 
	@Test
	void testGetTableName() {
		assertEquals("intervention", i1.getTableName());
	}
 
	@Test
	void testGetTableAlias() {
		assertEquals("i", i1.getTableAlias());
	}
 
	@Test
	void testGetInsertValues() {
		String expected = "'2024-01-10','Redovni pregled',10,5,1000.0,900.0,1,1";
		assertEquals(expected, i1.getInsertValues());
	}
 
	@Test
	void testSetAttributeValues() {
		String expected = "date='2024-01-10',notes='Redovni pregled',discountForLoyalty=10,"
				+ "discountForNumberOfServices=5,totalAmountWithoutDiscount=1000.0,"
				+ "totalAmountWithDiscount=900.0,idVeterinarian=1,idAnimal=1";
		assertEquals(expected, i1.setAttributeValues());
	}
 
	@Test
	void testGetQueryCondition() {
		assertEquals("id=1", i1.getQueryCondition());
	}
 
	@Test
	void testGetJoinQuery() {
		String expected = "INNER JOIN veterinarian v ON i.idVeterinarian=v.id "
				+ "INNER JOIN animal a ON i.idAnimal=a.id INNER JOIN owner o ON a.idOwner=o.id";
		assertEquals(expected, i1.getJoinQuery());
	}
 
	@Test
	void testSetIdFromRs() {
		i.setIdFromRS(10L);
		assertEquals(10L, i.getId());
	}
 
	@Test
	void testGetColumnNamesForInsert() {
		String expected = "date, notes, discountForLoyalty, discountForNumberOfServices, "
				+ "totalAmountWithoutDiscount, totalAmountWithDiscount, idVeterinarian, idAnimal";
		assertEquals(expected, i1.getColumnNamesForInsert());
	}
 
	@Test
	void testGetEntityFromResultSet() throws Exception {
		java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);
 
		org.mockito.Mockito.when(rs.getLong("idOwner")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("o.firstname")).thenReturn("Petar");
		org.mockito.Mockito.when(rs.getString("o.lastname")).thenReturn("Petrovic");
		org.mockito.Mockito.when(rs.getString("o.jmbg")).thenReturn("1597536548526");
		org.mockito.Mockito.when(rs.getBoolean("loyaltyCard")).thenReturn(false);
		org.mockito.Mockito.when(rs.getString("o.phone")).thenReturn("063945612");
		org.mockito.Mockito.when(rs.getString("o.email")).thenReturn("petar@gmail.com");
		org.mockito.Mockito.when(rs.getString("o.address")).thenReturn("Sarajevska 39");
 
		org.mockito.Mockito.when(rs.getLong("idAnimal")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("a.name")).thenReturn("Bobi");
		org.mockito.Mockito.when(rs.getString("a.species")).thenReturn("PAS");
		org.mockito.Mockito.when(rs.getInt("a.yearOfBirth")).thenReturn(2020);
		org.mockito.Mockito.when(rs.getString("a.gender")).thenReturn("MUSKI");
 
		org.mockito.Mockito.when(rs.getLong("idVeterinarian")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getString("v.firstname")).thenReturn("Marko");
		org.mockito.Mockito.when(rs.getString("v.lastname")).thenReturn("Markovic");
		org.mockito.Mockito.when(rs.getDate("v.birthday"))
				.thenReturn(java.sql.Date.valueOf(LocalDate.of(1990, 5, 20)));
		org.mockito.Mockito.when(rs.getString("v.phone")).thenReturn("0641234567");
		org.mockito.Mockito.when(rs.getString("v.email")).thenReturn("marko@gmail.com");
 
		org.mockito.Mockito.when(rs.getLong("i.id")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getDate("i.date"))
				.thenReturn(java.sql.Date.valueOf(LocalDate.of(2024, 1, 10)));
		org.mockito.Mockito.when(rs.getString("i.notes")).thenReturn("Redovni pregled");
		org.mockito.Mockito.when(rs.getInt("i.discountForLoyalty")).thenReturn(10);
		org.mockito.Mockito.when(rs.getInt("i.discountForNumberOfServices")).thenReturn(5);
		org.mockito.Mockito.when(rs.getDouble("i.totalAmountWithoutDiscount")).thenReturn(1000.0);
		org.mockito.Mockito.when(rs.getDouble("i.totalAmountWithDiscount")).thenReturn(900.0);
 
		GenericEntity result = i.getEntityFromResultSet(rs);
 
		assertTrue(result instanceof Intervention);
		Intervention resultIntervention = (Intervention) result;
		assertEquals(1L, resultIntervention.getId());
		assertEquals(LocalDate.of(2024, 1, 10), resultIntervention.getDate());
		assertEquals("Redovni pregled", resultIntervention.getNotes());
		assertEquals(10, resultIntervention.getDiscountForLoyalty());
		assertEquals(5, resultIntervention.getDiscountForNumberOfServices());
		assertEquals(1000.0, resultIntervention.getTotalAmountWithoutDiscount());
		assertEquals(900.0, resultIntervention.getTotalAmountWithDiscount());
		assertEquals("Marko", resultIntervention.getVeterinarian().getFirstname());
		assertEquals("Bobi", resultIntervention.getAnimal().getName());
		assertNotNull(resultIntervention.getInterventionItems());
		assertTrue(resultIntervention.getInterventionItems().isEmpty());
	}

}
