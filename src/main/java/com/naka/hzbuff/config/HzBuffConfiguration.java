package com.naka.hzbuff.config;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.context.SpringManagedContext;
import com.naka.hzbuff.repository.store.PersonStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hazelcast.repository.config.EnableHazelcastRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableHazelcastRepositories(basePackages = {"com.naka.hzbuff.repository.buffer"})
@EnableJpaRepositories(basePackages = {"com.naka.hzbuff.repository.dao"})
@ComponentScan(basePackages = {"com.naka.hzbuff"})
public class HzBuffConfiguration {

    @Bean
    public SpringManagedContext managedContext() {
        return new SpringManagedContext();
    }

    @Bean
    HazelcastInstance hazelcastInstance() {
        Config config = new Config();
        config.setManagedContext(managedContext());

        MapStoreConfig mapStoreConfig = new MapStoreConfig();
        mapStoreConfig.setClassName(PersonStore.class.getName()).setEnabled(true);

        mapStoreConfig.setWriteDelaySeconds(10);

        MapConfig mapConfig = new MapConfig();
        mapConfig.setName("default");
        mapConfig.setBackupCount(2);
        mapConfig.getMaxSizeConfig().setSize(10000);
        mapConfig.setTimeToLiveSeconds(300);
        mapConfig.setMapStoreConfig(mapStoreConfig);

//        SerializationConfig serializationConfig = config.getSerializationConfig();
//        serializationConfig.setPortableVersion(2).setUseNativeByteOrder(true);
//        serializationConfig.setAllowUnsafe(true).setEnableCompression(true);
//        serializationConfig.setCheckClassDefErrors(true);

//        ManagementCenterConfig managementCenterConfig = new ManagementCenterConfig();
//        managementCenterConfig.setUrl("http://localhost:32769/hazelcast-mancenter/").setEnabled(true);

//        config.setManagementCenterConfig(managementCenterConfig);
//        config.setSerializationConfig(serializationConfig);
        config.addMapConfig(mapConfig);

        return Hazelcast.newHazelcastInstance(config);
    }
}
