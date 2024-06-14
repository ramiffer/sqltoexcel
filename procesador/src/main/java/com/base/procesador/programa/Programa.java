package com.base.procesador.programa;

import com.base.procesador.modelo.Argumento;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class Programa implements IPrograma {

    private final JdbcTemplate _jdbcTemplate;

    public Programa(JdbcTemplate jdbcTemplate) {
        this._jdbcTemplate = jdbcTemplate;
    }

    public void start(Argumento argumento) {

        List<List<String>> lista = crearSubListas(argumento);
        List<String> queries = new ArrayList<>();

        int i = 0;
        for (List<String> subLista : lista) {
            queries.add(crearQuery(subLista));
            System.out.println(queries.get(i));
            i++;

        }

        this.ejecutarQuery(queries);


    }

    private static List<List<String>> crearSubListas(Argumento argumento) {

        List<List<String>> l_c = new ArrayList<>(); //Lista completa

        List<String> l_d = argumento.getDocumentos(); //Lista documento
        List<String> l_s = new ArrayList<>(100); //Lista simple

        for (String l_i : l_d) {
            l_s.add(l_i);

            if (l_s.size() == 100) {
                l_c.add(l_s);
                l_s = new ArrayList<>(100);
            }
        }

        if (!l_s.isEmpty()) l_c.add(l_s);

        return l_c;
    }

    private static String crearQuery(List<String> lista) {

        StringBuilder query = new StringBuilder("SELECT * FROM usuario WHERE nro_documento IN (");

        for (int i = 0; i < lista.size(); i++) {
            query.append("'").append(lista.get(i)).append("'");
            if (i < lista.size() - 1) {
                query.append(",");
            }
        }
        query.append(")").append(";");
        return query.toString();


    }

    private void ejecutarQuery(List<String> lista) {

        List<List<String>> resultadosTotales = new ArrayList<>();

        for(String q : lista) {
            List<Map<String, Object>> resultados = _jdbcTemplate
                    .queryForList(q);

            for(Map<String, Object> fila : resultados) {

                List<String> filaResultado = new ArrayList<>(4);
                filaResultado.add(Objects.toString(fila.get("EMAIL")));
                filaResultado.add(Objects.toString(fila.get("CELULAR")));
                filaResultado.add(Objects.toString(fila.get("NOMBRE")));
                filaResultado.add(Objects.toString(fila.get("DOCUMENTO")));
                filaResultado.add(Objects.toString(fila.get("APELLIDO")));
                resultadosTotales.add(filaResultado);

            }

        }
        this.escribirResultadosExcel(resultadosTotales);
    }

    private void escribirResultadosExcel(List<List<String>> datos) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Resultados");


        int rowNum = 0;
        for (List<String> filaDatos : datos) {
            Row row = sheet.createRow(rowNum++);

            int colNum = 0;
            for (String valor : filaDatos) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(valor);
            }

        }

        try (FileOutputStream outputStream = new FileOutputStream("resultados.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }




}
