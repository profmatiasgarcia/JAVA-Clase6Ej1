package Ej2;
/**
 * @author Prof Matias Garcia.
 * <p> Copyright (C) 2017 para <a href = "https://www.profmatiasgarcia.com.ar/"> www.profmatiasgarcia.com.ar </a>
 * - con licencia GNU GPL3.
 * <p> Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos de la
 * Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
 * bien con la versión 3 de dicha Licencia o bien (según su elección) con cualquier versión posterior. 
 * Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
 * incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN PROPÓSITO
 * PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.
 * Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
 * Si no ha sido así ingrese a <a href = "http://www.gnu.org/licenses/"> GNU org </a>
 */
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class VistaConsola {

    public VistaConsola(ResultSet rs, String nomTabla) {

        ResultSetMetaData metaDatos;
        try {
            rs.first();
            metaDatos = rs.getMetaData();

            int numeroDeColumnas = metaDatos.getColumnCount();
            System.out.println("Lista de " + nomTabla + ":\n");

            for (int i = 1; i <= numeroDeColumnas; i++) {
                System.out.printf("%-20s\t", metaDatos.getColumnName(i));
            }
            System.out.println();

            do {
                for (int i = 1; i <= numeroDeColumnas; i++) {
                    System.out.printf("%-20s\t", rs.getObject(i));
                }
                System.out.println();
            } while (rs.next());

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
