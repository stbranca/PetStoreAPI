package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;

public class Pets {
    // Attributes
    String uri = "https://petstore.swagger.io/v2/pet";

    // Methods and Functions
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Include - Create - Post
    @Test
    public void incluirPet() throws IOException {
        String jsonBody = lerJson("db/pet.json");

        // Sintaxe Gherkin
        //Given - When - Then

        given()
                .contentType("application/json")
                .log().all()
                .body(jsonBody)

                .when()
                .post(uri)

                .then()
                .log().all()
                .statusCode(200)
        ;
    }
}
