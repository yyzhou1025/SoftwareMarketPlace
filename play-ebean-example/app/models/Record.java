package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import com.avaje.ebean.*;

@Entity
public class Record extends Model {

    @Id
    public Long recordId;

    @Constraints.Required
    public String softwareIds;

    @Constraints.Required
    public Long customerId;

    @Constraints.Required
    public String deliveryMethod;

    @Constraints.Required
    public String packageType;

    @Constraints.Required
    public float totalPrice;

    public static Find<Long, Record> find = new Find<Long, Record>() {};

    public static List<Record> getRecord(long userId) {
        return Record.find.where().eq("customer_id", userId).findList();
    }
}
