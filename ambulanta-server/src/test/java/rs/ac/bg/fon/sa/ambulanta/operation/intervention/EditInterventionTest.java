package rs.ac.bg.fon.sa.ambulanta.operation.intervention;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditInterventionTest {

	private EditIntervention so;
	private DbBroker dbBroker;
	private Owner owner;
	private Animal animal;
	private Veterinarian veterinarian;
	private Service service;
	private Intervention intervention;
	private InterventionItem item;

	@BeforeEach
	public void setUp() throws Exception {
		so = new EditIntervention();
		dbBroker = new DbBroker();

		owner = new Owner(null, "Pera", "Peric", "1234567890123", false, "0611234567", "pera@gmail.com", "Bulevar oslobodjenja 1");
		owner = (Owner) dbBroker.add(owner);

		animal = new Animal(null, "Bobi", Species.PAS, 2020, Gender.MUSKI, owner);
		animal = (Animal) dbBroker.add(animal);

		veterinarian = new Veterinarian(null, "Marko", "Markovic", LocalDate.of(1990, 5, 20), "0641234567", "marko@gmail.com", "sifra123");
		veterinarian = (Veterinarian) dbBroker.add(veterinarian);

		service = new Service(null, "Vakcinacija", 1000, "Vakcinacija psa");
		service = (Service) dbBroker.add(service);

		intervention = new Intervention(null, LocalDate.now(), "Pregled psa", 10, 5, 1000, 850, veterinarian, animal, new ArrayList<>());
		intervention = (Intervention) dbBroker.add(intervention);

		item = new InterventionItem(intervention, 1, 1000, 1, 1000, service);
		intervention.getInterventionItems().add(item);

		DbConnectionFactory.getInstance().getConnection().commit();
	}

	@AfterEach
	public void tearDown() throws Exception {
		if (item != null && intervention != null && intervention.getId() != null) {
			dbBroker.delete(item);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (intervention != null && intervention.getId() != null) {
			dbBroker.delete(intervention);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (service != null && service.getId() != null) {
			dbBroker.delete(service);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (animal != null && animal.getId() != null) {
			dbBroker.delete(animal);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (owner != null && owner.getId() != null) {
			dbBroker.delete(owner);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		if (veterinarian != null && veterinarian.getId() != null) {
			dbBroker.delete(veterinarian);
			DbConnectionFactory.getInstance().getConnection().commit();
		}

		so = null;
		dbBroker = null;
		owner = null;
		animal = null;
		veterinarian = null;
		service = null;
		intervention = null;
		item = null;
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
	public void testPreconditionsInterventionDoesNotExist() {
		Intervention invalidIntervention = new Intervention(0L, LocalDate.now(), "Nepostojeca intervencija", 10, 5, 1000, 850, veterinarian, animal, new ArrayList<>());
		invalidIntervention.getInterventionItems().add(item);

		assertThrows(Exception.class, () -> so.preconditions(invalidIntervention));
	}

	@Test
	public void testPreconditionsWithoutItems() {
		intervention.getInterventionItems().clear();

		assertThrows(Exception.class, () -> so.preconditions(intervention));
	}

	@Test
	public void testPreconditionsValidObject() {
		assertDoesNotThrow(() -> so.preconditions(intervention));
		assertEquals(intervention.getId(), so.getIntervention().getId());
	}

	@Test
	public void testExecuteOperation() throws Exception {
		so.execute(intervention);

		assertNotNull(so.getIntervention());
		assertEquals(intervention.getId(), so.getIntervention().getId());
	}

	@Test
	public void testExecuteOperationWithEditedData() throws Exception {
		Intervention editedIntervention = new Intervention(intervention.getId(), LocalDate.now(), "Izmenjena napomena", 20, 10, 2000, 1400, veterinarian, animal, new ArrayList<>());
		editedIntervention.getInterventionItems().add(item);

		so.execute(editedIntervention);

		assertNotNull(so.getIntervention());
		assertEquals(intervention.getId(), so.getIntervention().getId());
		assertEquals("Izmenjena napomena", so.getIntervention().getNotes());
		assertEquals(20, so.getIntervention().getDiscountForLoyalty());
		assertEquals(10, so.getIntervention().getDiscountForNumberOfServices());
		assertEquals(2000, so.getIntervention().getTotalAmountWithoutDiscount());
		assertEquals(1400, so.getIntervention().getTotalAmountWithDiscount());
	}


}
