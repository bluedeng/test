package classes.caffeine;

import classes.JavaEmail;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

import static Utils.BasicUtils.println;
import static Utils.BasicUtils.sleep;

/**
 * @author dengtianzhi
 * @version 1.0
 * @created 2019/1/29.
 */
public class JavaCaffeine {

    public static void main(String... args) {
//        println("test no record stat: ");
//        testNumericCache(numericCacheWithoutRecordStat);

//        println("\ntest record stat: ");
//        testNumericCache(numericCacheWithRecordStat);

//        println("\ntest expire after access:");
//        testNumericCache(numericCacheWithExpireAfterAccess);

//        println("\ntest maximum size:");
//        testMaximumSize();

//        println("\ntest loading cache");
//        testLoadingCache();
        println("\ntest object cache");
        testObjectCache();
    }

    /**
     * initial cache, which do not record stats
     */
    private static Cache<Integer, Integer> numericCacheWithoutRecordStat = Caffeine.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(Duration.ofSeconds(5))
            .expireAfterAccess(Duration.ofSeconds(5))
            .build();

    /**
     * initial cache, which record stats
     */
    private static Cache<Integer, Integer> numericCacheWithRecordStat = Caffeine.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(Duration.ofSeconds(5))
            .expireAfterAccess(Duration.ofSeconds(5))
            .recordStats()
            .build();

    /**
     * initial cache, which set expireAfterAccess
     */
    private static Cache<Integer, Integer> numericCacheWithExpireAfterAccess = Caffeine.newBuilder()
            .maximumSize(10)
            .expireAfterAccess(Duration.ofSeconds(5))
            .recordStats()
            .build();

    /**
     * initial cache, which set maximum size
     */
    private static Cache<Integer, Integer> numericCacheWithMaximumSize = Caffeine.newBuilder()
            .maximumSize(10)
            .build();

    private static LoadingCache<Integer, Integer> numericLoadingCache = Caffeine.newBuilder()
            .maximumSize(10)
            .build(key -> key);

    private static Cache<Object, Object> objectCache = Caffeine.newBuilder()
            .maximumSize(10)
            .build();

    /**
     * this test tell us
     * if we initial cache without recordStats(), we can not obtain stats information about the cache
     * if we initial cache with recordStats(), it can give us stats information about cache with performance penalty
     *
     * get if present will return null if cache do not contains the key
     * get with function will execute the function first and then return the value if cache do not contains
     *
     * when we set expireAfterWrite and expireAfterAccess, this two expire time are unconnected
     * when we set expireAfterWrite, the key will expire after setting time from last change
     * when we set expireAfterAccess, the key will expire after setting time from last access
     */
    private static void testNumericCache(Cache<Integer, Integer> cache) {
        println(cache.getIfPresent(111));
        println(cache.get(111, (key) -> key));
        println(cache.stats());
        sleep(3);
        println(cache.stats());
        println(cache.getIfPresent(111));
        sleep(3);
        println(cache.asMap());
        println(cache.stats());
        println(cache.getIfPresent(111));
        println(cache.stats());
        sleep(3);
        println(cache.asMap());
        sleep(3);
        println(cache.asMap());
    }

    /**
     * insert many keys in a short time may not trigger the maximum size restrict
     * if keys in cache reach the maximum size restrict, it will evicts by access count, random when keys share same
     * count
     */
    private static void testMaximumSize() {
        Cache<Integer, Integer> cache = numericCacheWithMaximumSize;
        println(cache.asMap());
        int i = 0;
        while (i < 20) {
            cache.put(i, i);
            println(cache.getIfPresent(ThreadLocalRandom.current().nextInt(20)));
            println(cache.asMap() + "_" + cache.asMap().size());
//            sleep(1);
            i++;
        }

        while (i < 25) {
            cache.put(i, i);
            sleep(1);
            println(cache.asMap() + "_" + cache.asMap().size());
            i++;
        }
    }

    private static void testLoadingCache() {
        LoadingCache<Integer, Integer> loadingCache = numericLoadingCache;
        println(loadingCache.asMap());
        int i = 1;
        while (i < 30) {
            println(i + "_" + loadingCache.get(ThreadLocalRandom.current().nextInt(i)));
            println(loadingCache.asMap() + "_" + loadingCache.asMap().size());
            sleep(1);
            i++;
        }
    }

    private static void testObjectCache() {
        int i = 1;
        while (i < 20) {
            Object value = objectCache.get(ThreadLocalRandom.current().nextInt(), JavaCaffeine::resolveValue);
            println(value + "_" + value.getClass());
            println(i + "_" + objectCache.asMap() + "_" + objectCache.asMap().size());
            i++;
            sleep(1);
        }
    }

    private static Object resolveValue(Object obj) {
        int hashCode = obj.hashCode();
        if (hashCode % 5 == 0) {
            return 5;
        } else if (hashCode % 3 == 0) {
            return "" + hashCode;
        } else if (hashCode % 2 == 0) {
            return CompletableFuture.supplyAsync(() -> hashCode);
        } else {
            return new JavaEmail("java", "email");
        }
    }
}
