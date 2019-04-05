package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.PagedList;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class User extends com.avaje.ebean.Model {
    @Id
    public Long userId;

    @Constraints.Required
    public String userName;

    @Constraints.Required
    public String password;

    @Constraints.Required
    public String type;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Software> softwares;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Ownership> owns;

    public static Find<Long, User> find = new Find<Long, User>() {};

    public static Map<String, String> users() {
        LinkedHashMap<String, String> res = new LinkedHashMap<>();
        for (User u: User.find.orderBy("user_name").findList()) {
            res.put(u.userId.toString(), u.userName);
        }
        return res;
    }

    public static String authenticate(String userName, String password) {
        List<User> list = User.find.where().eq("user_name", userName).findList();
        if (User.find.where().eq("user_name", userName).findList().size() > 0) {
            String refPassword = list.get(0).password;
            if (password.equals(refPassword)) {
                return "Pass authentication";
            }
        }
        return null;
    }

    public static String checkRegister(String userName) {
        List<User> list = User.find.where().eq("user_name", userName).findList();
        if (list.size() == 0) {
            return "Can register";
        } else {
            return null;
        }
    }

    public static List<Software> getOwnedSoftware(String username) {
        List<User> l = User.find.where().eq("user_name", username).findList();
        User u = l.get(0);
        List<Software> res = new LinkedList<>();
        for (Ownership o : u.owns) {
            res.add(o.software);
        }
        return res;
    }

}
