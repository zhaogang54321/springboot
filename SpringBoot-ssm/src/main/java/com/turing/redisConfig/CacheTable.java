package com.turing.redisConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@ConfigurationProperties(prefix="redis")
public class CacheTable {

	private List<String> cacheList = new ArrayList<String>();

	public List<String> getCacheList() {
		return cacheList;
	}

	public void setCacheList(List<String> cacheList) {
		this.cacheList = cacheList;
	}
}
