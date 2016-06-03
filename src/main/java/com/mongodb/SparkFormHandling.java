package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by elena on 29/05/16.
 */
public class SparkFormHandling {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SparkFormHandling.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                Map<String, Object> fruits = new HashMap<String, Object>();
                fruits.put("fruits", Arrays.asList("apple", "orange", "banana","peach"));

                try {
                    Template template = configuration.getTemplate("freemarker/fruitPicker.ftl");
                    StringWriter writer = new StringWriter();
                    template.process(fruits, writer);

                    return writer;

                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                    return null;
                }
            }
        });

        Spark.post(new Route("/favorite_fruit") {
            @Override
            public Object handle(Request request, Response response) {
                String fruit = request.queryParams("fruit");
                if(fruit == null){
                    return "Why don't you pick one?";
                } else {
                    return "Your favourite fruit is " + fruit;
                }
            }
        });


    }
}
