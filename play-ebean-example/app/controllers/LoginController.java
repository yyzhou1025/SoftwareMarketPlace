package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

public class LoginController extends Controller {

    private FormFactory formFactory;

    @Inject
    public LoginController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result index() {
        Form<Login> loginForm = formFactory.form(Login.class);
        return ok(views.html.login.render(loginForm));
    }

    public Result authenticate() {
        Form<Login> loginForm = formFactory.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(views.html.login.render(loginForm));
        }
        String username = loginForm.get().username;
        String password = loginForm.get().password;
        if (User.authenticate(username, password) == null) {
            flash("authenticationError","Invalid username or password");
            return badRequest(views.html.login.render(loginForm));
        } else {
            session().clear();
            session("username", username);
            return redirect(routes.TestController.index());
        }
    }

    public Result logout() {
        session().clear();
        return redirect(routes.LoginController.index());
    }
}
