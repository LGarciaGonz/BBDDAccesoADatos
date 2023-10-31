package database;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class gestionDB {
    // CREAR BBDD ----------------------------------------------------------------------------
    public static void creaDB() {
        try (Connection miConexion = ConexionSGBD.conectar()) {

            // Crear la base de datos
            Statement crearBD = miConexion.createStatement();
            crearBD.executeUpdate("DROP DATABASE IF EXISTS mibd");
            crearBD.executeUpdate("CREATE DATABASE mibd");

        } catch (SQLException e) {
            System.err.println("\n>>> ERROR: error al conectar a la base de datos");
        }
    }

    public static void crearTablaDeps(){
        //al utilizar el try with resources con un objeto closeable, este se cerrará en cualquier caso.
        try(Connection miCon = ConexionBD.conectar("mibd")) {
            //sentencias SQL para crear tabla departamentos
            String tablaDep = "CREATE TABLE departamentos (\n" +
                    " dept_no  TINYINT(2) NOT NULL PRIMARY KEY,\n" +
                    " dnombre  VARCHAR(15), \n" +
                    " loc      VARCHAR(15)\n" +
                    ")";
            //sentencias SQL para añadir los valores de varios departamentos a la tabla
            List<String> addDeps = Arrays.asList("INSERT INTO departamentos VALUES (10,'CONTABILIDAD','SEVILLA')",
                    "INSERT INTO departamentos VALUES (20,'INVESTIGACIÓN','MADRID')",
                    "INSERT INTO departamentos VALUES (30,'VENTAS','BARCELONA')",
                    "INSERT INTO departamentos VALUES (40,'PRODUCCI N','BILBAO')");
            //variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaDep = miCon.createStatement();
            //borrado de las tablas antes de crearlas para no incurrir en violaciones de integridad
            crearTablaDep.executeUpdate("DROP TABLE IF EXISTS empleados");
            crearTablaDep.executeUpdate("DROP TABLE IF EXISTS departamentos");
            crearTablaDep.executeUpdate(tablaDep);
            for (String d : addDeps) {
                crearTablaDep.executeUpdate(d);
            }
        }catch (SQLSyntaxErrorException e) {
            System.out.println("Error en la sintaxis de la sentencia SQL" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("La sentencia SQL no cumple con los requisitos de integridad " +
                    "de la base de datos" + e.getMessage());
        }catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos");
        }
    }

    public static void crearTablaEmps() {
        try(Connection miCon = ConexionBD.conectar("mibd")) {
            //crear tabla departamentos
            String tablaEmp = "CREATE TABLE empleados (\n" +
                    " emp_no    SMALLINT(4)  NOT NULL PRIMARY KEY,\n" +
                    " apellido  VARCHAR(10),\n" +
                    " oficio    VARCHAR(10),\n" +
                    " dir       SMALLINT,\n" +
                    " fecha_alt DATE      ,\n" +
                    " salario   FLOAT(6,2),\n" +
                    " comision  FLOAT(6,2),\n" +
                    " dept_no   TINYINT(2) NOT NULL,\n" +
                    " CONSTRAINT FK_DEP FOREIGN KEY (dept_no ) REFERENCES departamentos(dept_no)\n" +
                    ")";
            List<String> addEmps = Arrays.asList("INSERT INTO empleados VALUES (7369,'S NCHEZ','EMPLEADO',7902,'1990/12/17',1040,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7499,'ARROYO','VENDEDOR',7698,'1990/02/20',1500,390,30)" ,
                    "INSERT INTO empleados VALUES (7521,'SALA','VENDEDOR',7698,'1991/02/22',1625,650,30)" ,
                    "INSERT INTO empleados VALUES (7566,'JIM NEZ','DIRECTOR',7839,'1991/04/02',2900,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7654,'MART N','VENDEDOR',7698,'1991/09/29',1600,1020,30)" ,
                    "INSERT INTO empleados VALUES (7698,'NEGRO','DIRECTOR',7839,'1991/05/01',3005,NULL,30)" ,
                    "INSERT INTO empleados VALUES (7782,'CEREZO','DIRECTOR',7839,'1991/06/09',2885,NULL,10)" ,
                    "INSERT INTO empleados VALUES (7788,'GIL','ANALISTA',7566,'1991/11/09',3000,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7839,'REY','PRESIDENTE',NULL,'1991/11/17',4100,NULL,10)" ,
                    "INSERT INTO empleados VALUES (7844,'TOVAR','VENDEDOR',7698,'1991/09/08',1350,0,30)" ,
                    "INSERT INTO empleados VALUES (7876,'ALONSO','EMPLEADO',7788,'1991/09/23',1430,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7900,'JIMENO','EMPLEADO',7698,'1991/12/03',1335,NULL,30)" ,
                    "INSERT INTO empleados VALUES (7902,'FERN NDEZ','ANALISTA',7566,'1991/12/03',3000,NULL,20)" ,
                    "INSERT INTO empleados VALUES (7934,'MU OZ','EMPLEADO',7782,'1992/01/23',1690,NULL,10)");

            Statement crearTablaDep = miCon.createStatement();
            crearTablaDep.execute("DROP TABLE IF EXISTS empleados");
            crearTablaDep.executeUpdate(tablaEmp);

            for (String e : addEmps) {
                crearTablaDep.execute(e);
            }

        }catch (SQLSyntaxErrorException e) {
            System.out.println("Error en la sintaxis de la sentencia SQL" + e.getMessage());
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("La sentencia SQL no cumple con los requisitos de integridad de la base de datos" + e.getMessage());
        }catch (SQLException e) {
            System.out.println("No se puede conectar a la base de datos");
            e.printStackTrace();
        }

    }


//    // CREAR TABLAS DE LA BBDD ----------------------------------------------------------------
//    public static void crearTablas() {
//
//        String nombreBD = "miBD";
//
//        try (Connection miConexion = ConexionBD.conectar(nombreBD)) {
//            // Crear tabla "departamentos"
//            String tablaDep = """
//                    CREATE TABLE departamentos (
//                     dept_no  TINYINT(2) NOT NULL PRIMARY KEY,
//                     dnombre  VARCHAR(15),
//                     loc      VARCHAR(15)
//                    ) ENGINE=InnoDB;""";
//
//            // Sentencias para añadir los valores de los departamentos a la tabla.
//            String addDep = "INSERT INTO departamentos VALUES (10,'CONTABILIDAD','SEVILLA')";
//            String addDep2 = "INSERT INTO departamentos VALUES (20,'INVESTIGACIÓN','MADRID')";
//
//            // Varibale para ejecutar las sentencias SQL
//            Statement crearTablaDep = miConexion.createStatement();
//
//            // Borrado de las tablas si existen previamente.
//            crearTablaDep.executeUpdate("DROP TABLE IF EXISTS empleados");
//            crearTablaDep.executeUpdate("DROP TABLE IF EXISTS departamentos");
//            crearTablaDep.executeUpdate(tablaDep);
//            crearTablaDep.execute(addDep);
//            crearTablaDep.execute(addDep2);
//
//        } catch (SQLException e) {
//            System.err.println("\n>>> ERROR: No se puede conectar a la base de datos / no es posible crear la tabla.");
//        }

//    public static void crearTablaEmps() {
//        try (Connection miConexion = ConexionBD.conectar("miBD")) {
//
//            // Crear tabla "empleados"
//            String tablaEmp = """
//                    CREATE TABLE empleados (
//                                                     emp_no    SMALLINT(4)  NOT NULL PRIMARY KEY,
//                                                     apellido  VARCHAR(10),
//                                                     oficio    VARCHAR(10),
//                                                     dir       SMALLINT,
//                                                     fecha_alt DATE      ,
//                                                     salario   FLOAT(6,2),
//                                                     comision  FLOAT(6,2),
//                                                     dept_no   TINYINT(2) NOT NULL,
//                                                     CONSTRAINT FK_DEP FOREIGN KEY (dept_no ) REFERENCES departamentos(dept_no)
//
//                                                    )""";
//
//
//            List<String> addEmp = Arrays.asList(
//                    "INSERT INTO empleados VALUES (7369,'S NCHEZ','EMPLEADO',7902,'1990/12/17',1040,NULL,60)",
//                    "INSERT INTO empleados VALUES (7499,'ARROYO','VENDEDOR',7698,'1990/02/20',1500,390,30)",
//                    "INSERT INTO empleados VALUES (7521,'SALA','VENDEDOR',7698,'1991/02/22',1625,650,30)",
//                    "INSERT INTO empleados VALUES (7566,'JIMENEZ','DIRECTOR',7839,'1991/04/02',2900,NULL,20)",
//                    "INSERT INTO empleados VALUES (7654,'MARTIN','VENDEDOR',7698,'1991/09/29',1600,1020,30)" ,
//                    "INSERT INTO empleados VALUES (7698,'NEGRO','DIRECTOR',7839,'1991/05/01',3005,NULL,30)" ,
//                    "INSERT INTO empleados VALUES (7782,'CEREZO','DIRECTOR',7839,'1991/06/09',2885,NULL,10)" ,
//                    "INSERT INTO empleados VALUES (7788,'GIL','ANALISTA',7566,'1991/11/09',3000,NULL,20)" ,
//                    "INSERT INTO empleados VALUES (7839,'REY','PRESIDENTE',NULL,'1991/11/17',4100,NULL,10)" ,
//                    "INSERT INTO empleados VALUES (7844,'TOVAR','VENDEDOR',7698,'1991/09/08',1350,0,30)" ,
//                    "INSERT INTO empleados VALUES (7876,'ALONSO','EMPLEADO',7788,'1991/09/23',1430,NULL,20)" ,
//                    "INSERT INTO empleados VALUES (7900,'JIMENO','EMPLEADO',7698,'1991/12/03',1335,NULL,30)" ,
//                    "INSERT INTO empleados VALUES (7902,'FERN NDEZ','ANALISTA',7566,'1991/12/03',3000,NULL,20)" ,
//                    "INSERT INTO empleados VALUES (7934,'MUÑOZ','EMPLEADO',7782,'1992/01/23',1690,NULL,10)"
//            );
//
//            Statement crearTablaEmp = miConexion.createStatement();
//            crearTablaEmp.executeUpdate("DROP TABLE IF EXISTS empleados");
//            crearTablaEmp.executeUpdate(tablaEmp);
//
//            for (String e : addEmp) {
//                crearTablaEmp.executeUpdate(e);
//            }
//        } catch (SQLException e) {
//            System.err.println("\n>>> ERROR: No es posible crear la tabla Empleados." + e.getMessage());
//
//        }
    //}
}
