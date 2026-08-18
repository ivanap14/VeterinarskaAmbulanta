package rs.ac.bg.fon.sa.ambulanta.repository.db.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import rs.ac.bg.fon.sa.ambulanta.domain.*;
import rs.ac.bg.fon.sa.ambulanta.repository.db.DbConnectionFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbBrokerTest {

	private DbBroker dbBroker;
    private Service service;

    @BeforeEach
    public void setUp() throws Exception {

        dbBroker = new DbBroker();

        service = new Service();
        service.setName("Test usluga");
        service.setPrice(1000.0);
        service.setDescription("Test opis usluge");

        service = (Service) dbBroker.add(service);

        DbConnectionFactory.getInstance().getConnection().commit();
    }

    @AfterEach
    public void tearDown() throws Exception {

        if (service != null && service.getId() != null) {
            dbBroker.delete(service);

            DbConnectionFactory.getInstance().getConnection().commit();
        }

        dbBroker = null;
        service = null;
    }

    @Test
    public void testAdd() throws Exception {

        Service newService = new Service();
        newService.setName("Nova test usluga");
        newService.setPrice(2000.0);
        newService.setDescription("Opis nove test usluge");

        Service result = (Service) dbBroker.add(newService);

        try {

            assertNotNull(result);

            assertNotNull(result.getId());

            assertEquals("Nova test usluga", result.getName());
            assertEquals(2000.0, result.getPrice());
            assertEquals("Opis nove test usluge",result.getDescription());

        } finally {

            // Brisanje dodatog podatka nakon testa
            if (result.getId() != null) {

                dbBroker.delete(result);

                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }

    @Test
    public void testEdit() throws Exception {

        service.setName("Izmenjena usluga");
        service.setPrice(2500.0);
        service.setDescription("Izmenjeni opis");

        Service result = (Service) dbBroker.edit(service);

        DbConnectionFactory.getInstance().getConnection().commit();

        assertNotNull(result);

        assertEquals(service.getId(), result.getId());
        assertEquals("Izmenjena usluga", result.getName());
        assertEquals(2500.0, result.getPrice());
        assertEquals("Izmenjeni opis", result.getDescription());

        // Provera da je zaista izmenjeno u bazi
        List<GenericEntity> services =dbBroker.getByCriteria(new Service(),"WHERE id=" + service.getId());

        assertEquals(1, services.size());

        Service databaseService = (Service) services.get(0);

        assertEquals("Izmenjena usluga", databaseService.getName());

        assertEquals(2500.0, databaseService.getPrice());

        assertEquals("Izmenjeni opis", databaseService.getDescription());
    }


    @Test
    public void testDelete() throws Exception {

        Service serviceToDelete = new Service();

        serviceToDelete.setName("Usluga za brisanje");
        serviceToDelete.setPrice(1500.0);
        serviceToDelete.setDescription("Usluga koja će biti obrisana");

        serviceToDelete = (Service) dbBroker.add(serviceToDelete);

        DbConnectionFactory.getInstance().getConnection().commit();

        Long id = serviceToDelete.getId();

        try {

            Service result =
                    (Service) dbBroker.delete(serviceToDelete);

            DbConnectionFactory.getInstance().getConnection().commit();

            assertNotNull(result);
            assertEquals(id, result.getId());

            // Provera da zapis vise ne postoji
            List<GenericEntity> services =dbBroker.getByCriteria(new Service(),"WHERE id=" + id);

            assertTrue(services.isEmpty());

        } finally {

            // Ako je kojim slucajem ostao u bazi
            List<GenericEntity> services = dbBroker.getByCriteria(new Service(),"WHERE id=" + id);

            if (!services.isEmpty()) {

                dbBroker.delete(services.get(0));

                DbConnectionFactory.getInstance().getConnection().commit();
            }
        }
    }


    @Test
    public void testGetAll() throws Exception {

        List<GenericEntity> services = dbBroker.getAll(new Service());

        assertNotNull(services);

        assertFalse(services.isEmpty());

        assertTrue(services.contains(service));
    }


    @Test
    public void testGetByCriteria() throws Exception {

        List<GenericEntity> services = dbBroker.getByCriteria(new Service(), "WHERE id=" + service.getId());

        assertNotNull(services);

        assertEquals(1, services.size());

        Service result = (Service) services.get(0);

        assertEquals(service.getId(), result.getId());
        assertEquals(service.getName(), result.getName());
        assertEquals(service.getPrice(), result.getPrice());
        assertEquals(service.getDescription(), result.getDescription());
    }

}
