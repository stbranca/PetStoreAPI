package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;


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
                .body("name", is("Marley"))
                .body("status", is("available"))
        ;
    }
}
//"id": 9223372016854973557
//"id": 9223372016854973577
//"id": 9223372016854973582
//"id": 1947090936