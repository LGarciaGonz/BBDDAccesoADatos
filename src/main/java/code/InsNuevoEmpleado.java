package code;

import database.ConexionBD;
import libs.Leer;

import java.beans.PropertyEditorSupport;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsNuevoEmpleado {

    public static void insertarEmpleado() {

        // DECLARACIONES
        Empleado nuevoEmpleado = new Empleado();
        String nombreDep;
        String nombreBD = "miBD";
        int idDepRec = 0;

        // PEDIR LOS DATOS DEL EMPLEADO A CREAR
        nuevoEmpleado.setNumEmpleado(Leer.pedirEntero("Inserte el número del empleado:"));
        nuevoEmpleado.setApellido(Leer.pedirCadena("Inserte el apellido del empleado:"));
        nombreDep = Leer.pedirCadena("Inserte el nombre del departamentos al que pertenece:").toUpperCase();



        try (Connection miConexion = ConexionBD.conectar(nombreBD)) {

            /**
             * Montamos la secuencia SQL de comprobación de que el departamento existe.
             * Devuelve el id del departamento SI EXISTE
             */

            PreparedStatement pstmt = miConexion.prepareStatement("SELECT dept_no FROM departamentos WHERE dnombre = ?");
            pstmt.setString(1, nombreDep);

            // Ejecutar la sentencia y almacenamos el resultado en el ResultSet
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                idDepRec = rs.getInt("dept_no");

            }

            if (idDepRec == 0) {
                System.err.println(">>> ERROR: El departamento indicado no existe y el empleado no se insertará.");
            } else {
                nuevoEmpleado.setNumDepto(idDepRec);
                PreparedStatement insEmp = miConexion.prepareStatement("insert into empleados (emp_no, apellido, dept_no) values (?,?,?);");
                insEmp.setInt(1, nuevoEmpleado.getNumEmpleado());
                insEmp.setString(2, nuevoEmpleado.getApellido());
                insEmp.setInt(3, nuevoEmpleado.getNumDepto());

                insEmp.execute();
            }

            nuevoEmpleado.setNumDepto(idDepRec);

            System.out.println(idDepRec);

        } catch (SQLException e) {
            System.err.println("No se puede conectar a la base de datos.");
        }

    }
}
