package main.java.org.ehcache.sample;

import java.net.URL;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.Configuration;
import org.ehcache.xml.XmlConfiguration;
import org.slf4j.Logger;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;
import static org.slf4j.LoggerFactory.getLogger;

public class ClusteredXML {
  private static final Logger LOGGER = getLogger(ClusteredXML.class);

  public static void main(String[] args) {
    LOGGER.info("Creating clustered cache manager from XML");

    URL myUrl = ClusteredXML.class.getResource("/ehcache.xml");
    Configuration xmlConfig = new XmlConfiguration(myUrl);
    try (CacheManager cacheManager = newCacheManager(xmlConfig)) {
      cacheManager.init();
      
      Cache<Long, String> basicCache = cacheManager.getCache("basicCache", Long.class, String.class);
      
      System.out.println("Getting from cache");
      System.out.println("Retrieved: "+ basicCache.get(1L));
      System.out.println("Retrieved: "+ basicCache.get(2L));
      System.out.println("Closing cache manager");
    }

    System.out.println("Exiting");
  }
}