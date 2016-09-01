package entidades;

import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "clientesController")
@ViewScoped
public class ClientesController extends AbstractController<Clientes> {

    @Inject
    private DistritosController codigoDistritoController;

    public ClientesController() {
        // Inform the Abstract parent controller of the concrete Clientes Entity
        super(Clientes.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoDistritoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Distritos controller in order to
     * display its data in its View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoDistrito(ActionEvent event) {
        if (this.getSelected() != null && codigoDistritoController.getSelected() == null) {
            codigoDistritoController.setSelected(this.getSelected().getCodigoDistrito());
        }
    }
}
