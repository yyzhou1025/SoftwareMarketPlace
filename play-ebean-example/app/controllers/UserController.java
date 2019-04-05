package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;
import java.util.List;

public class UserController extends Controller {

    public Result getItems(String username) {
        List<Software> res = User.getOwnedSoftware(username);
        return ok(views.html.customer.render(res));
    }
}
