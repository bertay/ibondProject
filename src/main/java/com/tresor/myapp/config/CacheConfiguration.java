package com.tresor.myapp.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import org.hibernate.cache.jcache.ConfigSettings;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import io.github.jhipster.config.cache.PrefixedKeyGenerator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {
    private GitProperties gitProperties;
    private BuildProperties buildProperties;
    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache = jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            createCache(cm, com.tresor.myapp.repository.UserRepository.USERS_BY_LOGIN_CACHE);
            createCache(cm, com.tresor.myapp.repository.UserRepository.USERS_BY_EMAIL_CACHE);
            createCache(cm, com.tresor.myapp.domain.User.class.getName());
            createCache(cm, com.tresor.myapp.domain.Authority.class.getName());
            createCache(cm, com.tresor.myapp.domain.User.class.getName() + ".authorities");
            createCache(cm, com.tresor.myapp.domain.NatureTitre.class.getName());
            createCache(cm, com.tresor.myapp.domain.NatureTitre.class.getName() + ".emissions");
            createCache(cm, com.tresor.myapp.domain.AvisEmission.class.getName());
            createCache(cm, com.tresor.myapp.domain.AvisEmission.class.getName() + ".emissions");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName());
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".soumissions");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".reouvertures");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".rachats");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".oncs");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".classements");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".resultats");
            createCache(cm, com.tresor.myapp.domain.Emission.class.getName() + ".membreSyndicats");
            createCache(cm, com.tresor.myapp.domain.Pays.class.getName());
            createCache(cm, com.tresor.myapp.domain.Pays.class.getName() + ".svts");
            createCache(cm, com.tresor.myapp.domain.Svt.class.getName());
            createCache(cm, com.tresor.myapp.domain.Soumission.class.getName());
            createCache(cm, com.tresor.myapp.domain.Soumission.class.getName() + ".detailSoumissions");
            createCache(cm, com.tresor.myapp.domain.DetailSoumission.class.getName());
            createCache(cm, com.tresor.myapp.domain.DetailSoumission.class.getName() + ".classements");
            createCache(cm, com.tresor.myapp.domain.Classement.class.getName());
            createCache(cm, com.tresor.myapp.domain.Adjudication.class.getName());
            createCache(cm, com.tresor.myapp.domain.Resultat.class.getName());
            createCache(cm, com.tresor.myapp.domain.Parametres.class.getName());
            createCache(cm, com.tresor.myapp.domain.Calendrier.class.getName());
            createCache(cm, com.tresor.myapp.domain.Calendrier.class.getName() + ".detailCalendriers");
            createCache(cm, com.tresor.myapp.domain.DetailCalendrier.class.getName());
            createCache(cm, com.tresor.myapp.domain.MembreSyndicat.class.getName());
            createCache(cm, com.tresor.myapp.domain.Reouverture.class.getName());
            createCache(cm, com.tresor.myapp.domain.Reouverture.class.getName() + ".soumissions");
            createCache(cm, com.tresor.myapp.domain.Reouverture.class.getName() + ".adjudications");
            createCache(cm, com.tresor.myapp.domain.Reouverture.class.getName() + ".classements");
            createCache(cm, com.tresor.myapp.domain.Reouverture.class.getName() + ".resultats");
            createCache(cm, com.tresor.myapp.domain.Rachat.class.getName());
            createCache(cm, com.tresor.myapp.domain.Rachat.class.getName() + ".soumissions");
            createCache(cm, com.tresor.myapp.domain.Rachat.class.getName() + ".adjudications");
            createCache(cm, com.tresor.myapp.domain.Rachat.class.getName() + ".classements");
            createCache(cm, com.tresor.myapp.domain.Rachat.class.getName() + ".resultats");
            createCache(cm, com.tresor.myapp.domain.Onc.class.getName());
            createCache(cm, com.tresor.myapp.domain.Onc.class.getName() + ".soumissions");
            createCache(cm, com.tresor.myapp.domain.Onc.class.getName() + ".adjudications");
            createCache(cm, com.tresor.myapp.domain.Onc.class.getName() + ".classements");
            createCache(cm, com.tresor.myapp.domain.Onc.class.getName() + ".resultats");
            // jhipster-needle-ehcache-add-entry
        };
    }

    private void createCache(javax.cache.CacheManager cm, String cacheName) {
        javax.cache.Cache<Object, Object> cache = cm.getCache(cacheName);
        if (cache == null) {
            cm.createCache(cacheName, jcacheConfiguration);
        }
    }

    @Autowired(required = false)
    public void setGitProperties(GitProperties gitProperties) {
        this.gitProperties = gitProperties;
    }

    @Autowired(required = false)
    public void setBuildProperties(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @Bean
    public KeyGenerator keyGenerator() {
        return new PrefixedKeyGenerator(this.gitProperties, this.buildProperties);
    }
}
