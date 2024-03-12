package com.mycompany.app;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    public static int[] processLists(ArrayList<Integer> list1, ArrayList<Integer> list2, int min, int max) {
        ArrayList<Integer> commonElements = new ArrayList<>();
        for (Integer num : list1) {
            if (list2.contains(num) && num >= min && num <= max) {
                commonElements.add(num);
            }
        }

        int sum = 0;
        for (Integer num : commonElements) {
            sum += num;
        }

        return new int[]{sum, commonElements.size()};
    }

    public static void main(String[] args) {
        port(getHerokuAssignedPort());

        get("/", (req, res) -> "Hello, World");

        post("/compute", (req, res) -> {
            // Extract and parse the first list
            String input1 = req.queryParams("input1");
            Scanner sc1 = new Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            ArrayList<Integer> list1 = new ArrayList<>();
            while (sc1.hasNext()) {
                int value = Integer.parseInt(sc1.next().replaceAll("\\s", ""));
                list1.add(value);
            }
            sc1.close(); // Close the scanner to avoid memory leaks

            // Extract and parse the second list
            String input2 = req.queryParams("input2");
            Scanner sc2 = new Scanner(input2);
            sc2.useDelimiter("[;\r\n]+");
            ArrayList<Integer> list2 = new ArrayList<>();
            while (sc2.hasNext()) {
                int value = Integer.parseInt(sc2.next().replaceAll("\\s", ""));
                list2.add(value);
            }
            sc2.close(); // Close the scanner to avoid memory leaks

            // Extract min and max values
            int min = Integer.parseInt(req.queryParams("input3").replaceAll("\\s", ""));
            int max = Integer.parseInt(req.queryParams("input4").replaceAll("\\s", ""));

            // Call the processLists method
            int[] result = processLists(list1, list2, min, max);

            // Prepare the response
            Map<String, Object> map = new HashMap<>();
            map.put("sum", result[0]);
            map.put("count", result[1]);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute", (rq, rs) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("result", "not computed yet!");
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}