package models;

import java.util.*;
import javax.persistence.*;

import com.avaje.ebean.PagedList;
import play.data.format.Formats;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Software extends com.avaje.ebean.Model {

    @Id
    public Long softwareId;

    @Constraints.Required
    public String softwareName;

    @Formats.NonEmpty
    public String description;

    public float price;

    @ManyToOne
    public User user;

    @OneToMany(mappedBy = "software", cascade = CascadeType.ALL)
    public Software software;

    public static Find<Long, Software> find = new Find<Long, Software>() {};

    public static List<Software> softwares() {
        return Software.find.findList();
    }

    public static PagedList<Software> page(int page, int pageSize, String sortBy, String order, String filter) {
        return find.where()
                .ilike("software_name", "%" + filter + "%")
                .orderBy(sortBy + " " + order)
                .fetch("user")
                .findPagedList(page, pageSize);
    }
   
}
