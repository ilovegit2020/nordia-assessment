package com.app.readwritecache.cache;

import org.springframework.stereotype.Component;

import com.app.readwritecache.entity.PaymentIdentification;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


@Component
public class CacheStorage {

	public PaymentIdentification getDataFromCache() {

		CacheManager cm = CacheManager.getInstance();
		Cache cache = cm.getCache("cache1");

		Element ele = cache.get("1");

		Object output = (ele == null ? null : ele.getObjectValue());
		PaymentIdentification newPid = (PaymentIdentification) output;

		cm.shutdown();
		return newPid;
	}
}
