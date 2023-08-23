package org.example.service;

import org.example.model.Product;

public interface DirectService {
    String getById(String id);

    void refreshAllDatas();

    String updateData(String id,String data);
}
