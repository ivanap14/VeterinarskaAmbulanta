package rs.ac.bg.fon.sa.ambulanta.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.sa.ambulanta.domain.Category;
import rs.ac.bg.fon.sa.ambulanta.domain.Specialization;
import rs.ac.bg.fon.sa.ambulanta.domain.VetSpec;
import rs.ac.bg.fon.sa.ambulanta.domain.Veterinarian;

class VetSpecTest {

	VetSpec vs, vs1;
	Veterinarian v;
	Specialization specialization;
 
	@BeforeEach
	void setUp() throws Exception {
		v = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567",
				"marko@gmail.com", "sifra123");
		specialization = new Specialization(1L, "Ortopedska hirurgija", Category.HIRURSKA, "Opis specijalizacije");
 
		vs = new VetSpec();
		vs1 = new VetSpec(v, specialization, LocalDate.of(2015, 6, 20), "Fakultet veterinarske medicine");
	}
 
	@AfterEach
	void tearDown() throws Exception {
		vs = null;
		vs1 = null;
		v = null;
		specialization = null;
	}
 
	@Test
	@DisplayName("testConstructor")
	void testVetSpec() {
		assertNotNull(vs);
	}
 
	@Test
	@DisplayName("testParametrizedConstructor")
	void testVetSpecVeterinarianSpecializationLocalDateString() {
		assertNotNull(vs1);
		assertEquals(v, vs1.getVeterinarian());
		assertEquals(specialization, vs1.getSpecialization());
		assertEquals(LocalDate.of(2015, 6, 20), vs1.getGraduationDate());
		assertEquals("Fakultet veterinarske medicine", vs1.getInstitution());
	}
 
	@Test
	void testSetVeterinarianNull() {
		assertThrows(NullPointerException.class, () -> vs.setVeterinarian(null));
	}
 
	@Test
	void testSetVeterinarian() {
		vs.setVeterinarian(v);
		assertEquals(v, vs.getVeterinarian());
	}
 
	@Test
	void testSetSpecializationNull() {
		assertThrows(NullPointerException.class, () -> vs.setSpecialization(null));
	}
 
	@Test
	void testSetSpecialization() {
		vs.setSpecialization(specialization);
		assertEquals(specialization, vs.getSpecialization());
	}
 
	@Test
	void testSetGraduationDateNull() {
		assertThrows(NullPointerException.class, () -> vs.setGraduationDate(null));
	}
 
	@Test
	void testSetGraduationDateFuture() {
		assertThrows(IllegalArgumentException.class, () -> vs.setGraduationDate(LocalDate.now().plusDays(1)));
	}
 
	@Test
	void testSetGraduationDate() {
		vs.setGraduationDate(LocalDate.of(2015, 6, 20));
		assertEquals(LocalDate.of(2015, 6, 20), vs.getGraduationDate());
	}
 
	@Test
	void testSetInstitutionNullOrEmpty() {
		assertThrows(IllegalArgumentException.class, () -> vs.setInstitution(null));
		assertThrows(IllegalArgumentException.class, () -> vs.setInstitution(""));
	}
 
	@Test
	void testSetInstitutionTooLong() {
		assertThrows(IllegalArgumentException.class, () -> vs.setInstitution("a".repeat(101)));
	}
 
	@Test
	void testSetInstitution() {
		vs.setInstitution("Fakultet veterinarske medicine");
		assertEquals("Fakultet veterinarske medicine", vs.getInstitution());
	}

	@Test
	void testHashCode() {
		Veterinarian v2 = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@gmail.com", "sifra123");
		Specialization s2 = new Specialization(1L, "Ortopedska hirurgija", Category.HIRURSKA, "Opis specijalizacije");
		VetSpec vs2 = new VetSpec(v2, s2, LocalDate.of(2018, 9, 1), "Neki drugi fakultet");

		Veterinarian v3 = new Veterinarian(2L, "Jovan", "Jovanovic", LocalDate.of(1985, 3, 10), "0651234567", "jovan@gmail.com", "sifra456");
		Specialization s3 = new Specialization(2L, "Interna medicina", Category.OPSTA, "Drugi opis");
		VetSpec vs3 = new VetSpec(v3, s3, LocalDate.of(2019, 1, 1), "Treći fakultet");

		assertEquals(vs1.hashCode(), vs2.hashCode());
		assertNotEquals(vs1.hashCode(), vs3.hashCode());
	}

	@Test
	void testEqualsObject() {
		Veterinarian v2 = new Veterinarian(1L, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@gmail.com", "sifra123");
		Specialization s2 = new Specialization(1L, "Ortopedska hirurgija", Category.HIRURSKA, "Opis specijalizacije");
		VetSpec vs2 = new VetSpec(v2, s2, LocalDate.of(2018, 9, 1), "Neki drugi fakultet");

		Veterinarian v3 = new Veterinarian(2L, "Jovan", "Jovanovic", LocalDate.of(1985, 3, 10), "0651234567", "jovan@gmail.com", "sifra456");
		Specialization s3 = new Specialization(2L, "Interna medicina", Category.OPSTA, "Drugi opis");
		VetSpec vs3 = new VetSpec(v3, s3, LocalDate.of(2019, 1, 1), "Treći fakultet");

		assertTrue(vs1.equals(vs2));
		assertFalse(vs1.equals(vs3));
	}

	@Test
	void testEqualsNull() {
		assertFalse(vs1.equals(null));
	}

	@Test
	void testEqualsInvalidClass() {
		assertFalse(vs1.equals(new String()));
	}

	@Test
	void testEqualsSameInstance() {
		assertTrue(vs1.equals(vs1));
	}

}
