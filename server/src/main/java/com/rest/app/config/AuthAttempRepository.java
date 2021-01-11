/**
 * 
 */
package com.rest.app.config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author danielf
 * @see https://www.baeldung.com/spring-security-block-brute-force-authentication-attempts
 * @see https://www.baeldung.com/guava-cacheloader
 */
@Service
public class AuthAttempRepository {

	private final int MAX_ATTEMPT = 3;
	private LoadingCache<String, Integer> attemptsCache;

	public AuthAttempRepository() {
		super();
		attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(5, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public void loginSucceeded(String key) {
		attemptsCache.invalidate(key);
	}

	public void loginFailed(String key) {
		int attempts = 0;
		try {
			attempts = attemptsCache.get(key);
		} catch (ExecutionException e) {
			attempts = 0;
		}
		attempts++;
		attemptsCache.put(key, attempts);
	}

	public boolean isBlocked(String key) {
		try {
			return attemptsCache.get(key) >= MAX_ATTEMPT;
		} catch (ExecutionException e) {
			return false;
		}
	}
}