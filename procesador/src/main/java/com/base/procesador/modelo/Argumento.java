package com.base.procesador.modelo;

import java.util.ArrayList;
import java.util.List;

public class Argumento {

    private List<String> documentos = new ArrayList<>();

    public List<String> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<String> documentos) {
        this.documentos = documentos;
    }
}
