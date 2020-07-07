package com.app.readwritecache.batch.item;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.app.readwritecache.entity.PaymentIdentification;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class WriteToStoreDataInCache implements ItemWriter<PaymentIdentification> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WriteToStoreDataInCache.class);
	
	@Override
	public void write(List<? extends PaymentIdentification> paymentIds) throws Exception {

		LOGGER.info("INSIDE WriteToStoreDataInCache -> ");
		for (PaymentIdentification paymentIdentification : paymentIds) {
			System.out.println(" --> FROm Writer Job paymentIdentification " + paymentIdentification);
			CacheManager cm = CacheManager.getInstance();
			Cache cache = cm.getCache("cache1");
			cache.put(new Element("1", paymentIdentification));

			System.out.println(" EndToEndId is successfuly stored in Cache");
		}

	}

}
