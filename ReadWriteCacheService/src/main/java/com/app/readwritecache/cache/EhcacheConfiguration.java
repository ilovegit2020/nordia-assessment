package com.app.readwritecache.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@Configuration
public class EhcacheConfiguration {

	@Bean
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(cacheManagerfactory().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean cacheManagerfactory() {
		EhCacheManagerFactoryBean ehBean = new EhCacheManagerFactoryBean();
		ehBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		ehBean.setShared(true);
		return ehBean;
	}
}
