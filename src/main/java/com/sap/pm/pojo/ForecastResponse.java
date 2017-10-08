package com.sap.pm.pojo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"forecasts",
"modelInformation",
"modelPerformance"
})
public class ForecastResponse {

@JsonProperty("forecasts")
private List<Forecast> forecasts = null;
@JsonProperty("modelInformation")
private ModelInformation modelInformation;
@JsonProperty("modelPerformance")
private ModelPerformance modelPerformance;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("forecasts")
public List<Forecast> getForecasts() {
return forecasts;
}

@JsonProperty("forecasts")
public void setForecasts(List<Forecast> forecasts) {
this.forecasts = forecasts;
}

@JsonProperty("modelInformation")
public ModelInformation getModelInformation() {
return modelInformation;
}

@JsonProperty("modelInformation")
public void setModelInformation(ModelInformation modelInformation) {
this.modelInformation = modelInformation;
}

@JsonProperty("modelPerformance")
public ModelPerformance getModelPerformance() {
return modelPerformance;
}

@JsonProperty("modelPerformance")
public void setModelPerformance(ModelPerformance modelPerformance) {
this.modelPerformance = modelPerformance;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}