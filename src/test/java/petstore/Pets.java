package petstore;

import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;


public class Pets {
    // Attributes
    String uri = "https://petstore.swagger.io/v2/pet";

    // Methods and Functions
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Include - Create - Post
    @Test(priority = 1)
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
                .body("category.name", is("BMX45YHE2"))
                .body("tags.name", contains("Sta"))
        ;
    }
    @Test(priority = 2)
    public void consultarPet() {
        String petId = "1947090936";
        String token =

        given()
                .contentType("application/json")
                .log().all()

                .when()
                .get(uri + "/" + petId)

                .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Marley"))
                .body("category.name", is("BMX45YHE2"))
                .body("status", is("available"))
                .extract()
                .path("category.name")
                ;
        System.out.println("O token é " + token);

    }
}
