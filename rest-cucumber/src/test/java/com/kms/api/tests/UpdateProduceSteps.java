package com.kms.api.tests;

import com.kms.api.model.LaptopBag;
import com.kms.api.requests.RequestFactory;
import com.kms.api.util.RequestBuilder;
import com.kms.api.util.ValidationUtil;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.Arrays;
import java.util.List;
import static com.kms.api.util.RestUtil.mapRestResponseToPojo;

public class UpdateProduceSteps extends TestBase{
    private String path = "";
    private Object requestPayload;
    private LaptopBag reqUpdateLaptop;
    private LaptopBag resUpdateLaptop;
    private String brandName;
    private List<String> features;
    private String laptopName;
    private Response res;

    @Given("the path \"([^\"]*)\" to the Update endpoint$")
    public void thePathToUpdateTheProduct(String path) {
        this.path = path;
    }

    @And("the payload of the update request with BrandName as \"([^\"]*)\", Features as \"([^\"]*)\", LaptopName as \"([^\"]*)\"$")
    public void thePayloadOfTheRequestWithBrandNameAsFeaturesAsLaptopNameAs(String brandName, String feature, String laptopName) {
        String[] array = feature.split(",");
        List<String> lst = Arrays.asList(array);
        requestPayload = RequestBuilder.requestPayload(laptopName, brandName, id, lst);
        this.brandName = brandName;
        this.features = lst;
        this.laptopName = laptopName;
    }

    @When("^I perform request to update an existing product$")
    public void iPerformTheRequestToApplication() {
        reqUpdateLaptop = (LaptopBag) requestPayload;
        res = RequestFactory.updateProduct(path, reqUpdateLaptop);
        resUpdateLaptop = mapRestResponseToPojo(res, LaptopBag.class);
    }

    @Then("^the update status code \"([^\"]*)\" should return$")
    public void theStatusCodeShouldReturn(String statusCode) {
        ValidationUtil.validateStatusCode(res, Integer.parseInt(statusCode));
    }

    @And("^the product is updated successfully with correct info$")
    public void theProductIsAddedSuccessfullyWithCorrectInfo() {
        ValidationUtil.validateStringEqual(brandName, resUpdateLaptop.getBrandName());
        ValidationUtil.validateStringEqual(features, resUpdateLaptop.getFeatures().getFeature());
        ValidationUtil.validateStringEqual(laptopName, resUpdateLaptop.getLaptopName());
    }
}
