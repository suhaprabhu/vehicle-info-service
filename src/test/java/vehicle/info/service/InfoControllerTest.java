package vehicle.info.service;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.server.EmbeddedServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import vehicle.info.api.VehicleConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InfoControllerTest {

    static EmbeddedServer embeddedServer;

    @BeforeClass
    public static void setup() {
        embeddedServer = ApplicationContext.run(
                EmbeddedServer.class
        );
    }

    @AfterClass
    public static void cleanup() {
        if (embeddedServer != null) {
            embeddedServer.stop();
        }
    }

    @Test
    public void testListConfig() {
        InfoControllerTestClient client = embeddedServer.getApplicationContext().getBean(InfoControllerTestClient.class);
        List<VehicleConfig> configList = client.list().blockingGet();
        assertEquals("If you copy the two files to /tmp/vehicle-info this will pass", 2, configList.size());
    }

    @Test
    public void testGetFirstConfig() {
        InfoControllerTestClient client = embeddedServer.getApplicationContext().getBean(InfoControllerTestClient.class);
        VehicleConfig firstConfig = client.first().blockingGet();
        assertNotNull(firstConfig);
    }

    @Test
    public void testFilterXls() {
        InfoControllerTestClient client = embeddedServer.getApplicationContext().getBean(InfoControllerTestClient.class);
        List<VehicleConfig> configList = client.filter("xls").blockingGet();
        assertEquals("If you copy the two files to /tmp/vehicle-info this will pass", 2, configList.size());

        configList = client.filter("pdf").blockingGet();
        assertEquals(0, configList.size());
    }
}
