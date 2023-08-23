package org.example.service;

import org.example.model.CompositeKey;

public interface CompositeKeyService {
    String getById(CompositeKey id);

    void refreshAllDatas();

    String updateData(CompositeKey id,String data);
}
