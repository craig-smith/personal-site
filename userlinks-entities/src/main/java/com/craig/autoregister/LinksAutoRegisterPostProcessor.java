package com.craig.autoregister;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.craig.entity.userlinks.UserLink;
import com.craig.entity.userlinks.service.UserLinksService;

/**
 * Created by csmith on 9/21/2016.
 */
@Component
public class LinksAutoRegisterPostProcessor implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;

    @Autowired
    private UserLinksService userLinksService;

    private Set<UserLink> allLinks;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void afterPropertiesSet() throws Exception {

        final Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(AutoRegisterLinks.class);

        for (final Object o : beansWithAnnotation.values()) {

            AutoRegisterLinks autoRegisterLinks = AnnotationUtils.findAnnotation(o.getClass(), AutoRegisterLinks.class);

            AutoRegisterLink[] links = autoRegisterLinks.autoregisterLinks();
            for (AutoRegisterLink link : links) {
                String url = link.url();
                String description = link.description();
                String acl = link.acl();
                String name = link.name();

                UserLink userLink = new UserLink(url, acl, name, description);
                insertIfnotExist(userLink);
            }

        }
    }

    private void insertIfnotExist(UserLink userLink) {

        if (!allLinks.contains(userLink) ) {
            userLinksService.save(userLink);
            allLinks.add(userLink);
        }
    }

    @PostConstruct
    public void setAllLinks() {
        allLinks = new HashSet<UserLink>();
        allLinks.addAll(userLinksService.getAllLinks());
    }
}
