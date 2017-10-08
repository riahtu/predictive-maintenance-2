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
"datasetID",
"dateColumn",
"numberOfForecasts",
"referenceDate",
"targetColumn"
})
public class Parameters {

@JsonProperty("datasetID")
private Integer datasetID;
@JsonProperty("dateColumn")
private String dateColumn;
@JsonProperty("numberOfForecasts")
private Integer numberOfForecasts;
@JsonProperty("referenceDate")
private Date referenceDate;
@JsonProperty("targetColumn")
private String targetColumn;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("datasetID")
public Integer getDatasetID() {
return datasetID;
}

@JsonProperty("datasetID")
public void setDatasetID(Integer datasetID) {
this.datasetID = datasetID;
}

@JsonProperty("dateColumn")
public String getDateColumn() {
return dateColumn;
}

@JsonProperty("dateColumn")
public void setDateColumn(String dateColumn) {
this.dateColumn = dateColumn;
}

@JsonProperty("numberOfForecasts")
public Integer getNumberOfForecasts() {
return numberOfForecasts;
}

@JsonProperty("numberOfForecasts")
public void setNumberOfForecasts(Integer numberOfForecasts) {
this.numberOfForecasts = numberOfForecasts;
}

@JsonProperty("referenceDate")
public Date getReferenceDate() {
return referenceDate;
}

@JsonProperty("referenceDate")
public void setReferenceDate(Date referenceDate) {
this.referenceDate = referenceDate;
}

@JsonProperty("targetColumn")
public String getTargetColumn() {
return targetColumn;
}

@JsonProperty("targetColumn")
public void setTargetColumn(String targetColumn) {
this.targetColumn = targetColumn;
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