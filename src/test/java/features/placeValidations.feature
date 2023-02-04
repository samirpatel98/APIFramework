Feature: Validating place APIs

  @AddPlace @Regression
  Scenario Outline: Verify AddPlace API
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User calls "addPlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      | name  | language | address |
      |AAHouse| English  | World Trade Center |
      |Work   | French   | Tower |
  #Comment    |Home   | Indian   | road |

  @DeletePlace @Regression
  Scenario: Verify that Delete functionality is working
    Given Delete Place Payload
    When User calls "deletePlaceAPI" with "POST" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"