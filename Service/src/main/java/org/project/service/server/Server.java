package org.project.service.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.project.service.config.AppConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Server {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();

        final Connector connector = new Connector();
        connector.setPort(8085);
        connector.setScheme("http");
        connector.setSecure(false);
        tomcat.setConnector(connector);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        Context tomcatContext = tomcat.addContext("", null);
        tomcatContext.setParentClassLoader(Server.class.getClassLoader());

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        tomcatContext.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
