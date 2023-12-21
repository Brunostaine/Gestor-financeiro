package com.brunostaine.api.gestor.financeiro.entities;

import com.brunostaine.api.gestor.financeiro.enums.Categoria;
import com.brunostaine.api.gestor.financeiro.enums.Tipo;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "receitas_despesas")
public class Financas implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 25)
    private Tipo tipo;
    @Column(name = "valor", nullable = false, length = 25)
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false, length = 25)
    private Categoria categoria;
    @Column(name = "descricao", length = 300)
    private String descricao;
    @CreatedDate
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Financas financas = (Financas) o;
        return Objects.equals(id, financas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Financas{" +
                "id=" + id +
                '}';
    }
}
