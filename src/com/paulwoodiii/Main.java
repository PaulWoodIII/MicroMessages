package com.paulwoodiii;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    public static MustacheTemplateEngine templateEngine = new MustacheTemplateEngine();
    static User currentUser;

    public static void main(String[] args) {
	// write your code here
        Spark.staticFileLocation("/public");

        Spark.get(
                "/",
                (request, response) -> {
                    HashMap m = new HashMap();
                    m.put("user",currentUser);
                    return new ModelAndView(m,"home.html");
                },
                new MustacheTemplateEngine()
        );
        Spark.post(
                "/post",
                (request, response) -> {
                    if(null != request.queryParams("message")){
                        Post p = new Post();
                        p.content = request.queryParams("message");
                        currentUser.posts.add(p);
                    }
                    response.redirect("/");
                    return null;
                });


        Spark.post(
                "/login",
                (request, response) -> {
                    if(null != request.queryParams("username") &&
                            null != request.queryParams("password") ){
                        currentUser = new User();
                        currentUser.username =  request.queryParams("username");
                        currentUser.password =  request.queryParams("password");
                    }
                    response.redirect("/");
                    return null;
                });

        Spark.post(
                "/logout",
                (request, response) -> {
                    currentUser = null;
                    response.redirect("/");
                    return null;
                });

    }
}
