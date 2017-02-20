package models;

import play.data.validation.Constraints;

/**
 * Created by Daniel on 17/02/2017.
 */
public class Contato {
    public Long id;

    @Constraints.Required
    public String nome;
    @Constraints.Required
    public String telefone;


}
