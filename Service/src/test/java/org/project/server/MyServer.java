package org.project.server;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.project.config.AppConfigTest;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
public class MyServer {
    private static Tomcat tomcat;
    public static void main(String[] args) throws LifecycleException {
        tomcat = new Tomcat();

        final Connector connector = new Connector();
        connector.setPort(8084);
        connector.setScheme("http");
        connector.setSecure(false);
        tomcat.setConnector(connector);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfigTest.class);

        Context tomcatContext = tomcat.addContext("", null);
        tomcatContext.setParentClassLoader(MyServer.class.getClassLoader());

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        Tomcat.addServlet(tomcatContext, "dispatcherServlet", dispatcherServlet);
        tomcatContext.addServletMappingDecoded("/", "dispatcherServlet");

        tomcat.start();
        tomcat.getServer().await();
    }
}
