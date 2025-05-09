Feature: Login Scenarios

Background:
    Given The User login into the Webpage

  Scenario: Login Scenarios
    Then User launches the Url "https://www.demoblaze.com/index.html" in the browser
    And User clicks on the "Samsung galaxy s6" mobile in the screen
    Then User checks the mobile model Enterd is "Samsung galaxy s6" is same added in cart page
    And User close the browser
    
    @regression
    Scenario: Login Scenarios
    Then User launches the Url "https://www.demoblaze.com/index.html" in the browser
    And User clicks on the "Samsung galaxy s6" mobile in the screen
    Then User checks the mobile model Enterd is "Samsung galaxy s6" is same added in cart page
    
    @smoke
    Scenario: Login Scenarios
    Then User launches the Url "https://www.demoblaze.com/index.html" in the browser
    And User clicks on the "Samsung galaxy s6" mobile in the screen
    
    
    
