package fr.example.establishment;

import com.google.gson.Gson;
import fr.example.TestContainersConfiguration;
import fr.example.core.configuration.MultiModuleFlywayMigrationStrategy;
import fr.example.establishment.spi.dto.EstablishmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.test.ApplicationModuleTest;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ApplicationModuleTest(extraIncludes = "core", webEnvironment = RANDOM_PORT)
@Import({TestContainersConfiguration.class, MultiModuleFlywayMigrationStrategy.class})
public class EstablishmentIntegrationTests {
    @LocalServerPort
    public Integer serverPort;

    @Test
    void testEstablishmentCreationAndDeletion() {
        Gson gson = new Gson();

        // Création d'un établissement
        EstablishmentDTO establishmentDTO = EstablishmentDTO.builder()
                .name("College X")
                .nbMaxClassroom(35)
                .build();

        // Appel API de la création d'un établissement (DTO précédent)
        EstablishmentDTO establishmentCreated = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .body(gson.toJson(establishmentDTO))
                .when()
                .post("/establishments")
                .then()
                .statusCode(CREATED.value())
                .extract()
                .as(EstablishmentDTO.class);

        // Appel API pour récupérer l'établissement créé en fonction de son Id
        EstablishmentDTO establishmentFound = given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/establishments/" + establishmentCreated.id())
                .then()
                .statusCode(OK.value())
                .extract()
                .as(EstablishmentDTO.class);

        // Comparaison de l'établissement créé et de l'établissement récupéré
        assertThat(establishmentCreated).isEqualTo(establishmentFound).isNotNull();
        assertThat(establishmentCreated.id()).isEqualTo(establishmentFound.id()).isNotNull();
        assertThat(establishmentCreated.name()).isEqualTo(establishmentFound.name()).isEqualTo("College X");
        assertThat(establishmentCreated.address()).isEqualTo(establishmentFound.address()).isNull();
        assertThat(establishmentCreated.email()).isEqualTo(establishmentFound.email()).isNull();
        assertThat(establishmentCreated.phoneNumber()).isEqualTo(establishmentFound.phoneNumber()).isNull();
        assertThat(establishmentCreated.nbMaxClassroom()).isEqualTo(establishmentFound.nbMaxClassroom()).isEqualTo(35);

        // On supprime l'établissement créé via l'API
        given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .delete("/establishments/" + establishmentCreated.id())
                .then()
                .statusCode(NO_CONTENT.value());

        // On vérifie via un call API que l'établissement n'existe plus
        given().port(serverPort)
                .contentType(APPLICATION_JSON_VALUE)
                .when()
                .get("/establishments/" + establishmentCreated.id())
                .then()
                .statusCode(NOT_FOUND.value());
    }
}
