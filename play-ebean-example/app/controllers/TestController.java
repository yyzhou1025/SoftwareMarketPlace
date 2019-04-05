package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;
import models.*;

import javax.inject.Inject;
import javax.persistence.PersistenceException;

public class TestController extends Controller {

    private FormFactory formFactory;

    @Inject
    public TestController(FormFactory formFactory) {
        this.formFactory = formFactory;
    }

    public Result GO_HOME = Results.redirect(
            routes.TestController.list(0, "software_name", "asc", "")
    );

    @Security.Authenticated(Secured.class)
    public Result index() {
        return GO_HOME;
    }

    @Security.Authenticated(Secured.class)
    public Result addSoftware() {
        Form<Software> softwareForm = formFactory.form(Software.class);
        return ok(views.html.addSoftware.render(
                User.find.where().eq("user_name", session().get("username")).findList().get(0),
                softwareForm));
    }

    @Security.Authenticated(Secured.class)
    public Result save() {
        Form<Software> softwareForm = formFactory.form(Software.class).bindFromRequest();
        if (softwareForm.hasErrors()) {
            return badRequest(views.html.addSoftware.render(
                    User.find.where().eq("user_name", session().get("username")).findList().get(0),
                    softwareForm));
        }
        softwareForm.get().save();
        return index();
    }

    @Security.Authenticated(Secured.class)
    public Result list(int page, String sortBy, String order, String filter) {
        return ok(views.html.test.render(
                User.find.where().eq("user_name", session().get("username")).findList().get(0),
                Software.page(page, 10, sortBy, order, filter),
                sortBy, order, filter
         ));
    }
    
    
    public Result softwareDetail(String softwareName, String description, Float price) {
    	return ok(softwareDetail.render(softwareName, description, price));
    }
    
     
    
    
    
    
 
   
    
}
