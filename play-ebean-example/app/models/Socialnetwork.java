package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Socialnetwork extends Model {

    @Id
    public Long countId;

    @Constraints.Required
    public Long softwareId;

    @Constraints.Required
    public Long userId;

    @Constraints.Required
    public String comment;

    public static Find<Long, Socialnetwork> find = new Find<Long, Socialnetwork>() {};

    public static List<Socialnetwork> getUserCommets(long id) {
        return Socialnetwork.find.where().eq("user_id", id).findList();
    }

    public static List<Socialnetwork> getCircleComments(long id) {
        return Socialnetwork.find.where().eq("software_id", id).findList();
    }
}
