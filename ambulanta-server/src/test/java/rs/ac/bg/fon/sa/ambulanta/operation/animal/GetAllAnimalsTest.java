package rs.ac.bg.fon.sa.ambulanta.operation.animal;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.sa.ambulanta.repository.db.impl.DbBroker;
import rs.ac.bg.fon.sa.ambulanta.domain.*;

class GetAllAnimalsTest {

	private GetAllAnimals so;
    private DbBroker dbBroker;
    private Owner o;
    private Animal a;

    @BeforeEach
    public void setUp() throws Exception {
        so = new GetAllAnimals();
        dbBroker = new DbBroker();

        o = new Owner();
        o.setFirstname("Pera");
        o.setLastname("Peric");
        o.setJmbg("1234567890123");
        o.setLoyaltyCard(false);
        o.setPhone("0611234567");
        o.setEmail("pera@gmail.com");
        o.setAddress("Bulevar oslobodjenja 1");
        o = (Owner) dbBroker.add(o);
        DbConnectionFactory.getInstance().getConnection().commit();

        a = new Animal();
        a.setName("Bobi");
        a.setSpecies(Species.PAS);
        a.setYearOfBirth(2020);
        a.setGender(Gender.MUSKI); 
        a.setOwner(o);
        a = (Animal) dbBroker.add(a);
        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {

        if (a != null && a.getId() != null) {
            dbBroker.delete(a);
            DbConnectionFactory.getInstance().getConnection().commit();
        }
        if (o != null && o.getId() != null) {
            dbBroker.delete(o);
            DbConnectionFactory.getInstance().getConnection().commit();
        }

        so = null;
        a = null;
        o = null;
        dbBroker = null;
    }

    @Test
    public void testPreconditions() {
        assertDoesNotThrow(() -> so.preconditions(null));
    }

    @Test
    public void testExecuteOneAnimal() throws Exception {
        so.execute(null);
        List<Animal> animals = so.getAnimals();

        assertNotNull(animals);
        assertFalse(animals.isEmpty());
        assertTrue(animals.contains(a));
    }

    @Test
    public void testExecuteOperationMultipleAnimals() throws Exception {
        Animal a2 = new Animal();
        a2.setName("Lara");
        a2.setSpecies(Species.MACKA);
        a2.setYearOfBirth(2021);
        a2.setGender(Gender.ZENSKI);
        a2.setOwner(o);
        a2 = (Animal) dbBroker.add(a2);
        DbConnectionFactory.getInstance().getConnection().commit();

        try {
            so.execute(null);
            List<Animal> animals = so.getAnimals();

            assertNotNull(animals);
            assertTrue(animals.size() >= 2);
            assertTrue(animals.contains(a));
            assertTrue(animals.contains(a2));
        } finally {
            if (a2 != null && a2.getId() != null) {
                dbBroker.delete(a2);
                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

}
