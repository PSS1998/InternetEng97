package com.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Project> projects;
    public static List<Skill>skills;

    public static List<Bid>bids = new ArrayList<Bid>();

    public static User user = new User(
            "1",
          "علی",
            "شریف زاده",
            "برنامه نویس وب",
            "",
            new ArrayList <Skill>(){{
                add(new Skill("HTML",5));
                add(new Skill("Javascript",4));
                add(new Skill("C++",2));
                add(new Skill("Java",3));
            }},
            "روی سنگ قبرم بنویسید: خدا بیامورز می خواست خیلی کارا بکنه ولی پول نداشت."
    );

    public static List<User> users = new ArrayList<User>() {{
        add(new User(
                "1",
                "علی",
                "شریف زاده",
                "برنامه نویس وب",
                "",
                new ArrayList <Skill>(){{
                    add(new Skill("HTML",5));
                    add(new Skill("Javascript",4));
                    add(new Skill("C++",2));
                    add(new Skill("Java",3));
                }},
                "روی سنگ قبرم بنویسید: خدا بیامورز می خواست خیلی کارا بکنه ولی پول نداشت."
        ));
        add(new User(
               "2",
               "محمد",
               "علی زاده",
               "برنامه نویس فرانت اند",
               "",
               new ArrayList <Skill>(){{
                   add(new Skill("HTML",4));
                   add(new Skill("Javascript",3));
               }},
               "روی سنگ قبرم بنویسید: خدا بیامورز می خواست خیلی کارا بکنه ولی پول نداشت."
       ));
        add(new User(
                "3",
                "حسین",
                "شریفی",
                "برنامه نویس وب",
                "",
                new ArrayList <Skill>(){{
                    add(new Skill("HTML",5));
                    add(new Skill("Node.js",4));
                    add(new Skill("CSS",2));
                    add(new Skill("React",3));
                }},
                "روی سنگ قبرم بنویسید: خدا بیامورز می خواست خیلی کارا بکنه ولی پول نداشت."
        ));
        add(new User(
                "4",
                "رضا",
                "شرفی",
                "برنامه نویس",
                "",
                new ArrayList <Skill>(){{
                    add(new Skill("HTML",5));
                    add(new Skill("Javascript",4));
                    add(new Skill("Node.js",4));
                    add(new Skill("CSS",2));
                    add(new Skill("React",3));
                    add(new Skill("C++",2));
                    add(new Skill("Java",3));
                    add(new Skill("Python", 5));
                    add(new Skill("Android", 5));
                    add(new Skill("C", 5));
                    add(new Skill("Django", 5));
                    add(new Skill("SQL", 5));
                    add(new Skill("PHP", 5));
                    add(new Skill("MySQL", 5));
                    add(new Skill("Photoshop", 5));
                }},
                "روی سنگ قبرم بنویسید: خدا بیامورز می خواست خیلی کارا بکنه ولی پول نداشت."
        ));
    }};

}
