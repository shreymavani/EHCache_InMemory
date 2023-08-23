package org.example.service;

import java.util.HashMap;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("rtDataService")
public class DirectServiceImpl implements DirectService{

    private static HashMap<String, String> rtData = new HashMap<>();

    static
    {
       rtData = getDummyDatas();
    }

    @Cacheable(value = "rtDatas",
            key = "#ApplicationAndClusterID",
            unless = "#result==null")

    public String getById(String ApplicationAndClusterID)
    {
        System.out.println("<!----------Entering getByName()---------->");
        if(rtData.containsKey(ApplicationAndClusterID))
            return rtData.get(ApplicationAndClusterID);
        return null;
    }

    @CacheEvict(value = "rtDatas", allEntries = true)
    public void refreshAllDatas()
    {
        // This method will remove all 'products' from cache, say as a
        // result of flush API.
    }

    @CachePut(value = "rtDatas", key = "#id", unless = "#result==null")
    public String updateData(String id,String data)
    {
        System.out.println("<!----------Entering updateData --------->");
        if(rtData.containsKey(id))
            return rtData.put(id,data);
        return null;
    }

    private static HashMap<String, String> getDummyDatas()
    {
        HashMap<String, String> rtData = new HashMap<>();
        rtData.put("Spark", "I am Spark");
        rtData.put("Impala", "I am Impala");
        rtData.put("Hive", "I am Hive");
        return rtData;
    }

}
