package com.sap.pm.pojo;
import java.util.Date;
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
"date",
"errorBarHigherBound",
"errorBarLowerBound",
"forecastValue",
"realValue"
})
public class Forecast {

@JsonProperty("date")
private Date date;
@JsonProperty("errorBarHigherBound")
private Double errorBarHigherBound;
@JsonProperty("errorBarLowerBound")
private Double errorBarLowerBound;
@JsonProperty("forecastValue")
private Double forecastValue;
@JsonProperty("realValue")
private Double realValue;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("date")
public Date getDate() {
return date;
}

@JsonProperty("date")
public void setDate(Date date) {
this.date = date;
}

@JsonProperty("errorBarHigherBound")
public Double getErrorBarHigherBound() {
return errorBarHigherBound;
}

@JsonProperty("errorBarHigherBound")
public void setErrorBarHigherBound(Double errorBarHigherBound) {
this.errorBarHigherBound = errorBarHigherBound;
}

@JsonProperty("errorBarLowerBound")
public Double getErrorBarLowerBound() {
return errorBarLowerBound;
}

@JsonProperty("errorBarLowerBound")
public void setErrorBarLowerBound(Double errorBarLowerBound) {
this.errorBarLowerBound = errorBarLowerBound;
}

@JsonProperty("forecastValue")
public Double getForecastValue() {
return forecastValue;
}

@JsonProperty("forecastValue")
public void setForecastValue(Double forecastValue) {
this.forecastValue = forecastValue;
}

@JsonProperty("realValue")
public Double getRealValue() {
return realValue;
}

@JsonProperty("realValue")
public void setRealValue(Double realValue) {
this.realValue = realValue;
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