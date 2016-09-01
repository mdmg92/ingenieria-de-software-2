/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Informatica
 */
@Entity
@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
    @NamedQuery(name = "Proveedores.findByIdProveedor", query = "SELECT p FROM Proveedores p WHERE p.idProveedor = :idProveedor"),
    @NamedQuery(name = "Proveedores.findByRazonSocialProveedor", query = "SELECT p FROM Proveedores p WHERE p.razonSocialProveedor = :razonSocialProveedor"),
    @NamedQuery(name = "Proveedores.findByDireccionProveedor", query = "SELECT p FROM Proveedores p WHERE p.direccionProveedor = :direccionProveedor"),
    @NamedQuery(name = "Proveedores.findByTelefonoProveedor", query = "SELECT p FROM Proveedores p WHERE p.telefonoProveedor = :telefonoProveedor"),
    @NamedQuery(name = "Proveedores.findByEmailProveedor", query = "SELECT p FROM Proveedores p WHERE p.emailProveedor = :emailProveedor"),
    @NamedQuery(name = "Proveedores.findByRucProveedor", query = "SELECT p FROM Proveedores p WHERE p.rucProveedor = :rucProveedor")})
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_proveedor")
    private Integer idProveedor;
    @Size(max = 2147483647)
    @Column(name = "razon_social_proveedor")
    private String razonSocialProveedor;
    @Size(max = 2147483647)
    @Column(name = "direccion_proveedor")
    private String direccionProveedor;
    @Size(max = 2147483647)
    @Column(name = "telefono_proveedor")
    private String telefonoProveedor;
    @Size(max = 2147483647)
    @Column(name = "email_proveedor")
    private String emailProveedor;
    @Size(max = 2147483647)
    @Column(name = "ruc_proveedor")
    private String rucProveedor;
    @JoinColumn(name = "codigo_distrito", referencedColumnName = "id_distrito")
    @ManyToOne
    private Distritos codigoDistrito;

    public Proveedores() {
    }

    public Proveedores(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getEmailProveedor() {
        return emailProveedor;
    }

    public void setEmailProveedor(String emailProveedor) {
        this.emailProveedor = emailProveedor;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public Distritos getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(Distritos codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProveedor != null ? idProveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProveedor == null && other.idProveedor != null) || (this.idProveedor != null && !this.idProveedor.equals(other.idProveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.Proveedores[ idProveedor=" + idProveedor + " ]";
    }
    
}
