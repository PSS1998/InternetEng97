package model;

import java.util.ArrayList;
import java.util.List;

public class Data {
    public static List<Project> projects;
    public static List<Skill>skills;
    public static List<Skill> alisSkills = new ArrayList <Skill>(){{
        add(new Skill("HTML",5));
        add(new Skill("Javascript",4));
        add(new Skill("C++",2));
        add(new Skill("Java",3));
    }};



    public static User user = new User(
            "1",
          "علی",
            "شریف زاده",
            "برنامه نویس وب",
            "",
            alisSkills,
            "روی سنگ قبرم بنویسید: خدا بیامورز می خواست خیلی کارا بکنه ولی نتونست."

    );
}
