package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by elena on 29/05/16.
 */
public class HelloWorldSparkFreemarkerStyle {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldSparkFreemarkerStyle.class, "/");

        Spark.get(new Route("/") { //web app homepage

            @Override
            public Object handle(Request arg0, Response arg1) {
                StringWriter stringWriter = new StringWriter();

                try {
                    Template template = configuration.getTemplate("hello.ftl");
                    Map<String, Object> helloMap = new HashMap<String, Object>();
                    helloMap.put("name", "Freemarker");

                    template.process(helloMap, stringWriter);

                    System.out.println(stringWriter);

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }

                return stringWriter;
            }
        });


    }
}
