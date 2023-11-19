package com.malagueta.fintch.tables;

import com.malagueta.fintch.domain_service.value.CreditoSatus;
import jakarta.persistence.*;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@ToString
@Entity
public class Credito implements Serializable {
    //dados de auditoria
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@Column(nullable = false)
    private Date createdDate;
    private Date updateDate;


    //@OneToOne(mappedBy = "user")//
    //@OneToOne(cascade = {CascadeType.ALL}, optional = false)
    @ToString.Exclude
   // @ManyToOne(fetch =FetchType.EAGER ,optional=false)
    private Long createdBy; // este campo no futoro vai conter o Objecto user

    @ToString.Exclude
   // @OneToOne(optional = true)
    private Long aprovadoPOr; // este campo vai conter o user que aprovou o credito

    @ManyToOne(fetch =FetchType.EAGER )
    //@JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;// este capo no futuro vai conter o Cliente a quem está associado o Credito
    //@Enumerated(EnumType.STRING)

    //@NonNull
    private long valor;
   // @Column(nullable = false)
   // private long saldo;
   @Temporal(TemporalType.DATE)
    private Date doDate;
    @Temporal(TemporalType.DATE)
    private Date beginDate;
   // @Column(nullable = false)
   @Enumerated(EnumType.STRING)
    private CreditoSatus estado;
    private double jurus;

    private double balance;

    //@ToString.Exclude
    //@OneToMany
    //private Set<Garantia> garanias;

    @ToString.Exclude
    @OneToOne
    private Producto producto;
    /**
     * Data da proxima prestacao a ser paga
     */
    private LocalDate proxima_Prestacao;


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getAprovadoPOr() {
        return aprovadoPOr;
    }

    public void setAprovadoPOr(Long aprovadoPOr) {
        this.aprovadoPOr = aprovadoPOr;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Credito setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public long getValor() {
        return valor;
    }

    public void setValor(long valor) {
        this.valor = valor;
    }




    public Date getDoDate() {
        return doDate;
    }

    public void setDoDate(Date doDate) {
        this.doDate = doDate;
    }

    public double getJurus() {
        return jurus;
    }

    public void setJurus(double jurus) {
        this.jurus = jurus;
    }

    public Long getId() {
        return id;
    }

    public Credito setId(Long id) {
        this.id = id;
        return this;
    }

    public CreditoSatus getEstado() {
        return estado;
    }

    public Credito setEstado(CreditoSatus estado) {
        this.estado = estado;
        return this;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public Credito setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Credito setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public Producto getProducto() {
        return producto;
    }

    public Credito setProducto(Producto producto) {
        this.producto = producto;
        return this;
    }

    public LocalDate getProxima_Prestacao() {
        return proxima_Prestacao;
    }

    public Credito setProxima_Prestacao(LocalDate proxima_Prestacao) {
        this.proxima_Prestacao = proxima_Prestacao;
        return this;
    }


}
