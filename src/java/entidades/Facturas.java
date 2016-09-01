/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Informatica
 */
@Entity
@Table(name = "facturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturas.findAll", query = "SELECT f FROM Facturas f"),
    @NamedQuery(name = "Facturas.findByIdFactura", query = "SELECT f FROM Facturas f WHERE f.idFactura = :idFactura"),
    @NamedQuery(name = "Facturas.findByNumeroFactura", query = "SELECT f FROM Facturas f WHERE f.numeroFactura = :numeroFactura"),
    @NamedQuery(name = "Facturas.findByFechaFacturacion", query = "SELECT f FROM Facturas f WHERE f.fechaFacturacion = :fechaFacturacion"),
    @NamedQuery(name = "Facturas.findByEstado", query = "SELECT f FROM Facturas f WHERE f.estado = :estado"),
    @NamedQuery(name = "Facturas.findByPorcentajeIva", query = "SELECT f FROM Facturas f WHERE f.porcentajeIva = :porcentajeIva"),
    @NamedQuery(name = "Facturas.findByFechaEstado", query = "SELECT f FROM Facturas f WHERE f.fechaEstado = :fechaEstado")})
public class Facturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura")
    private Integer idFactura;
    @Size(max = 2147483647)
    @Column(name = "numero_factura")
    private String numeroFactura;
    @Column(name = "fecha_facturacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFacturacion;
    @Size(max = 2147483647)
    @Column(name = "estado")
    private String estado;
    @Column(name = "porcentaje_iva")
    private Integer porcentajeIva;
    @Column(name = "fecha_estado")
    @Temporal(TemporalType.DATE)
    private Date fechaEstado;
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo_cliente")
    @ManyToOne
    private Clientes codigoCliente;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
    @ManyToOne
    private Pedidos idPedido;
    @JoinColumn(name = "codigo_vendedor", referencedColumnName = "codigo_vendedor")
    @ManyToOne
    private Vendedores codigoVendedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facturas")
    private List<DetallesFacturas> detallesFacturasList;

    public Facturas() {
    }

    public Facturas(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Date getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(Date fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(Integer porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public Date getFechaEstado() {
        return fechaEstado;
    }

    public void setFechaEstado(Date fechaEstado) {
        this.fechaEstado = fechaEstado;
    }

    public Clientes getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Clientes codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Pedidos getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Pedidos idPedido) {
        this.idPedido = idPedido;
    }

    public Vendedores getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(Vendedores codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    @XmlTransient
    public List<DetallesFacturas> getDetallesFacturasList() {
        return detallesFacturasList;
    }

    public void setDetallesFacturasList(List<DetallesFacturas> detallesFacturasList) {
        this.detallesFacturasList = detallesFacturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFactura != null ? idFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturas)) {
            return false;
        }
        Facturas other = (Facturas) object;
        if ((this.idFactura == null && other.idFactura != null) || (this.idFactura != null && !this.idFactura.equals(other.idFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.Facturas[ idFactura=" + idFactura + " ]";
    }
    
}
