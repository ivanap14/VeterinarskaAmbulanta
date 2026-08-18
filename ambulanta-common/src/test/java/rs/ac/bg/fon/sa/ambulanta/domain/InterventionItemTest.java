package rs.ac.bg.fon.sa.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

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
import rs.ac.bg.fon.sa.ambulanta.domain.Service;
import rs.ac.bg.fon.sa.ambulanta.domain.Species;
import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;

class InterventionItemTest {

	InterventionItem ii, ii1;
	Owner o;
	Animal a;
	Veterinarian v;
	Intervention intervention;
	Service service;
 
	@BeforeEach
	void setUp() throws Exception {
		o = new Owner(1L, "Petar", "Petrovic", "1597536548526", false, "063945612", "petar@gmail.com",
				"Sarajevska 39");
		a = new Animal(1L, "Bobi", Species.PAS, 2020, Gender.MUSKI, o);
		v = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567",
				"marko@gmail.com", "sifra123");
		intervention = new Intervention(1L, LocalDate.of(2024, 1, 10), "Redovni pregled", 10, 5, 1000.0, 900.0,
				v, a, null);
		service = new Service(1L, "Vakcinacija", 100.0, "Opis usluge");
 
		ii = new InterventionItem();
		ii1 = new InterventionItem(intervention, 1, 100.0, 2, 200.0, service);
	}
 
	@AfterEach
	void tearDown() throws Exception {
		ii = null;
		ii1 = null;
		o = null;
		a = null;
		v = null;
		intervention = null;
		service = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testInterventionItem() {
		assertNotNull(ii);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testInterventionItemInterventionIntDoubleIntDoubleService() {
		assertNotNull(ii1);
		assertEquals(intervention, ii1.getIntervention());
		assertEquals(1, ii1.getRb());
		assertEquals(100.0, ii1.getPrice());
		assertEquals(2, ii1.getQuantity());
		assertEquals(200.0, ii1.getAmount());
		assertEquals(service, ii1.getService());
	}
 
	@Test
	void testSetInterventionNull() {
		assertThrows(NullPointerException.class, () -> ii.setIntervention(null));
	}
 
	@Test
	void testSetIntervention() {
		ii.setIntervention(intervention);
		assertEquals(intervention, ii.getIntervention());
	}
 
	@ParameterizedTest
	@CsvSource({
		"0",
		"-1"
	})
	void testSetRbInvalid(int rb) {
		assertThrows(IllegalArgumentException.class, () -> ii.setRb(rb));
	}
 
	@Test
	void testSetRb() {
		ii.setRb(3);
		assertEquals(3, ii.getRb());
	}
 
	@ParameterizedTest
	@CsvSource({
		"0",
		"-10"
	})
	void testSetPriceInvalid(double price) {
		assertThrows(IllegalArgumentException.class, () -> ii.setPrice(price));
	}
 
	@Test
	void testSetPrice() {
		ii.setPrice(150.0);
		assertEquals(150.0, ii.getPrice());
	}
 
	@ParameterizedTest
	@CsvSource({
		"0",
		"-1"
	})
	void testSetQuantityInvalid(int quantity) {
		assertThrows(IllegalArgumentException.class, () -> ii.setQuantity(quantity));
	}
 
	@Test
	void testSetQuantity() {
		ii.setQuantity(5);
		assertEquals(5, ii.getQuantity());
	}
 
	@Test
	void testSetAmountNegative() {
		assertThrows(IllegalArgumentException.class, () -> ii.setAmount(-50));
	}
 
	@Test
	void testSetAmount() {
		ii.setAmount(300.0);
		assertEquals(300.0, ii.getAmount());
	}
 
	@Test
	void testSetServiceNull() {
		assertThrows(NullPointerException.class, () -> ii.setService(null));
	}
 
	@Test
	void testSetService() {
		ii.setService(service);
		assertEquals(service, ii.getService());
	}
 
	@Test
	void testGetInsertValues() {
		String expected = "1,1,100.0,2,200.0,1";
		assertEquals(expected, ii1.getInsertValues());
	}
 
	@Test
	void testGetQueryCondition() {
		assertEquals("idIntervention=1", ii1.getQueryCondition());
	}
 
	@Test
	void testGetEntityFromResultSet() throws Exception {
		java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);
 
		org.mockito.Mockito.when(rs.getLong("idIntervention")).thenReturn(1L);
		org.mockito.Mockito.when(rs.getInt("item.rb")).thenReturn(1);
		org.mockito.Mockito.when(rs.getDouble("item.price")).thenReturn(100.0);
		org.mockito.Mockito.when(rs.getInt("item.quantity")).thenReturn(2);
		org.mockito.Mockito.when(rs.getDouble("item.amount")).thenReturn(200.0);
		org.mockito.Mockito.when(rs.getString("s.name")).thenReturn("Vakcinacija");
		org.mockito.Mockito.when(rs.getLong("s.id")).thenReturn(1L);
 
		GenericEntity result = ii.getEntityFromResultSet(rs);
 
		assertTrue(result instanceof InterventionItem);
		InterventionItem resultItem = (InterventionItem) result;
		assertEquals(1L, resultItem.getIntervention().getId());
		assertEquals(1, resultItem.getRb());
		assertEquals(100.0, resultItem.getPrice());
		assertEquals(2, resultItem.getQuantity());
		assertEquals(200.0, resultItem.getAmount());
		assertEquals("Vakcinacija", resultItem.getService().getName());
		assertEquals(1L, resultItem.getService().getId());
	}


}
