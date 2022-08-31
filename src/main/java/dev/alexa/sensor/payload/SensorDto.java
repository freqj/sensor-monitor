package dev.alexa.sensor.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Map;

public class SensorDto {
    private Long id;
    @NotEmpty
    @Size(max=30)
    private String title;
    @NotEmpty
    @Size(max = 15)
    private String model;
    @NotEmpty
    private Map<String,Integer> range;
    private String type;
    private String unit;
    @Size(max = 40)
    private String location;
    @Size(max = 200)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Map<String, Integer> getRange() {
        return range;
    }

    public void setRange(Map<String, Integer> range) {
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
