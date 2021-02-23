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
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class AccDatos {

    private Connection conexion = null; // maneja la conexión
    private Statement instruccion = null; // instrucción de consulta
    private ResultSet conjuntoResultados = null; // maneja los resultados
    private String nomTabla;
    private String consulta;

    public AccDatos(String CONTROLADOR, String URL_BASEDATOS, String USER, String PASS) {

        // se conecta a la base de datos libros y realiza una consulta
        try {
            consulta = "SELECT * FROM ";
            nomTabla = "materias";
            // carga la clase controlador
            Class.forName(CONTROLADOR);

            // establece la conexión a la base de datos
            conexion = DriverManager.getConnection(URL_BASEDATOS, USER, PASS);

            // crea objeto Statement para consultar la base de datos
            instruccion = conexion.createStatement();

            conjuntoResultados = instruccion.executeQuery(consulta + nomTabla);
        } catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        } // fin de catch
        catch (ClassNotFoundException noEncontroClase) {
            noEncontroClase.printStackTrace();
        } // fin de catch

    }

    public ResultSet getConjuntoResultados() {
        return conjuntoResultados;
    }

    public void mostrarTabla(String nomTabla) {
        try {
            // consulta la base de datos
            conjuntoResultados = instruccion.executeQuery(consulta + nomTabla);
        } catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        }
        VistaConsola vc = new VistaConsola(conjuntoResultados, nomTabla);

    }

    public void cerrarBD() {

        try {
            conjuntoResultados.close();
            instruccion.close();
            conexion.close();
        } // fin de try
        catch (Exception excepcion) {
            excepcion.printStackTrace();
        } // fin de catch

    }
}
