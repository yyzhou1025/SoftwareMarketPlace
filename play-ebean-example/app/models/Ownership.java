package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Ownership extends Model {

    @Id
    public Long id;

    @ManyToOne
    public User user;

    @ManyToOne
    public Software software;

    public static Find<Long, Ownership> find = new Find<Long, Ownership>() {};

    public static List<Ownership> getSoftware (long userId) {
        return Ownership.find.where().eq("user_user_id", userId).findList();
    }

    public static List<Ownership> getUser (long softwareId) {
        return Ownership.find.where().eq("software_id", softwareId).findList();
    }
}
