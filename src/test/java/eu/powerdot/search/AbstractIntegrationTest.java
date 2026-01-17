package eu.powerdot.search;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalManagementPort;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.lifecycle.Startables;

@Testcontainers
@SpringBootTest(classes = CodeChallenge.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server.port=0",
        "management.server.port=0",
})
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int localServerPort;

    @LocalManagementPort
    protected int localServerManagementPort;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("code-challenge");

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("code-challenge.database.host",
                () -> postgres.getHost() + ":" + postgres.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT));
        registry.add("code-challenge.database.username", () -> postgres.getUsername());
        registry.add("code-challenge.database.password", () -> postgres.getPassword());
    }

    static {
        Startables.deepStart(postgres).join();
    }
}
