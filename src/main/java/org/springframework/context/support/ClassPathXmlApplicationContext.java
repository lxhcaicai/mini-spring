package org.springframework.context.support;

import org.springframework.beans.BeansException;

/**
 * xml文件的应用上下文
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    /**
     * 从xml文件加载BeanDefinition，并且自动刷新上下文
     * @param configLocation
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String configLocation) throws BeansException {
        this(new String[]{configLocation});
    }

    /**
     * 从xml文件加载BeanDefinition，并且自动刷新上下文
     * @param configLocations
     */
    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return this.configLocations;
    }
}
