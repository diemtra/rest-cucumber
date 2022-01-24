@product
Feature: Delete an existing product

  Scenario: Delete an existing product
    Given the path "delete/{id}" to the Delete endpoint
    When I perform request to delete an existing product
    Then the delete status code "200" should return