package org.example.service;

import org.example.model.RTData;
import java.util.ArrayList;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

//@Service("rtDataService")
public class RTDataServiceImpl implements RTDataService
{

    private static List<RTData> rtDatas;

    static
    {
        rtDatas = getDummyProducts();
    }

    @Cacheable(value = "rtDatas",
            key = "#ApplicationAndClusterID",
            condition = "#ApplicationAndClusterID!='54322_5332'",
            unless = "#result==null")

    public RTData getById(String ApplicationAndClusterID)
    {
        System.out.println("<!----------Entering getByName()---------->");
        for (RTData r : rtDatas)
        {
            if (r.getApplicationAndClusterID().equalsIgnoreCase(ApplicationAndClusterID))
                return r;
        }
        return null;
    }

    @CacheEvict(value = "rtDatas", allEntries = true)
    public void refreshAllDatas()
    {
        // This method will remove all 'products' from cache, say as a
        // result of flush API.
    }

    @CachePut(value = "rtDatas", key = "#rtData.ApplicationAndClusterID", unless = "#result==null")
    public RTData updateData(RTData rtData)
    {
        System.out.println("<!----------Entering updateData --------->");
        for (RTData r : rtDatas)
        {
            if (r.getApplicationAndClusterID().equalsIgnoreCase(rtData.getApplicationAndClusterID()))
                r.setData(rtData.getData());
            return r;
        }
        return null;
    }

    private static List<RTData> getDummyProducts()
    {
        List<RTData> rtDatas = new ArrayList<RTData>();
        rtDatas.add(new RTData("12345_9876", "Its me spark"));
        rtDatas.add(new RTData("89677_5678", "Its me Impala"));
        rtDatas.add(new RTData("54322_5332", "Its me Hive"));


        for(RTData r:rtDatas)
            System.out.println(r.getApplicationAndClusterID());
        return rtDatas;
    }

}
