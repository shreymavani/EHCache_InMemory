package org.example.service;


import org.example.model.RTData;

public interface RTDataService {

    RTData getById(String ApplicationAndClusterID);

    void refreshAllDatas();

    RTData updateData(RTData rtData);
}
