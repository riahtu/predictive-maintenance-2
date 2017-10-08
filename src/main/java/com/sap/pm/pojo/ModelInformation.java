package com.sap.pm.pojo;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"cycles",
"fluctuations",
"trend"
})
public class ModelInformation {

@JsonProperty("cycles")
private String cycles;
@JsonProperty("fluctuations")
private String fluctuations;
@JsonProperty("trend")
private String trend;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("cycles")
public String getCycles() {
return cycles;
}

@JsonProperty("cycles")
public void setCycles(String cycles) {
this.cycles = cycles;
}

@JsonProperty("fluctuations")
public String getFluctuations() {
return fluctuations;
}

@JsonProperty("fluctuations")
public void setFluctuations(String fluctuations) {
this.fluctuations = fluctuations;
}

@JsonProperty("trend")
public String getTrend() {
return trend;
}

@JsonProperty("trend")
public void setTrend(String trend) {
this.trend = trend;
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