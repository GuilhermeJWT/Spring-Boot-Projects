package br.com.systemsgs.picpay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_transferencia")
public class Transferencia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "carteira_pagador_id")
    private Carteira pagador;

    @ManyToOne
    @JoinColumn(name = "carteiro_recebedor_id")
    private Carteira recebedor;

    @Column(name = "valor")
    private BigDecimal valor;

    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "data_transacao")
    private Instant dataTransacao;

    public Transferencia(Carteira pagador, Carteira recebedor, BigDecimal valor) {
        this.pagador = pagador;
        this.recebedor = recebedor;
        this.valor = valor;
    }
}
