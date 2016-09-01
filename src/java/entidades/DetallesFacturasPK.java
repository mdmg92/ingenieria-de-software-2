/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Informatica
 */
@Embeddable
public class DetallesFacturasPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_factura")
    private int idFactura;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigo_producto")
    private int codigoProducto;

    public DetallesFacturasPK() {
    }

    public DetallesFacturasPK(int idFactura, int codigoProducto) {
        this.idFactura = idFactura;
        this.codigoProducto = codigoProducto;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idFactura;
        hash += (int) codigoProducto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesFacturasPK)) {
            return false;
        }
        DetallesFacturasPK other = (DetallesFacturasPK) object;
        if (this.idFactura != other.idFactura) {
            return false;
        }
        if (this.codigoProducto != other.codigoProducto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.DetallesFacturasPK[ idFactura=" + idFactura + ", codigoProducto=" + codigoProducto + " ]";
    }
    
}
