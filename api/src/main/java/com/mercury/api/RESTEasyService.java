package com.mercury.api;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.*;

@ApplicationPath("/rest")
public class RESTEasyService extends Application {

    private static Logger LOG = LoggerFactory.getLogger(RESTEasyService.class);

    private Set<Object> singletons = new HashSet<>();

    public RESTEasyService() {
        List<ClassLoader> classLoadersList = new ArrayList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner(), new TypeAnnotationsScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.leonardorifeli.ca.clock.api"))));

        Set<Class<? extends Object>> allClasses = reflections.getTypesAnnotatedWith(javax.ws.rs.Path.class);
        for (Class clazz : allClasses) {
            try {
                singletons.add(clazz.newInstance());
            } catch (InstantiationException e) {
                LOG.error("Error instanciating object for class "+clazz.getName(), e);
            } catch (IllegalAccessException e) {
                LOG.error("Error instanciating object for class "+clazz.getName(), e);
            }
        }
    }

    @Override
    public Set<Object> getSingletons() {
        return this.singletons;
    }
}