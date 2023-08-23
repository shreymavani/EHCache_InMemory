package org.example.service;

import java.util.HashMap;
import org.example.model.CompositeKey;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("rtCompService")
public class CompositeKeyServiceImpl implements CompositeKeyService{
    private static HashMap<CompositeKey, String> rtComp = new HashMap<>();

    static
    {
        rtComp = getDummyDatas();
    }

    @Cacheable(value = "rtCompCache",
            key = "#ApplicationAndClusterID",
            unless = "#result==null")

    public String getById(CompositeKey ApplicationAndClusterID)
    {
        System.out.println("<!----------Entering getByName()---------->");
        if(rtComp.containsKey(ApplicationAndClusterID))
            return rtComp.get(ApplicationAndClusterID);
        return null;
    }

    @CacheEvict(value = "rtCompCache", allEntries = true)
    public void refreshAllDatas()
    {
        // This method will remove all 'products' from cache, say as a
        // result of flush API.
    }

    @CachePut(value = "rtCompCache", key = "#id", unless = "#result==null")
    public String updateData(CompositeKey id,String data)
    {
        System.out.println("<!----------Entering updateData --------->");
        if(rtComp.containsKey(id)){rtComp.put(id,data);System.out.println(rtComp.get(id));}
        return null;
    }

    private static HashMap<CompositeKey, String> getDummyDatas()
    {
        HashMap<CompositeKey, String> rtComp = new HashMap<>();
        CompositeKey cp = new CompositeKey("09687","123456","Spark");
        CompositeKey cp1 = new CompositeKey("09687","123456","Hive");
        CompositeKey cp2 = new CompositeKey("09687","123456","Impala");

        rtComp.put(cp, "I am Spark");
        rtComp.put(cp1, "I am Impala");
        rtComp.put(cp2, "I am Hive");
        return rtComp;
    }

}
