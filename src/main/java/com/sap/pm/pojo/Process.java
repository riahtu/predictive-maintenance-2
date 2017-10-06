
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
    "process",
    "state",
    "metrics"
})
public class Process {

    @JsonProperty("process")
    private String process;
    @JsonProperty("state")
    private String state;
    @JsonProperty("metrics")
    private List<Metric> metrics = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("process")
    public String getProcess() {
        return process;
    }

    @JsonProperty("process")
    public void setProcess(String process) {
        this.process = process;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("metrics")
    public List<Metric> getMetrics() {
        return metrics;
    }

    @JsonProperty("metrics")
    public void setMetrics(List<Metric> metrics) {
        this.metrics = metrics;
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
