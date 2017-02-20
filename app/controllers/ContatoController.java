package controllers;

import models.Contato;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 17/02/2017.
 */
public class ContatoController extends Controller {


    private final Form<Contato> formulario;

    @Inject
    public ContatoController(FormFactory formFactory){
        this.formulario = formFactory.form(Contato.class);
    }

    public Result novo(){
        Form<Contato> form = formulario.fill(new Contato());
        return ok(views.html.novocontato.render("Novo Contato", form));
    }

    public Result salva(){
        Form<Contato> form = formulario.bindFromRequest();
        if (form.hasErrors()){
            flash("erro", "Preencha o nome e o telefone.");
            return badRequest(views.html.novocontato.render("Novo Contato", form));
        } else {
            models.ContatoDao.save(form.get());
            List<Contato> contatos = models.ContatoDao.findAll();
            //return ok(views.html.lista.render("Lista de Contatos", contatos));
            flash("sucesso", "Contato adicionado com sucesso!");
            return redirect(routes.ContatoController.lista(""));
        }
    }
    public Result lista(String filtro){
        List<Contato> contatos = models.ContatoDao.findByName(filtro);
        return ok(views.html.lista.render("Lista de Contatos", contatos));
    }


}
