package com.kms.api.tests;

import com.kms.api.requests.RequestFactory;
import com.kms.api.util.ValidationUtil;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class DeleteProductSteps extends TestBase{
    private String path = "";
    private Response res;

    @Given("^the path \"([^\"]*)\" to the Delete endpoint$")
    public void thePathToDeleteTheProduct(String path) {
        this.path = path;
    }

    @When("^I perform request to delete an existing product$")
    public void iPerformTheRequestToApplication() {
        res = RequestFactory.deleteProduct(path, String.valueOf(id));
    }

    @Then("^the delete status code \"([^\"]*)\" should return$")
    public void theStatusCodeShouldReturn(String  statusCode) {
        ValidationUtil.validateStatusCode(res, Integer.parseInt(statusCode));
    }
}
