package com.sap.pm.model;
public class ForecastBody
{
    private String referenceDate;

    private int numberOfForecasts;

    private String dateColumn;

    private int datasetID;

    private String targetColumn;

    public String getReferenceDate ()
    {
        return referenceDate;
    }

    public void setReferenceDate (String referenceDate)
    {
        this.referenceDate = referenceDate;
    }

    public int getNumberOfForecasts ()
    {
        return numberOfForecasts;
    }

    public void setNumberOfForecasts (int numberOfForecasts)
    {
        this.numberOfForecasts = numberOfForecasts;
    }

    public String getDateColumn ()
    {
        return dateColumn;
    }

    public void setDateColumn (String dateColumn)
    {
        this.dateColumn = dateColumn;
    }

    public int getDatasetID ()
    {
        return datasetID;
    }

    public void setDatasetID (int datasetID)
    {
        this.datasetID = datasetID;
    }

    public String getTargetColumn ()
    {
        return targetColumn;
    }

    public void setTargetColumn (String targetColumn)
    {
        this.targetColumn = targetColumn;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [referenceDate = "+referenceDate+", numberOfForecasts = "+numberOfForecasts+", dateColumn = "+dateColumn+", datasetID = "+datasetID+", targetColumn = "+targetColumn+"]";
    }
}