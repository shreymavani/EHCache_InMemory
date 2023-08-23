package org.example.model;

import java.io.Serializable;

public class RTData implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String ApplicationAndClusterID;
    private String Data;

    public RTData(String applicationAndClusterID, String data) {
        ApplicationAndClusterID = applicationAndClusterID;
        Data = data;
    }

    public String getApplicationAndClusterID() {
        return ApplicationAndClusterID;
    }

    public void setApplicationAndClusterID(String applicationAndClusterID) {
        ApplicationAndClusterID = applicationAndClusterID;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    @Override public String toString() {
        return "RTData{" +
                "ApplicationAndClusterID='" + ApplicationAndClusterID + '\'' +
                ", Data='" + Data + '\'' +
                '}';
    }

}
