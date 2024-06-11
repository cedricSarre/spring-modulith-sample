package fr.example.classroom;

import com.google.gson.Gson;
import fr.example.TestContainersConfiguration;
import fr.example.classroom.internal.repository.ClassroomRepository;
import fr.example.classroom.spi.dto.ClassroomDTO;
import fr.example.core.configuration.MultiModuleFlywayMigrationStrategy;
import fr.example.establishment.EstablishmentServiceInterface;
import fr.example.establishment.internal.entity.Establishment;
import fr.example.establishment.spi.event.EstablishmentDeletedEvent;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.events.core.EventPublicationRepository;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ApplicationModuleTest(extraIncludes = "core", webEnvironment = RANDOM_PORT)
@Import({TestContainersConfiguration.class, MultiModuleFlywayMigrationStrategy.class})
@RequiredArgsConstructor
public class ClassroomIntegrationTests {
    private final static UUID establishmentId = UUID.fromString("b8c115bd-57e7-4b15-b4ab-c05076179171");
    private final ClassroomRepository classroomRepository;
    private final EventPublicationRepository jpaEventPublicationRepository;
    @LocalServerPort
    public Integer serverPort;
    @MockBean
    EstablishmentServiceInterface establishmentServiceInterface;

    @Test
    void testEstablishmentCreationAndDeletion(Scenario scenario) {
        Mockito.when(establishmentServiceInterface.findEstablishmentById(establishmentId))
                .thenReturn(new Establishment());
        Mockito.when(establishmentServiceInterface.getMaxNumberOfClassroomByEstablishmentId(establishmentId))
                .thenReturn(1);

        Gson gson = new Gson();

        ClassroomDTO classroomDTO = ClassroomDTO.builder()
                .name("Classe X")
                .establishmentId(establishmentId)
                .level("6ème")
                .maxStudentsCapacity(35)
                .build();

        ClassroomDTO classroomCreated = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .body(gson.toJson(classroomDTO))
                .when()
                .post("/classrooms")
                .then()
                .statusCode(CREATED.value())
                .extract()
                .as(ClassroomDTO.class);

        ClassroomDTO classroomFound = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/classrooms/" + classroomCreated.id())
                .then()
                .statusCode(OK.value())
                .extract()
                .as(ClassroomDTO.class);

        assertThat(classroomCreated).isEqualTo(classroomFound).isNotNull();
        assertThat(classroomCreated.id()).isEqualTo(classroomFound.id()).isNotNull();
        assertThat(classroomCreated.name()).isEqualTo(classroomFound.name()).isEqualTo("Classe X");
        assertThat(classroomCreated.establishmentId()).isEqualTo(classroomFound.establishmentId()).isNotNull();
        assertThat(classroomCreated.level()).isEqualTo(classroomFound.level()).isNotNull();
        assertThat(classroomCreated.maxStudentsCapacity()).isEqualTo(classroomFound.maxStudentsCapacity()).isEqualTo(35);

        scenario.publish(new EstablishmentDeletedEvent(establishmentId))
                .andWaitForStateChange(() -> classroomRepository.findByEstablishmentId(establishmentId))
                .andVerify(result -> {
                    assert result.isEmpty();
                });

        given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/classrooms/" + classroomCreated.id())
                .then()
                .statusCode(NOT_FOUND.value());

        await().until(() -> jpaEventPublicationRepository.findIncompletePublications().isEmpty());
    }
}
