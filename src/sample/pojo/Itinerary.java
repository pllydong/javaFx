package sample.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Itinerary {
    private String date;
    private List<String> activityPlan;
    private String contactNumber;
    private String accommodationsAddress;
}
