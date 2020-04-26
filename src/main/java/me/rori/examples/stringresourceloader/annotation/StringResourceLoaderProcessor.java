package me.rori.examples.stringresourceloader.annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;


@Component
public class StringResourceLoaderProcessor implements ResourceLoaderAware, BeanPostProcessor {

  Logger logger = LoggerFactory.getLogger(StringResourceLoader.class);

  private ResourceLoader resourceLoader;

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    Field[] fields = bean.getClass().getDeclaredFields();
    for (Field field : fields) {
      if (field.isAnnotationPresent(StringResourceLoader.class)) {
        StringResourceLoader annotation = field.getAnnotation(StringResourceLoader.class);
        try {
          String resourceString = loadResourceToString(annotation.value());
          field.set(bean, resourceString);
          logger.debug("Loaded :\n" + resourceString);
        } catch (IllegalAccessException | IOException e) {
          e.printStackTrace();
          throw new BeanInitializationException("Exception loading resource: " + annotation.value(), e);
        }
      }
    }
    return bean;
  }

  private String loadResourceToString(String resourceLocation) throws IOException {
    Resource resource = resourceLoader.getResource(resourceLocation);
    return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
  }

  @Override
  public void setResourceLoader(final org.springframework.core.io.ResourceLoader resourceLoader) {
    this.resourceLoader = resourceLoader;
  }
}