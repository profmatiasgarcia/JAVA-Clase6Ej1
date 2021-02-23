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

public class JavaAppBaseDeDatos {

    // nombre del controlador de JDBC y URL de la base de datos
   // static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    static final String CONTROLADOR = "org.mariadb.jdbc.Driver";
   // static final String URL_BASEDATOS = "jdbc:mysql://localhost/instituto";
    static final String URL_BASEDATOS = "jdbc:mariadb://localhost/instituto";

    public static void main(String[] args) {
//        AccDatos ad = new AccDatos(CONTROLADOR, URL_BASEDATOS, "root", "");
//        ad.mostrarTabla("profesores");
//        ad.mostrarTabla("materias");
//        ad.cerrarBD();

        Controlador c = new Controlador(new AccDatos(CONTROLADOR, URL_BASEDATOS, "root", ""));
        c.ejecutar();

    }

}
