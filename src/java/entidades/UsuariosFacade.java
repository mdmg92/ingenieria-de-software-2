/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Informatica
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> implements UsuariosFacadeLocal {

    @PersistenceContext(unitName = "appGestionVentasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    @Override
    public Usuarios iniciarSesion(Usuarios us) {
        Usuarios usuario = null;
        String consulta;
        try{
            consulta = "FROM Usuarios u "
                    + "WHERE u.username = ?1 and u.password = ?2";
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsername());
            query.setParameter(2, us.getPassword());
            
            List<Usuarios> user = query.getResultList();
            if (!user.isEmpty()) {
                usuario = user.get(0);
            }
        }catch(Exception e) {
            throw e;
        }
        return usuario;
    }
}
