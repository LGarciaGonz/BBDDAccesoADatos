package code;

import database.ConexionBD;
import libs.Leer;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class nuevoDepartamento {

    public static void pedirDatosDepto() {

        // DECLARACIONES ------------
        int numDepto = 0;
        String nombreDepto = "";
        String localizacion = "";

        try {
            // LEER NÚMERO DE DEPARTAMENTO ---------------
            System.out.print("Número de departamento: ");
            Leer.pedirEntero("Número de departamento: ");

            // LEER NOMBRE ---------------
            System.out.print("Nombre de departamento: ");
            Leer.pedirCadena(nombreDepto);

            // LEER LOCALIZACIÓN ---------------
            System.out.print("Localización: ");
            Leer.pedirCadena(localizacion);

            //Departamento depto = new Departamento(numDepto, nombreDepto, localizacion);

        } catch (InputMismatchException e) {
            System.err.println("\n>>> ERROR: EL TIPO DE DATO INTRODUCIDO NO ES CORRECTO");
            nuevoDepartamento.pedirDatosDepto();
        }
    }

    public static void insertarNuevoDepto() {

        // Datos a insertar
        Departamento newDep = new Departamento();
        newDep.setNumDepto(50);
        newDep.setNombreDepto("IT");
        newDep.setLocalizacion("Sabiñanigo");

        try (Connection miCon = ConexionBD.conectar("miBD")){

            PreparedStatement pstmt = miCon.prepareStatement("INSERT INTO departamentos VALUES (?,?,?);");
            pstmt.setInt(1, newDep.getNumDepto());
            pstmt.setString(2, newDep.getNombreDepto());
            pstmt.setString(3, newDep.getLocalizacion());
            pstmt.execute();

         } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("\n>>> ERROR: No se cumple una condición de integridad de la base de datos");
        } catch (SQLSyntaxErrorException e) {
            System.err.println("\n>>> ERROR: Hay un error de sintaxis: " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
