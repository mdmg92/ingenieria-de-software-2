/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "productos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Productos.findAll", query = "SELECT p FROM Productos p"),
    @NamedQuery(name = "Productos.findByIdProducto", query = "SELECT p FROM Productos p WHERE p.idProducto = :idProducto"),
    @NamedQuery(name = "Productos.findByDescripcionProducto", query = "SELECT p FROM Productos p WHERE p.descripcionProducto = :descripcionProducto"),
    @NamedQuery(name = "Productos.findByPrecioActual", query = "SELECT p FROM Productos p WHERE p.precioActual = :precioActual"),
    @NamedQuery(name = "Productos.findByUnidadMedida", query = "SELECT p FROM Productos p WHERE p.unidadMedida = :unidadMedida"),
    @NamedQuery(name = "Productos.findByStockActual", query = "SELECT p FROM Productos p WHERE p.stockActual = :stockActual"),
    @NamedQuery(name = "Productos.findByStockMinimo", query = "SELECT p FROM Productos p WHERE p.stockMinimo = :stockMinimo")})
public class Productos implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos")
    private List<DetallesPedidos> detallesPedidosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productos")
    private List<DetallesFacturas> detallesFacturasList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_producto")
    private Integer idProducto;
    @Size(max = 2147483647)
    @Column(name = "descripcion_producto")
    private String descripcionProducto;
    @Column(name = "precio_actual")
    private Integer precioActual;
    @Size(max = 2147483647)
    @Column(name = "unidad_medida")
    private String unidadMedida;
    @Column(name = "stock_actual")
    private Integer stockActual;
    @Column(name = "stock_minimo")
    private Integer stockMinimo;

    public Productos() {
    }

    public Productos(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Integer getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(Integer precioActual) {
        this.precioActual = precioActual;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProducto != null ? idProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Productos)) {
            return false;
        }
        Productos other = (Productos) object;
        if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && !this.idProducto.equals(other.idProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "entidades.Productos[ idProducto=" + idProducto + " ]";
        return String.format("%s[idProducto=%d]", getClass().getSimpleName(), getIdProducto());
    }

    @XmlTransient
    public List<DetallesPedidos> getDetallesPedidosList() {
        return detallesPedidosList;
    }

    public void setDetallesPedidosList(List<DetallesPedidos> detallesPedidosList) {
        this.detallesPedidosList = detallesPedidosList;
    }

    @XmlTransient
    public List<DetallesFacturas> getDetallesFacturasList() {
        return detallesFacturasList;
    }

    public void setDetallesFacturasList(List<DetallesFacturas> detallesFacturasList) {
        this.detallesFacturasList = detallesFacturasList;
    }
    
}
