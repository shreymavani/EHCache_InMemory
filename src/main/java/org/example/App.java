package org.example;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.Attribute;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;
import org.example.configuration.AppConfig;
import org.example.model.CompositeKey;
import org.example.service.CompositeKeyService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App
{
	public static void main(String[] args)
	{

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		CompositeKeyService service2 = (CompositeKeyService) context.getBean("rtCompService");		//Service for rtCompService Cache

		CompositeKey cp = new CompositeKey("09687","123456","Spark");
		CompositeKey cp1 = new CompositeKey("09687","123456","Hive");

		System.out.println("Application ID ->" + service2.getById(cp));		//Fetching from Cache if Available else fetching from DataBase
		System.out.println("Application ID ->" + service2.getById(cp));

		System.out.println("Application ID ->" + service2.getById(cp1));			//Fetching from Cache if Available else fetching from DataBase
		System.out.println("Application ID ->" + service2.getById(cp1));

		service2.updateData(cp,"I am Lord Yarn Composite");					//Update in DataBase

		System.out.println("Application ID ->" + service2.getById(cp));				//Fetching from Cache if Available else fetching from DataBase
		System.out.println("Application ID ->" + service2.getById(cp));

		System.out.println("Refreshing the Cache/Clearing the Cache Data");

		service2.refreshAllDatas();

		System.out.println("Application ID ->" + service2.getById(cp));				//Fetching from Cache if Available else fetching from DataBase
		System.out.println("Application ID ->" + service2.getById(cp));

		System.out.println("Application ID ->" + service2.getById(cp1));			//Fetching from Cache if Available else fetching from DataBase
		System.out.println("Application ID ->" + service2.getById(cp1));


		CacheManager cacheManager = CacheManager.getInstance();						//CacheManager Instance
		final Cache rtComp =  cacheManager.getCache("rtCompCache");			//Getting rtCompCache

		rtComp.put(new Element(cp,"New Data")); 									//Direct Cache Insert and Update
		System.out.println(rtComp.getSize());										//Size of Cache

		Query query = rtComp.createQuery()											//Query on Searchable Attributes
				.includeKeys().includeValues()
				.addCriteria(new Attribute("applicationId").eq("09687"))
				.addCriteria(new Attribute("clusterId").eq("123456"))
				.addCriteria(new Attribute("engineType").eq("Spark"));

		Results results = query.execute();											//Executing Query
		System.out.println(results.size());											//Query Result Size


		for (Result element : results.all()) {										//Printing the Query Results
			System.out.println(element.getValue());
		}
		
		((AbstractApplicationContext) context).close();
	}

}
