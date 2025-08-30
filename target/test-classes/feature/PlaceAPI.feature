Feature: Validating Place API's

@AddPlaceAPI
Scenario Outline: Verify Place is being successfully added using Add Place API

Given Add Place API Payload with "<name>" and "<address>"
When User hit "AddPlaceAPI" with http "post" request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id got maps to "<name>" Using "GetPlaceAPI"

Examples:
				|name| address|
				|Uday |Raigad Nivas |
				

@DeletePlaceAPI
Scenario: Verify Place is getting deleted using Delete Place API
Given Delete Palce API Payload
When User hit "DeletePlaceAPI" with http "post" request
Then the API call got success with status code 200
And "status" in response body is "OK"
