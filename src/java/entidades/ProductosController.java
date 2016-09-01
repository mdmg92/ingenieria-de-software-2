package entidades;

import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "productosController")
@ViewScoped
public class ProductosController extends AbstractController<Productos> {

    public ProductosController() {
        // Inform the Abstract parent controller of the concrete Productos Entity
        super(Productos.class);
    }

}
