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
"mape",
"mapePerHorizon",
"maximumConfidentHorizon",
"qualityRating"
})
public class ModelPerformance {

@JsonProperty("mape")
private Double mape;
@JsonProperty("mapePerHorizon")
private List<Double> mapePerHorizon = null;
@JsonProperty("maximumConfidentHorizon")
private Integer maximumConfidentHorizon;
@JsonProperty("qualityRating")
private Integer qualityRating;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("mape")
public Double getMape() {
return mape;
}

@JsonProperty("mape")
public void setMape(Double mape) {
this.mape = mape;
}

@JsonProperty("mapePerHorizon")
public List<Double> getMapePerHorizon() {
return mapePerHorizon;
}

@JsonProperty("mapePerHorizon")
public void setMapePerHorizon(List<Double> mapePerHorizon) {
this.mapePerHorizon = mapePerHorizon;
}

@JsonProperty("maximumConfidentHorizon")
public Integer getMaximumConfidentHorizon() {
return maximumConfidentHorizon;
}

@JsonProperty("maximumConfidentHorizon")
public void setMaximumConfidentHorizon(Integer maximumConfidentHorizon) {
this.maximumConfidentHorizon = maximumConfidentHorizon;
}

@JsonProperty("qualityRating")
public Integer getQualityRating() {
return qualityRating;
}

@JsonProperty("qualityRating")
public void setQualityRating(Integer qualityRating) {
this.qualityRating = qualityRating;
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