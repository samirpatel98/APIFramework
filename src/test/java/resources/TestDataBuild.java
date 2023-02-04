package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public AddPlace addPlacePayLoad(String name, String language, String address) {
        AddPlace p = new AddPlace();

        p.setAccuracy(50);
        p.setAddress(address);
        p.setLanguage(language);
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setName(name);

        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");

        p.setTypes(myList);

        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        p.setLocation(location);
        return p;
    }

    public String deletePlacePayload(String place_id) {
        return("{\n" +
                "\t\"place_id\":\""+ place_id +"\"\n" +
                "}");
    }
}
