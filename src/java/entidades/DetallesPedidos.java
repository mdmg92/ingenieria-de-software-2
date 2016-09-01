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
@Table(name = "detalles_pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesPedidos.findAll", query = "SELECT d FROM DetallesPedidos d"),
    @NamedQuery(name = "DetallesPedidos.findByIdPedido", query = "SELECT d FROM DetallesPedidos d WHERE d.detallesPedidosPK.idPedido = :idPedido"),
    @NamedQuery(name = "DetallesPedidos.findByIdProducto", query = "SELECT d FROM DetallesPedidos d WHERE d.detallesPedidosPK.idProducto = :idProducto"),
    @NamedQuery(name = "DetallesPedidos.findByCantidadPedido", query = "SELECT d FROM DetallesPedidos d WHERE d.cantidadPedido = :cantidadPedido")})
public class DetallesPedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetallesPedidosPK detallesPedidosPK;
    @Column(name = "cantidad_pedido")
    private Integer cantidadPedido;
    @JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pedidos pedidos;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Productos productos;

    public DetallesPedidos() {
    }

    public DetallesPedidos(DetallesPedidosPK detallesPedidosPK) {
        this.detallesPedidosPK = detallesPedidosPK;
    }

    public DetallesPedidos(int idPedido, int idProducto) {
        this.detallesPedidosPK = new DetallesPedidosPK(idPedido, idProducto);
    }

    public DetallesPedidosPK getDetallesPedidosPK() {
        return detallesPedidosPK;
    }

    public void setDetallesPedidosPK(DetallesPedidosPK detallesPedidosPK) {
        this.detallesPedidosPK = detallesPedidosPK;
    }

    public Integer getCantidadPedido() {
        return cantidadPedido;
    }

    public void setCantidadPedido(Integer cantidadPedido) {
        this.cantidadPedido = cantidadPedido;
    }

    public Pedidos getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedidos pedidos) {
        this.pedidos = pedidos;
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
        hash += (detallesPedidosPK != null ? detallesPedidosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesPedidos)) {
            return false;
        }
        DetallesPedidos other = (DetallesPedidos) object;
        if ((this.detallesPedidosPK == null && other.detallesPedidosPK != null) || (this.detallesPedidosPK != null && !this.detallesPedidosPK.equals(other.detallesPedidosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.DetallesPedidos[ detallesPedidosPK=" + detallesPedidosPK + " ]";
    }
    
}
