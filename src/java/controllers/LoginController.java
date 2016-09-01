package controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import entidades.Usuarios;
import entidades.UsuariosFacadeLocal;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;

@Named
@SessionScoped
public class LoginController implements Serializable{
    
    @EJB
    private UsuariosFacadeLocal EJBUsuario;
    private Usuarios usuario;
    private boolean login = false;
    
    @PostConstruct
    public void init() {
        usuario = new Usuarios();
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion() {
        Usuarios us;
        String redirect = null;
        try {
            us = EJBUsuario.iniciarSesion(usuario);
            if (us!=null) {
                HttpSession session = SessionBean.getSession();
                session.setAttribute("username", us.getUsername());
                login = true;
                redirect = "/index";    
            }else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas!"));
            }
        }catch(Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error al iniciar sesion."));
        }
        return redirect;           
    }
    
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}
