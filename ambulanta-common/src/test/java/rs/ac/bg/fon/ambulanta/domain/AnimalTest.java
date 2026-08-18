package rs.ac.bg.fon.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;



class AnimalTest {

	Animal a,a1,a2,a3;
	Owner o;
	
	@BeforeEach
	void setUp() throws Exception {
		o=new Owner(1L, "Petar", "Petrović", "1597536548526", false, "063945612", "petar@gmail.com", "Sarajevska 39");
		a=new Animal();
		a1=new Animal(1L, "Bobi", Species.PAS, 2020, Gender.MUSKI, o);
			
	}

	@AfterEach
	void tearDown() throws Exception {
		o=null;
		a=null;
		a1=null;
	}

	@Test
	void testHashCode() {
		Animal a2 = new Animal(1L, "Bobi", Species.PAS, 2020, Gender.MUSKI, o);
		Animal a3 = new Animal(2L, "Macka", Species.MACKA, 2017, Gender.ZENSKI, o);
		
		assertEquals(a1.hashCode(), a2.hashCode());
		assertNotEquals(a1.hashCode(), a3.hashCode());
	}

	@Test
	@DisplayName("testConstructor")
	void testAnimal() {
		assertNotNull(a);
	}

	@Test
	@DisplayName("testParametrizedConstructor")
	void testAnimalLongStringSpeciesIntGenderOwner() {	
		assertNotNull(a1);
		assertEquals("Bobi", a1.getName());
		assertEquals(Species.PAS, a1.getSpecies());
		assertEquals(2020, a1.getYearOfBirth());
		assertEquals(Gender.MUSKI, a1.getGender());
		assertEquals(o, a1.getOwner());
	}

	@Test
	void testSetId() {
		a.setId(5L);
		assertEquals(5L, a.getId());
	}

	@Test
	void testSetNameNullOrEmpty() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setName(null));
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setName(""));
	}
	
	@Test
	void testSetNameInvalidName() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setName("Ogi123"));
	}
	@Test
	void testSetNameTooLong() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setName("a".repeat(51)));
	}
	@Test
	void testSetName() {
		a.setName("Ogi");
		assertEquals("Ogi", a.getName());
	}

	@Test
	void testSetSpeciesNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setSpecies(null));
	}
	
	@Test
	void testSetSpecies() {
		a.setSpecies(Species.PAS);
		assertEquals(Species.PAS, a.getSpecies());
	}

	@Test
	void testSetYearOfBirthNegative() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setYearOfBirth(-17));
	}
	

	@ParameterizedTest
	@CsvSource({
		"1",
		"23",
		"123",
		"12345",
		"123456"
	})
	void testSetYearOfBirthInvalid(int number) {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setYearOfBirth(number));
	}
	
	@Test
	void testSetYearOfBirthFuture() {
		assertThrows(java.lang.IllegalArgumentException.class, ()->a.setYearOfBirth(LocalDateTime.now().plusYears(1).getYear()));
	}
	
	@ParameterizedTest
	@CsvSource({
		"0",
		"2021",
		"2026"
	})
	void testSetYearOfBirth(int number) {
		a.setYearOfBirth(number);
		
		assertEquals(number, a.getYearOfBirth());
	}

	@Test
	void testSetGenderNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setGender(null));
	}
	
	@Test
	void testSetGender() {
		a.setGender(Gender.ZENSKI);
		assertEquals(Gender.ZENSKI, a.getGender());
	}

	@Test
	void testSetOwnerNull() {
		assertThrows(java.lang.NullPointerException.class, ()->a.setGender(null));
	}
	
	@Test
	void testSetOwner() {
		a.setOwner(o);
		assertEquals(o, a.getOwner());
	}

	@Test
	void testToString() {
		a.setId(1L);
		a.setName("Ogi");
		a.setSpecies(Species.PAS);
		
		String s = a.toString();

		assertEquals("[1] Ogi (PAS)", s);
	}

	@Test
	void testEqualsObject() {
		Animal a2 = new Animal(1l, "Bobi", Species.PAS, 2020, Gender.MUSKI, o);
		Animal a3 = new Animal(2l, "Macka", Species.MACKA, 2017, Gender.ZENSKI, o);
		
		assertTrue(a1.equals(a2));
		assertFalse(a1.equals(a3));
	}
	
	@Test
	void testEqualsNull() {
		assertFalse(a1.equals(null));
	}
	
	@Test
	void testEqualsInvalidClass() {
		assertFalse(a1.equals(new String()));
	}

	@Test
	void testGetInsertValues() {
	    String expected = "'Bobi','PAS',2020,'MUSKI',1";
	    assertEquals(expected, a1.getInsertValues());
	}

	@Test
	void testSetIdFromRS() {
	    a.setIdFromRS(10L);
	    assertEquals(10L, a.getId());
	}

	@Test
	void testGetEntityFromResultSet() throws Exception {
	    java.sql.ResultSet rs = org.mockito.Mockito.mock(java.sql.ResultSet.class);

	    org.mockito.Mockito.when(rs.getLong("id")).thenReturn(1L, 1L);
	    org.mockito.Mockito.when(rs.getString("firstname")).thenReturn("Petar");
	    org.mockito.Mockito.when(rs.getString("lastname")).thenReturn("Petrović");
	    org.mockito.Mockito.when(rs.getString("jmbg")).thenReturn("1597536548526");
	    org.mockito.Mockito.when(rs.getBoolean("loyaltyCard")).thenReturn(false);
	    org.mockito.Mockito.when(rs.getString("phone")).thenReturn("063945612");
	    org.mockito.Mockito.when(rs.getString("email")).thenReturn("petar@gmail.com");
	    org.mockito.Mockito.when(rs.getString("address")).thenReturn("Sarajevska 39");
	    org.mockito.Mockito.when(rs.getString("name")).thenReturn("Bobi");
	    org.mockito.Mockito.when(rs.getString("species")).thenReturn("PAS");
	    org.mockito.Mockito.when(rs.getInt("yearOfBirth")).thenReturn(2020);
	    org.mockito.Mockito.when(rs.getString("gender")).thenReturn("MUSKI");

	    GenericEntity result = a.getEntityFromResultSet(rs);

	    assertTrue(result instanceof Animal);
	    Animal resultAnimal = (Animal) result;
	    assertEquals("Bobi", resultAnimal.getName());
	    assertEquals(Species.PAS, resultAnimal.getSpecies());
	    assertEquals(2020, resultAnimal.getYearOfBirth());
	    assertEquals(Gender.MUSKI, resultAnimal.getGender());
	    assertEquals(1L, resultAnimal.getId());
	    assertEquals("Petar", resultAnimal.getOwner().getFirstname());
	}

	@Test
	void testSetAttributeValues() {
	    String expected = "name='Bobi',species='PAS',yearOfBirth=2020,gender='MUSKI',idOwner=1";
	    assertEquals(expected, a1.setAttributeValues());
	}

	@Test
	void testGetQueryCondition() {
	    assertEquals("id=1", a1.getQueryCondition());
	}

}
