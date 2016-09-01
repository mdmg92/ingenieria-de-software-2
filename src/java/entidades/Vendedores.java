/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "vendedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedores.findAll", query = "SELECT v FROM Vendedores v"),
    @NamedQuery(name = "Vendedores.findByCodigoVendedor", query = "SELECT v FROM Vendedores v WHERE v.codigoVendedor = :codigoVendedor"),
    @NamedQuery(name = "Vendedores.findByNombreVendedor", query = "SELECT v FROM Vendedores v WHERE v.nombreVendedor = :nombreVendedor"),
    @NamedQuery(name = "Vendedores.findByApellidoVendedor", query = "SELECT v FROM Vendedores v WHERE v.apellidoVendedor = :apellidoVendedor"),
    @NamedQuery(name = "Vendedores.findByFechaIngreso", query = "SELECT v FROM Vendedores v WHERE v.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Vendedores.findBySueldoBasico", query = "SELECT v FROM Vendedores v WHERE v.sueldoBasico = :sueldoBasico"),
    @NamedQuery(name = "Vendedores.findByTipoVendedor", query = "SELECT v FROM Vendedores v WHERE v.tipoVendedor = :tipoVendedor")})
public class Vendedores implements Serializable {

    @OneToMany(mappedBy = "codigoVendedor")
    private List<Facturas> facturasList;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private Usuarios userId;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo_vendedor")
    private Integer codigoVendedor;
    @Size(max = 2147483647)
    @Column(name = "nombre_vendedor")
    private String nombreVendedor;
    @Size(max = 2147483647)
    @Column(name = "apellido_vendedor")
    private String apellidoVendedor;
    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "sueldo_basico")
    private Integer sueldoBasico;
    @Size(max = 2147483647)
    @Column(name = "tipo_vendedor")
    private String tipoVendedor;
    @OneToMany(mappedBy = "codigoVendedor")
    private Collection<Distritos> distritosCollection;

    public Vendedores() {
    }

    public Vendedores(Integer codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public Integer getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(Integer codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public void setNombreVendedor(String nombreVendedor) {
        this.nombreVendedor = nombreVendedor;
    }

    public String getApellidoVendedor() {
        return apellidoVendedor;
    }

    public void setApellidoVendedor(String apellidoVendedor) {
        this.apellidoVendedor = apellidoVendedor;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(Integer sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public String getTipoVendedor() {
        return tipoVendedor;
    }

    public void setTipoVendedor(String tipoVendedor) {
        this.tipoVendedor = tipoVendedor;
    }

    @XmlTransient
    public Collection<Distritos> getDistritosCollection() {
        return distritosCollection;
    }

    public void setDistritosCollection(Collection<Distritos> distritosCollection) {
        this.distritosCollection = distritosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoVendedor != null ? codigoVendedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vendedores)) {
            return false;
        }
        Vendedores other = (Vendedores) object;
        if ((this.codigoVendedor == null && other.codigoVendedor != null) || (this.codigoVendedor != null && !this.codigoVendedor.equals(other.codigoVendedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "packbgestionventas.Vendedores[ codigoVendedor=" + codigoVendedor + " ]";
    }

    @XmlTransient
    public List<Facturas> getFacturasList() {
        return facturasList;
    }

    public void setFacturasList(List<Facturas> facturasList) {
        this.facturasList = facturasList;
    }

    public Usuarios getUserId() {
        return userId;
    }

    public void setUserId(Usuarios userId) {
        this.userId = userId;
    }
    
}
