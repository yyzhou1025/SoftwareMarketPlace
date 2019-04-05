package models;

import java.util.*;
import javax.persistence.*;

import play.data.format.Formats;
import play.db.ebean.*;
import play.data.validation.*;

@Entity
public class Login extends com.avaje.ebean.Model {

    @Id
    public Long id;

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String password;
}
