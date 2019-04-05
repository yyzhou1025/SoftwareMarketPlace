package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

public class RegisterController extends Controller {

    private FormFactory formFactory;

    @Inject
    public RegisterController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result index() {
        Form<User> userForm = formFactory.form(User.class);
        return ok(views.html.register.render(userForm));
    }

    public Result authenticate() {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();
        if (userForm.hasErrors()) {
            return badRequest(views.html.register.render(userForm));
        }
        String username = userForm.get().userName;
        if (User.checkRegister(username) == null) {
            flash("registerError", "This username has been taken");
            return badRequest(views.html.register.render(userForm));
        } else {
            userForm.get().save();
            return redirect(routes.LoginController.index());
        }
    }
}
