package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ContatoDao {

    private static Map<Long, Contato> contatos = new ConcurrentHashMap<Long, Contato>();

    private static AtomicLong uuid = new AtomicLong();

    public static Contato findById(long id) {
        return contatos.get(id);
    }

    public static List<Contato> findByName(String filter){
        List<Contato> result = new ArrayList<Contato>();
        for (Contato contato: contatos.values()){
            if (contato.nome.toLowerCase().toLowerCase().contains(filter.toLowerCase())){
                result.add(contato);
            }
        }
        return result;
    }

    public static List<Contato> findAll(){
        List<Contato> result = new ArrayList<Contato>();
        for (Contato contato: contatos.values()){
            result.add(contato);

        }
        return result;
        //return new ArrayList<Contato>(contatos.values());
    }
    public static void save(Contato contato) {
        if (contato.id == null) {
            contato.id = uuid.incrementAndGet();
        }
        contatos.put(contato.id, contato);
    }

    public static void delete(long id) {

        contatos.remove(id);
    }

}
