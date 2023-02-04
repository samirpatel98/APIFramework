package stepDefinitions;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        stepDefinition sd = new stepDefinition();
        if(stepDefinition.place_id == null) {
            sd.add_place_payload_with("Samir", "French", "China");
            sd.user_calls_with_http_request("addPlaceAPI", "POST");
            sd.verify_place_id_created_maps_to_using("Samir", "getPlaceAPI");
        }
    }
}
