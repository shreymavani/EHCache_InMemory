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
import org.example.model.Product;
import org.example.model.RTData;
import org.example.service.CompositeKeyService;
import org.example.service.DirectService;
import org.example.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App
{
	public static void main(String[] args)
	{

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class);

		ProductService service = (ProductService) context.getBean("productService");
		DirectService service1 = (DirectService) context.getBean("rtDataService");
		CompositeKeyService service2 = (CompositeKeyService) context.getBean("rtCompService");

		System.out.println("LED TV ->" + service.getByName("LED TV"));
		System.out.println("LED TV ->" + service.getByName("LED TV"));
		System.out.println("LED TV ->" + service.getByName("LED TV"));

		System.out.println("Phone ->" + service.getByName("Phone"));
		System.out.println("Phone ->" + service.getByName("Phone"));
		System.out.println("Phone ->" + service.getByName("Phone"));

		System.out.println("Application ID ->" + service1.getById("Spark"));
		System.out.println("Application ID ->" + service1.getById("Spark"));
		System.out.println("Application ID ->" + service1.getById("Spark"));
//
		System.out.println("Application ID ->" + service1.getById("Hive"));
		System.out.println("Application ID ->" + service1.getById("Hive"));
		System.out.println("Application ID ->" + service1.getById("Hive"));

		CompositeKey cp = new CompositeKey("09687","123456","Spark");
		CompositeKey cp1 = new CompositeKey("09687","123456","Hive");

		System.out.println("Application ID ->" + service2.getById(cp));
		System.out.println("Application ID ->" + service2.getById(cp));
		System.out.println("Application ID ->" + service2.getById(cp));

		System.out.println("Application ID ->" + service2.getById(cp1));
		System.out.println("Application ID ->" + service2.getById(cp1));
		System.out.println("Application ID ->" + service2.getById(cp1));

		Product product = new Product("Fridge", 550);
		RTData rtData= new RTData("89677_5678", "I am Lord Yarn");
		service.updateProduct(product);
		service1.updateData("Spark","I am Lord Yarn");
		service2.updateData(cp,"I am Lord Yarn Composite");

		System.out.println("LED TV ->" + service.getByName("LED TV"));
		System.out.println("LED TV ->" + service.getByName("LED TV"));
		System.out.println("LED TV ->" + service.getByName("LED TV"));

		System.out.println("Application ID ->" + service1.getById("Spark"));
		System.out.println("Application ID ->" + service1.getById("Spark"));
		System.out.println("Application ID ->" + service1.getById("Spark"));

		System.out.println("Application ID ->" + service2.getById(cp));
		System.out.println("Application ID ->" + service2.getById(cp));
		System.out.println("Application ID ->" + service2.getById(cp));

		System.out.println("Refreshing all products");

		service.refreshAllProducts();
		service1.refreshAllDatas();
		service2.refreshAllDatas();

		System.out.println("LED TV [after refresh]->" + service.getByName("LED TV"));
		System.out.println("LED TV [after refresh]->" + service.getByName("LED TV"));
		System.out.println("LED TV [after refresh]->" + service.getByName("LED TV"));

		System.out.println("Application ID ->" + service1.getById("Spark"));
		System.out.println("Application ID ->" + service1.getById("Spark"));
		System.out.println("Application ID ->" + service1.getById("Spark"));

		System.out.println("Application ID ->" + service2.getById(cp));
		System.out.println("Application ID ->" + service2.getById(cp));
		System.out.println("Application ID ->" + service2.getById(cp));

		System.out.println("Application ID ->" + service2.getById(cp1));
		System.out.println("Application ID ->" + service2.getById(cp1));
		System.out.println("Application ID ->" + service2.getById(cp1));

		CacheManager cacheManager = CacheManager.getInstance();
		final Cache rtComp =  cacheManager.getCache("rtCompCache");

		System.out.println(rtComp.getSize());

		Query query = rtComp.createQuery()
				.includeKeys().includeValues()
				.addCriteria(new Attribute("applicationId").eq("09687"))
				.addCriteria(new Attribute("clusterId").eq("123456"))
				.addCriteria(new Attribute("engineType").eq("Spark"));

//		Element cachedElement = rtComp.get(cp);
//		if (cachedElement != null) {
//			Object cachedData = cachedElement.getObjectValue();
//			// Process the cachedData as needed
//			System.out.println(cachedData.toString());
//		}
		Results results = query.execute();
//		System.out.println(results.size());

		for (Result element : results.all()) {
			System.out.println(element.getValue());
		}
		
		((AbstractApplicationContext) context).close();
	}

}
