package proyectoexists1;
//CAMBIAR RUTAS
//primer ejercicio usando eXists

import java.io.File;
import java.util.Iterator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XPathQueryService;

// incluir as librerias de exists: (/home/oracle... exists/lib) TODOS LOS JAR
public class ProyectoExists1 {

    public static String driver = "org.exist.xmldb.DatabaseImpl";

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {

        //CONEXION
        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        //Aquí podemos meter la ruta de la colección a la que queremos acceder
        String coleccion = "/db/cosas";
        Collection col;
        String uri = "xmldb:exist://localhost:8080/exist/xmlrpc";
        col = DatabaseManager.getCollection(uri + coleccion, "admin", "oracle");

        //OJO, FIJARSE EN LO QUE DEVUELVEN LOS MÉTODOS:
        //LISTAR LAS COLECCIONES
        String[] listChildCollections = col.listChildCollections();

        for (String z : listChildCollections) {

            System.out.println(z);

        }

        //LISTAR LOS RECURSOS DE UNA COLECCIÓN
        String[] listResources = col.listResources();

        for (String z : listResources) {

            System.out.println(z);

        }

        //Objeto para la administración de la bd exists:
        CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");

        //CREAR COLECCIÓN:
//        mgtService.createCollection("prueba2");
//        mgtService.createCollection("cosas");
        //
        //BORRAR COLECCIÓN:
        //mgtService.removeCollection("prueba2");
        //DUDA col.removeCollection no va pero sí con el mgtService???
        //
        //SUBIR RECURSO(documento) A UNA COLECCIÓN (los subimos a la colección "cosas"):
        //subimos empleados.xml:
//        File archivo1 = new File("/home/oracle/Desktop/proyectoExists/empleados.xml");
//
//        Resource recursoNuevo1 = col.createResource(archivo1.getName(), "XMLResource");
//
//        recursoNuevo1.setContent(archivo1);
//
//        col.storeResource(recursoNuevo1);
//        //subimos proba.xml
//        File archivo2 = new File("/home/oracle/Desktop/proyectoExists/proba.xml");
//
//        Resource recursoNuevo2 = col.createResource(archivo2.getName(), "XMLResource");
//
//        recursoNuevo2.setContent(archivo2);
//
//        col.storeResource(recursoNuevo2);
//        //subimos proba2.xml
//        File archivo3 = new File("/home/oracle/Desktop/proyectoExists/proba2.xml");
//
//        Resource recursoNuevo3 = col.createResource(archivo3.getName(), "XMLResource");
//
//        recursoNuevo3.setContent(archivo3);
//
//        col.storeResource(recursoNuevo3);
        //
        //BORRAR RECURSO:
        //borramos por ej. proba2.xml
//        Resource recursoborrar = col.getResource("proba2.xml");
//	col.removeResource(recursoborrar);
        //
        /*
         MOSTRAR EL CONTENIDO DE LOS RECURSOS DE UNA COLECCIÓN  (sendo -col- a coleción)
         
         */
        System.out.println("Contenido de los recursos de una colección:");
        //CREAMOS UN NUEVO OBJETO DE SERVCICIO "XPathQueryService" !!!
        XPathQueryService servicioXPQS = (XPathQueryService) col.getService("XPathQueryService", "1.0");

        //Aquí seleccionamos qué recurso de la colección leemos (este query solo muestra el de empleados, buscar el que muestra todos)
        //Diferencia con el siguiente apartado?: 
        ResourceSet recursos = servicioXPQS.query("/EMPLEADOS/EMP_ROW[DEPT_NO=10]");

        ResourceIterator it = (ResourceIterator) recursos.getIterator();

        Resource r;

        while (it.hasMoreResources()) {

            r = (Resource) it.nextResource();

            System.out.println(r.getContent());

        }

        /*
         MOSTRAR CONTENIDO DE UN RECURSO DE UNA COLECCIÓN:  (sendo -col- a coleción  
         e proba.xml o recurso)
         */
        System.out.println("Contenido de un recurso de una colección");
        //esta consulta solo muestra el contenido de una etiqueta en concreto del xml (/PLAY/fm)
        //simplemente introducimos después del nombre del fichero lo que queremos mostrar del documento
        ResourceSet recursos2 = servicioXPQS.queryResource("proba.xml", "/PLAY/fm");

        ResourceIterator it2 = recursos2.getIterator();

        Resource r2;

        while (it2.hasMoreResources()) {

            r2 = it2.nextResource();

            System.out.println(r2.getContent());

        }

        /*
         OPERACIONES DE ACTUALIZACION DE UN SOLO  DOCUMENTO (recurso) DE UNA COLECCIÓN
         */
        //
        //INSERTAR ELEMENTO DENTRO DEL RECURSO/DOCUMENTO DE UNA COLECCIÓN:
        //(en empleados.xml por ej)
//        String cons1="update insert <zona><cod_zona>50</cod_zona><nombre>Madrid-Oeste</nombre><director>Alicia Ramos Martin</director></zona> into /EMPLEADOS/ZONAS";
//        
//         servicioXPQS.queryResource("empleados.xml",cons1);
        //
        //ACTUALIZAR ELEMENTO DENTRO DE UN RECURSO/DOCUMENTO DE UNA COLECCIÓN:
        //(en empleados.xml)
//        String cons2="update value /EMPLEADOS/EMP_ROW[EMP_NO=7369]/APELLIDO with 'RODROGUEZ'";
//        
//        servicioXPQS.queryResource("empleados.xml",cons2);
        //
        //BORRAR ELEMENTO DENTRO DEL RECURSO/DOCUMENTO DE UNA COLECCIÓN:
//       String cons3 ="update delete /EMPLEADOS/ZONAS/zona[cod_zona=50]";
//
//       servicioXPQS.queryResource("empleados.xml",cons3);
        //
        //OPERACIONES DE ACTUALIZACIÓN DE TODOS LOS RECURSOS/DOCUMENTOS DE UNA COLECCIÓN
        //
        //INSERTAR ELEMENTO DENTRO DE LOS RECURSOS/DOCUMENTOS DE UNA COLECCIÓN
        //(vamos a insertar una info en los recursos de la colección cosas)
        //Lo que hace es buscar en todos los documentos por las etiquetas que le hemos especificado
        //Si las encuentra, pues actualiza/inserta su contenido (?)
//        String cons4 = "update insert <zona><cod_zona>50</cod_zona><nombre>Madrid-Oeste</nombre><director>Alicia Ramos Martin</director></zona> into /EMPLEADOS/ZONAS";
//        servicioXPQS.query(cons4);
//
//        //otra:
//        String cons5 = "update insert <autor><cod_autor>1</cod_autor><nombre>luis</nombre><edad>30</edad></autor> into /PLAY";
//        servicioXPQS.query(cons5);
        //
        //ACTUALIZAR ELEMENTO DENTRO DE LOS RECURSOS/DOCUMENTOS DE UNA COLECCIÓN
//        String cons6="update value /EMPLEADOS/EMP_ROW[EMP_NO=7369]/APELLIDO with 'RODROEGUEZ'";
//        servicioXPQS.query(cons6);
        //
        //BORRAR ELEMENTO DENTRO DE LOS RECURSOS/DOCUMENTOS DE UNA COLECCIÓN
//        String cons7 ="update delete /EMPLEADOS/ZONAS/zona[cod_zona=50]";
//        
//        servicioXPQS.query(cons7);
    }

}
