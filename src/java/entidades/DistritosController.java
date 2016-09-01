package entidades;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "distritosController")
@ViewScoped
public class DistritosController extends AbstractController<Distritos> {

    @Inject
    private VendedoresController codigoVendedorController;

    public DistritosController() {
        // Inform the Abstract parent controller of the concrete Distritos Entity
        super(Distritos.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoVendedorController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Vendedores controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoVendedor(ActionEvent event) {
        if (this.getSelected() != null && codigoVendedorController.getSelected() == null) {
            codigoVendedorController.setSelected(this.getSelected().getCodigoVendedor());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Clientes entities that
     * are retrieved from Distritos?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Clientes page
     */
    public String navigateClientesCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Clientes_items", this.getSelected().getClientesCollection());
        }
        return "/entidades/clientes/index";
    }

}
