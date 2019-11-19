package br.com.springbootapi_backoffice.entity;

import javax.persistence.*;

@Entity
@Table(name = "Token", schema = "sc")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long clienteId;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Boolean validado;

    public long getId() {
        return id;
    }

    public long getClienteId() {
        return clienteId;
    }

    public String getToken() {
        return token;
    }

    public Boolean getValidado() {
        return validado;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setValidated(Boolean validado) {
        this.validado = validado;
    }

}