/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Informatica
 */
@Entity
@Table(name = "detalles_facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesFacturas.findAll", query = "SELECT d FROM DetallesFacturas d"),
    @NamedQuery(name = "DetallesFacturas.findByIdFactura", query = "SELECT d FROM DetallesFacturas d WHERE d.detallesFacturasPK.idFactura = :idFactura"),
    @NamedQuery(name = "DetallesFacturas.findByCodigoProducto", query = "SELECT d FROM DetallesFacturas d WHERE d.detallesFacturasPK.codigoProducto = :codigoProducto"),
    @NamedQuery(name = "DetallesFacturas.findByCantidadVendida", query = "SELECT d FROM DetallesFacturas d WHERE d.cantidadVendida = :cantidadVendida"),
    @NamedQuery(name = "DetallesFacturas.findByPrecioVenta", query = "SELECT d FROM DetallesFacturas d WHERE d.precioVenta = :precioVenta")})
public class DetallesFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesFacturasPK detallesFacturasPK;
    @Column(name = "cantidad_vendida")
    private Integer cantidadVendida;
    @Column(name = "precio_venta")
    private Integer precioVenta;
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Facturas facturas;
    @JoinColumn(name = "codigo_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;

    public DetallesFacturas() {
    }

    public DetallesFacturas(DetallesFacturasPK detallesFacturasPK) {
        this.detallesFacturasPK = detallesFacturasPK;
    }

    public DetallesFacturas(int idFactura, int codigoProducto) {
        this.detallesFacturasPK = new DetallesFacturasPK(idFactura, codigoProducto);
    }

    public DetallesFacturasPK getDetallesFacturasPK() {
        return detallesFacturasPK;
    }

    public void setDetallesFacturasPK(DetallesFacturasPK detallesFacturasPK) {
        this.detallesFacturasPK = detallesFacturasPK;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Integer getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Integer precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Facturas getFacturas() {
        return facturas;
    }

    public void setFacturas(Facturas facturas) {
        this.facturas = facturas;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detallesFacturasPK != null ? detallesFacturasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesFacturas)) {
            return false;
        }
        DetallesFacturas other = (DetallesFacturas) object;
        if ((this.detallesFacturasPK == null && other.detallesFacturasPK != null) || (this.detallesFacturasPK != null && !this.detallesFacturasPK.equals(other.detallesFacturasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.DetallesFacturas[ detallesFacturasPK=" + detallesFacturasPK + " ]";
    }
    
}
