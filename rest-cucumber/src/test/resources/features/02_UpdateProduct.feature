@product
  Feature: Update an existing product

    Scenario: Update info an existing product
      Given the path "update" to the Update endpoint
      And the payload of the update request with BrandName as "VNLaptop2", Features as "16GB RAM,1TB Hard Drive", LaptopName as "Unix"
      When I perform request to update an existing product
      Then the update status code "200" should return
      And the product is updated successfully with correct info