/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "distritos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distritos.findAll", query = "SELECT d FROM Distritos d"),
    @NamedQuery(name = "Distritos.findByIdDistrito", query = "SELECT d FROM Distritos d WHERE d.idDistrito = :idDistrito"),
    @NamedQuery(name = "Distritos.findByNombreDistrito", query = "SELECT d FROM Distritos d WHERE d.nombreDistrito = :nombreDistrito")})
public class Distritos implements Serializable {

    @OneToMany(mappedBy = "codigoDistrito")
    private List<Proveedores> proveedoresList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_distrito")
    private Integer idDistrito;
    @Size(max = 2147483647)
    @Column(name = "nombre_distrito")
    private String nombreDistrito;
    @JoinColumn(name = "codigo_vendedor", referencedColumnName = "codigo_vendedor")
    @ManyToOne
    private Vendedores codigoVendedor;
    @OneToMany(mappedBy = "codigoDistrito")
    private Collection<Clientes> clientesCollection;

    public Distritos() {
    }

    public Distritos(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public Vendedores getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(Vendedores codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    @XmlTransient
    public Collection<Clientes> getClientesCollection() {
        return clientesCollection;
    }

    public void setClientesCollection(Collection<Clientes> clientesCollection) {
        this.clientesCollection = clientesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistrito != null ? idDistrito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distritos)) {
            return false;
        }
        Distritos other = (Distritos) object;
        if ((this.idDistrito == null && other.idDistrito != null) || (this.idDistrito != null && !this.idDistrito.equals(other.idDistrito))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.Distritos[ idDistrito=" + idDistrito + " ]";
    }

    @XmlTransient
    public List<Proveedores> getProveedoresList() {
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }
    
}
