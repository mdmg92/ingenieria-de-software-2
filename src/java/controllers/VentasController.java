package controllers;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import entidades.Clientes;
import entidades.Facturas;
import entidades.Vendedores;
import entidades.ClientesFacade;
import entidades.VendedoresFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import entidades.Productos;
import entidades.ProductosFacade;
import javax.enterprise.context.SessionScoped;
import entidades.DetallesFacturas;
import entidades.DetallesFacturasFacade;
import entidades.FacturasFacadeLocal;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Fernando
 */
@Named(value = "ventasController")
@SessionScoped
public class VentasController implements Serializable {

    private Facturas factura = new Facturas();
    private Clientes cliente = new Clientes();
    private Vendedores vendedor = new Vendedores();
    private Productos producto = new Productos();
    private DetallesFacturas detalle = new DetallesFacturas();

    private List<Clientes> clientelista = new ArrayList<Clientes>();
    private List<Vendedores> vendedorlista = new ArrayList<Vendedores>();
    private List<Productos> productosLista = new ArrayList<Productos>();
    private List<DetallesFacturas> detalleslista = new ArrayList<DetallesFacturas>();

    private int cantidad;
    private String nroVenta;
    private String fechaVenta;
    private Connection con = null;
    private int ventaTotal = 0;
    private int ventaCantidad = 0;

    @EJB
    private VendedoresFacade vendedorEJB = new VendedoresFacade();
    @EJB
    private ClientesFacade clienteEJB = new ClientesFacade();
    @EJB
    private ProductosFacade productoEJB = new ProductosFacade();
    @EJB
    private DetallesFacturasFacade detalleEJB = new DetallesFacturasFacade();
    @EJB
    private FacturasFacadeLocal facturasEJB;

    @PostConstruct
    void initialiseSession() {
        con = DataConnect.getConnection();
        this.cargarVista();
    }

    public void cargarVista() {
        int nuevaSeq = obtenerNroFactura() + 1;
        if (nuevaSeq < 10) {
            this.nroVenta = "000" + String.valueOf(nuevaSeq);
        } else if (nuevaSeq < 100) {
            this.nroVenta = "00" + String.valueOf(nuevaSeq);
        } else {
            this.nroVenta = String.valueOf(nuevaSeq);
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        fechaVenta = formatter.format(date);
        this.clientelista = clienteEJB.findAll();
        this.vendedorlista = vendedorEJB.findAll();
        this.productosLista = productoEJB.findAll();
    }

    /**
     * Creates a new instance of VentasController
     */
    public VentasController() {

    }

    public void agregarProducto() {
        DetallesFacturas det = new DetallesFacturas();
        int precio = this.producto.getPrecioActual();
        det.setProductos(this.producto);
        det.setCantidadVendida(cantidad);
        det.setPrecioVenta(precio);

        if (!detalleslista.isEmpty()) {
            for (DetallesFacturas detalle : detalleslista) {
                if (detalle.getProductos() == det.getProductos()) {
                    det.setCantidadVendida(det.getCantidadVendida() + detalle.getCantidadVendida());
                    det.setPrecioVenta(det.getPrecioVenta() + detalle.getPrecioVenta());
                    detalleslista.remove(detalle);
                    this.ventaTotal -= (detalle.getCantidadVendida() * detalle.getPrecioVenta());
                    this.ventaCantidad -= detalle.getCantidadVendida();
                    break;
                }
            }
        }

        this.detalleslista.add(det);
        this.ventaTotal += (det.getCantidadVendida() * det.getPrecioVenta());
        this.ventaCantidad += det.getCantidadVendida();
        det = new DetallesFacturas();
    }

    public void quitarProducto(DetallesFacturas det) {
        this.detalleslista.remove(det);
    }

    public void guardarVenta() {
        if (!validarVenta()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Venta incorrecta", " Parametros incorrectos!"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return;
        }
        this.factura.setNumeroFactura(this.nroVenta);
        this.factura.setCodigoCliente(this.cliente);
        this.factura.setCodigoVendedor(this.vendedor);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = formatter.parse(this.fechaVenta);
            this.factura.setFechaFacturacion(date);
            this.factura.setFechaEstado(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        this.factura.setEstado("Pendiente");
        this.factura.setPorcentajeIva(5);
        facturasEJB.create(this.factura);
        int idFactura = obtenerNroFactura();

        for (DetallesFacturas det : detalleslista) {
            try {
                PreparedStatement insertDetalle
                        = con.prepareStatement("INSERT INTO detalles_facturas "
                                + "(cantidad_vendida, precio_venta, id_factura, "
                                + "codigo_producto) VALUES (?, ?, ?, ?)");

                insertDetalle.setInt(1, det.getCantidadVendida());
                insertDetalle.setInt(2, det.getPrecioVenta());
                insertDetalle.setInt(3, idFactura);
                insertDetalle.setInt(4, det.getProductos().getIdProducto());
                insertDetalle.executeUpdate();
                insertDetalle.close();
            } catch (SQLException ex) {
                System.out.println("Error al obtener secuencia -->" + ex.getMessage());
            }
        }

        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Venta guardada", "La venta ha sido guardada!"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public void cancelarVenta() {
        if (factura != null) {
            facturasEJB.remove(factura);
            this.cliente = new Clientes();
            this.vendedor = new Vendedores();
            this.ventaCantidad = 0;
            this.ventaTotal = 0;
        }
        if (!detalleslista.isEmpty() && detalleslista != null) {
            for (DetallesFacturas detalleFactura : detalleslista) {
                detalleEJB.remove(detalleFactura);
                this.detalleslista = new ArrayList<DetallesFacturas>();
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Venta cancelada", " La venta ha sido cancelada!"));
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    public int obtenerNroFactura() {
        int ultimoValor = 0;
        try {
            PreparedStatement ps
                    = con.prepareStatement("SELECT last_value FROM facturas_id_factura_seq");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal uv = rs.getBigDecimal("last_value");
                ultimoValor = uv.toBigInteger().intValue();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener secuencia -->" + ex.getMessage());
        }
        return ultimoValor;
    }

    public void eliminarDetalle(DetallesFacturas det) {
        this.detalleslista.remove(det);
        this.ventaTotal -= (det.getCantidadVendida() * det.getPrecioVenta());
        this.ventaCantidad -= det.getCantidadVendida();
    }

    public boolean validarVenta() {
        if (this.cliente != null && this.vendedor != null && !this.detalleslista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public int getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(int ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    public int getVentaCantidad() {
        return ventaCantidad;
    }

    public void setVentaCantidad(int ventaCantidad) {
        this.ventaCantidad = ventaCantidad;
    }

    public FacturasFacadeLocal getFacturasEJB() {
        return facturasEJB;
    }

    public void setFacturasEJB(FacturasFacadeLocal facturasEJB) {
        this.facturasEJB = facturasEJB;
    }

    public DetallesFacturas getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallesFacturas detalle) {
        this.detalle = detalle;
    }

    public List<DetallesFacturas> getDetalleslista() {
        return detalleslista;
    }

    public void setDetalleslista(List<DetallesFacturas> detalleslista) {
        this.detalleslista = detalleslista;
    }

    public DetallesFacturasFacade getDetalleEJB() {
        return detalleEJB;
    }

    public void setDetalleEJB(DetallesFacturasFacade detalleEJB) {
        this.detalleEJB = detalleEJB;
    }

    public Facturas getFactura() {
        return factura;
    }

    public void setFactura(Facturas factura) {
        this.factura = factura;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Vendedores getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedores vendedor) {
        this.vendedor = vendedor;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public List<Clientes> getClientelista() {
        return clientelista;
    }

    public void setClientelista(List<Clientes> clientelista) {
        this.clientelista = clientelista;
    }

    public List<Vendedores> getVendedorlista() {
        return vendedorlista;
    }

    public void setVendedorlista(List<Vendedores> vendedorlista) {
        this.vendedorlista = vendedorlista;
    }

    public List<Productos> getProductosLista() {
        return productosLista;
    }

    public void setProductosLista(List<Productos> productosLista) {
        this.productosLista = productosLista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(String nroVenta) {
        this.nroVenta = nroVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public VendedoresFacade getVendedorEJB() {
        return vendedorEJB;
    }

    public void setVendedorEJB(VendedoresFacade vendedorEJB) {
        this.vendedorEJB = vendedorEJB;
    }

    public ClientesFacade getClienteEJB() {
        return clienteEJB;
    }

    public void setClienteEJB(ClientesFacade clienteEJB) {
        this.clienteEJB = clienteEJB;
    }

    public ProductosFacade getProductoEJB() {
        return productoEJB;
    }

    public void setProductoEJB(ProductosFacade productoEJB) {
        this.productoEJB = productoEJB;
    }

}
