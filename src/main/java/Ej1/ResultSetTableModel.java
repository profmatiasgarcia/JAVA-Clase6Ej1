package Ej1;
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
// Un objeto TableModel que suministra datos ResultSet a un objeto JTable.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.AbstractTableModel;

// las filas y columnas del objeto ResultSet se cuentan desde 1 y
// las filas y columnas del objeto JTable se cuentan desde 0. Al procesar
// filas o columnas de ResultSet para usarlas en un objeto JTable, es
// necesario sumar 1 al número de fila o columna para manipular
// la columna apropiada del objeto ResultSet (es decir, la columna 0 de JTable
// es la columna 1 de ResultSet y la fila 0 de JTable es la fila 1 de ResultSet).
public class ResultSetTableModel extends AbstractTableModel {

    private Connection conexion;
    private Statement instruccion;
    private ResultSet conjuntoResultados;
    private ResultSetMetaData metaDatos;
    private int numeroDeFilas;

    // lleva la cuenta del estado de la conexión a la Base de Datos
    private boolean conectadoABaseDatos = false;

    // el constructor inicializa conjuntoResultados y obtiene su objeto de
    // metadatos y determina el número de filas
    public ResultSetTableModel(String controlador, String url, String nombreusuario, String contrasenia,
            String consulta) throws SQLException, ClassNotFoundException {
        // se conecta a la Base de Datos
        Class.forName(controlador);

        conexion = DriverManager.getConnection(url, nombreusuario, contrasenia);

        // crea objeto Statement para consultar la Base de Datos
        instruccion = conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // actualiza el estado de la conexión a la Base de Datos
        conectadoABaseDatos = true;

        // establece consulta y la ejecuta
        establecerConsulta(consulta);
    } // fin del constructor ResultSetTableModel

    // obtiene la clase que representa el tipo de la columna
    public Class getColumnClass(int columna) throws IllegalStateException {
        // verifica que esté disponible la conexión a la Base de Datos
        if (!conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la Base de Datos");
        }

        // determina la clase de JAVA de la columna
        try {
            String nombreClase = metaDatos.getColumnClassName(columna + 1);

            // devuelve objeto Class que representa a nombreClase
            return Class.forName(nombreClase);
        } // fin de try
        catch (Exception excepcion) {
            excepcion.printStackTrace();
        } // fin de catch

        return Object.class; // si ocurren problemas en el código anterior,
        // asume el tipo Object
    } // fin del método getColumnClass

    // obtiene el número de columnas en el objeto ResultSet
    public int getColumnCount() throws IllegalStateException {
        // verifica que esté disponible la conexión a la Base de Datos
        if (!conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexión a la Base de Datos");
        }

        // determina el número de columnas
        try {
            return metaDatos.getColumnCount();
        } // fin de try
        catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        } // fin de catch

        return 0; // si ocurren problemas en el código anterior, devuelve 0 para
        // el número de columnas
    } // fin del método getColumnCount

    // obtiene el nombre de una columna específica en el objeto ResultSet
    public String getColumnName(int columna) throws IllegalStateException {
        // verifica que esté disponible la conexión a la Base de Datos
        if (!conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la Base de Datos");
        }

        // determina el nombre de la columna
        try {
            return metaDatos.getColumnName(columna + 1);
        } // fin de try
        catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        } // end catch

        return ""; // si hay problemas, devuelve la cadena vacía para el nombre
        // de la columna
    } // fin del método getColumnName

    // devuelve el número de filas en el objeto ResultSet
    public int getRowCount() throws IllegalStateException {
        // verifica que esté disponible la conexión a la Base de Datos
        if (!conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la Base de Datos");
        }

        return numeroDeFilas;
    } // fin del método getRowCount

    // obtiene el valor en la fila y columna específicas
    public Object getValueAt(int fila, int columna) throws IllegalStateException {
        // verifica que esté disponible la conexión a la Base de Datos
        if (!conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la Base de Datos");
        }

        // obtiene un valor en una fila y columna especificadas del objeto
        // ResultSet
        try {
            conjuntoResultados.absolute(fila + 1);
            return conjuntoResultados.getObject(columna + 1);
        } // fin de try
        catch (SQLException excepcionSql) {
            excepcionSql.printStackTrace();
        } // fin de catch

        return ""; // si hay problemas, devuelve el objeto cadena vacía
    } // fin del método getValueAt

    // establece nueva cadena de consulta en la Base de Datos
    public void establecerConsulta(String consulta) throws SQLException, IllegalStateException {
        // verifica que esté disponible la conexión a la Base de Datos
        if (!conectadoABaseDatos) {
            throw new IllegalStateException("No hay conexion a la Base de Datos");
        }

        // especifica la consulta y la ejecuta
        conjuntoResultados = instruccion.executeQuery(consulta);

        // obtiene metadatos para el objeto ResultSet
        metaDatos = conjuntoResultados.getMetaData();

        // determina el número de filas en el objeto ResultSet
        conjuntoResultados.last(); // avanza a la última fila
        numeroDeFilas = conjuntoResultados.getRow(); // obtiene el número de
        // fila

        // notifica al objeto JTable que el modelo ha cambiado
        fireTableStructureChanged();
    } // fin del método establecerConsulta

    // cierra objetos Statement y Connection
    public void desconectarDeBaseDatos() {
        if (conectadoABaseDatos) {
            // cierra objetos Statement y Connection
            try {
                conjuntoResultados.close();
                instruccion.close();
                conexion.close();
            } // fin de try
            catch (SQLException excepcionSql) {
                excepcionSql.printStackTrace();
            } // fin de catch
            finally // actualiza el estado de la conexión a la Base de Datos
            {
                conectadoABaseDatos = false;
            } // fin de finally
        } // fin de if
    } // fin del método desconectarDeBaseDatos
} // fin de la clase ResultSetTableModel
