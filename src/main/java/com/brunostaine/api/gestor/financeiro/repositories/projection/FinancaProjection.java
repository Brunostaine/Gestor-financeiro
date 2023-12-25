package com.brunostaine.api.gestor.financeiro.repositories.projection;

import java.util.UUID;

public interface FinancaProjection {
    UUID getId();
    String getCategoria();
    String getTipo();
    String getValor();
    String getDescricao();
}
