package com.base.procesador;

import com.base.procesador.modelo.Argumento;
import com.base.procesador.programa.IPrograma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProcesadorApplication implements CommandLineRunner {

    @Autowired
    private IPrograma iPrograma;

    public static void main(String[] args) {
        SpringApplication.run(ProcesadorApplication.class, args);

    }

    public void run(String... args) {
        Argumento argumento = new Argumento();
        argumento.setDocumentos(List.of("doc1", "doc2", "doc3", "doc4", "doc5", "doc6", "doc7", "doc8", "doc9", "doc10", "doc11", "doc12", "doc13", "doc14", "doc15", "doc16", "doc17", "doc18", "doc19", "doc20", "doc21", "doc22", "doc23", "doc24", "doc25", "doc26", "doc27", "doc28", "doc29", "doc30", "doc31", "doc32", "doc33", "doc34", "doc35", "doc36", "doc37", "doc38", "doc39", "doc40", "doc41", "doc42", "doc43", "doc44", "doc45", "doc46", "doc47", "doc48", "doc49", "doc50", "doc51", "doc52", "doc53", "doc54", "doc55", "doc56", "doc57", "doc58", "doc59", "doc60", "doc61", "doc62", "doc63", "doc64", "doc65", "doc66", "doc67", "doc68", "doc69", "doc70", "doc71", "doc72", "doc73", "doc74", "doc75", "doc76", "doc77", "doc78", "doc79", "doc80", "doc81", "doc82", "doc83", "doc84", "doc85", "doc86", "doc87", "doc88", "doc89", "doc90", "doc91", "doc92", "doc93", "doc94", "doc95", "doc96", "doc97", "doc98", "doc99", "doc100", "doc101", "doc102", "doc103"));
        iPrograma.start(argumento);
    }

}
