/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
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
@Table(name = "clientes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c"),
    @NamedQuery(name = "Clientes.findByCodigoCliente", query = "SELECT c FROM Clientes c WHERE c.codigoCliente = :codigoCliente"),
    @NamedQuery(name = "Clientes.findByRazonSocialCliente", query = "SELECT c FROM Clientes c WHERE c.razonSocialCliente = :razonSocialCliente"),
    @NamedQuery(name = "Clientes.findByDireccionCliente", query = "SELECT c FROM Clientes c WHERE c.direccionCliente = :direccionCliente"),
    @NamedQuery(name = "Clientes.findByTelefonoCliente", query = "SELECT c FROM Clientes c WHERE c.telefonoCliente = :telefonoCliente"),
    @NamedQuery(name = "Clientes.findByEmailCliente", query = "SELECT c FROM Clientes c WHERE c.emailCliente = :emailCliente"),
    @NamedQuery(name = "Clientes.findByContacto", query = "SELECT c FROM Clientes c WHERE c.contacto = :contacto")})
public class Clientes implements Serializable {

    @OneToMany(mappedBy = "codigoCliente")
    private List<Facturas> facturasList;
    @OneToMany(mappedBy = "codigoCliente")
    private List<Pedidos> pedidosList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_cliente")
    private Integer codigoCliente;
    @Size(max = 2147483647)
    @Column(name = "razon_social_cliente")
    private String razonSocialCliente;
    @Size(max = 2147483647)
    @Column(name = "direccion_cliente")
    private String direccionCliente;
    @Size(max = 2147483647)
    @Column(name = "telefono_cliente")
    private String telefonoCliente;
    @Size(max = 2147483647)
    @Column(name = "email_cliente")
    private String emailCliente;
    @Size(max = 2147483647)
    @Column(name = "contacto")
    private String contacto;
    @JoinColumn(name = "codigo_distrito", referencedColumnName = "id_distrito")
    @ManyToOne
    private Distritos codigoDistrito;

    public Clientes() {
    }

    public Clientes(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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
        hash += (codigoCliente != null ? codigoCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.codigoCliente == null && other.codigoCliente != null) || (this.codigoCliente != null && !this.codigoCliente.equals(other.codigoCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.Clientes[ codigoCliente=" + codigoCliente + " ]";
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }
    
}
