package entidades;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "vendedoresController")
@ViewScoped
public class VendedoresController extends AbstractController<Vendedores> {

    public VendedoresController() {
        // Inform the Abstract parent controller of the concrete Vendedores Entity
        super(Vendedores.class);
    }

    /**
     * Sets the "items" attribute with a collection of Distritos entities that
     * are retrieved from Vendedores?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Distritos page
     */
    public String navigateDistritosCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Distritos_items", this.getSelected().getDistritosCollection());
        }
        return "/entidades/distritos/index";
    }

}
