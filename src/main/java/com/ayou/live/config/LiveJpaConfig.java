package com.ayou.live.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * description
 *
 * @author ysy
 * @date 2019-08-19
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        // repository包名
        basePackages = {"com.ayou.live.repository","com.ayou.live.id"},
        // 实体管理bean名称
        entityManagerFactoryRef = "liveEntityManagerFactory",
        // 事务管理bean名称
        transactionManagerRef = "liveTransactionManager")
public class LiveJpaConfig {
    private static final String[] ENTITY_PACKAGE = {
            "com.ayou.live.domain",
            "com.ayou.live.id",
            "org.axonframework.modelling.saga.repository.jpa",
            "org.axonframework.eventsourcing.eventstore.jpa"
    };
    /**
     * 扫描spring.jpa.newsApi开头的配置信息
     *
     * @return jpa配置信息
     */
//    @Primary
//    @Bean(name = "liveJpaProperties")
//    @ConfigurationProperties(prefix = "spring.jpa.live")
//    public JpaProperties liveJpaProperties() {
//        JpaProperties jpaProperties = new JpaProperties();
//        jpaProperties.setOpenInView(true);
//        return jpaProperties;
//    }
    @Autowired
    private JpaProperties liveJpaProperties;

    /**
     * 获取主库实体管理工厂对象
     *
     * @param dataSource 注入名为liveDataSource的数据源
     * @param builder    注入EntityManagerFactoryBuilder
     * @return 实体管理工厂对象
     */
    @Primary
    @Bean(name = "liveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean liveEntityManagerFactory(@Qualifier("liveDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        return builder
                // 设置数据源
                .dataSource(dataSource)
                // 设置jpa配置
                .properties(liveJpaProperties.getProperties())
                // 设置实体包名
                .packages(ENTITY_PACKAGE)
                // 设置持久化单元名，用于@PersistenceContext注解获取EntityManager时指定数据源
                .persistenceUnit("livePersistenceUnit").build();
    }

    /**
     * 获取实体管理对象
     *
     * @param factory 注入名为newsApiEntityManagerFactory的bean
     * @return 实体管理对象
     */
    @Primary
    @Bean(name = "liveEntityManager")
    public EntityManager liveEntityManager(@Qualifier("liveEntityManagerFactory") EntityManagerFactory factory) {
        return factory.createEntityManager();
    }

    /**
     * 获取主库事务管理对象
     *
     * @param factory 注入名为newsApiEntityManagerFactory的bean
     * @return 事务管理对象
     */
    @Primary
    @Bean(name = "liveTransactionManager")
    public PlatformTransactionManager liveTransactionManager(@Qualifier("liveEntityManagerFactory") EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
