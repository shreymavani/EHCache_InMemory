package org.example.model;

import java.io.Serializable;
import java.util.Objects;

public class CompositeKey implements Serializable {
    private String applicationId;
    private String clusterId;
    private String engineType;

    public CompositeKey(String applicationId, String clusterId, String engineType) {
        this.applicationId = applicationId;
        this.clusterId = clusterId;
        this.engineType = engineType;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getClusterId() {
        return clusterId;
    }

    public String getEngineType() {
        return engineType;
    }

    // Override equals and hashCode methods based on the composite key components
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeKey that = (CompositeKey) o;

        if (!applicationId.equals(that.applicationId)) return false;
        if (!clusterId.equals(that.clusterId)) return false;
        return engineType.equals(that.engineType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(applicationId, clusterId, engineType);
    }
}
