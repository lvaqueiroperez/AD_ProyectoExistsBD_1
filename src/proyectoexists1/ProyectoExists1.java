package proyectoexists1;

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

// incluir as librerias de exists: (/home/oracle... exists) TODOS LOS JAR
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
        //devolver las colecciones
        String[] listChildCollections = col.listChildCollections();

        for (String z : listChildCollections) {

            System.out.println(z);

        }

        //devolver los recursos
        String[] listResources = col.listResources();

        for (String z : listResources) {

            System.out.println(z);

        }

        //Objeto para la administración de la bd exists:
        CollectionManagementService mgtService = (CollectionManagementService) col.getService("CollectionManagementService", "1.0");

        //crear colección:
        //mgtService.createCollection("prueba2");
        //mgtService.createCollection("cosas");
        //borrar colección:
        //mgtService.removeCollection("prueba2");
        //subir  recurso(documento) a unha colecion (los subimos a la colecció "cosas"):
        //subimos empleados.xml:
//        File archivo=new File ("/home/oracle/Desktop/eXists/empleados.xml");
//        
//        Resource recursoNuevo  = col.createResource(archivo.getName(),"XMLResource");
//       
//	recursoNuevo.setContent(archivo);
//        
//        col.storeResource(recursoNuevo);
        //subimos proba.xml
//        File archivo = new File("/home/oracle/Desktop/eXists/proba.xml");
//
//        Resource recursoNuevo = col.createResource(archivo.getName(), "XMLResource");
//
//        recursoNuevo.setContent(archivo);
//
//        col.storeResource(recursoNuevo);
        //subimos proba2.xml
//        File archivo = new File("/home/oracle/Desktop/eXists/proba2.xml");
//
//        Resource recursoNuevo = col.createResource(archivo.getName(), "XMLResource");
//
//        recursoNuevo.setContent(archivo);
//
//        col.storeResource(recursoNuevo);
        
        //BORRAR RECURSO:
        //borramos por ej. proba2.xml
//        Resource recursoborrar = col.getResource("proba2.xml");
//	col.removeResource(recursoborrar);
        
        
        /*
        amosar contido dos recursos dunha colecion :  (sendo -col- a coleción  
        )
        */
        System.out.println("contido de la colección");
        //creamos objeto de servicio:
       XPathQueryService servicio= (XPathQueryService) col.getService("XPathQueryService", "1.0");
       
       //aquí seleccionamos qué recurso de la colección leemos (este query solo muestra el de empleados, buscar el que muestra todos)
       ResourceSet recursos= servicio.query("/EMPLEADOS/EMP_ROW[DEPT_NO=10]");
       
       ResourceIterator it = (ResourceIterator) recursos.getIterator();
       
       Resource r;
       
       while(it.hasMoreResources()){
        
        r = (Resource)  it.nextResource();
        
        System.out.println(r.getContent());
        
    }
       
    
       /*
       amosar contido dun recurso dunha colecion :  (sendo -col- a coleción  
       e proba.xml o recurso)
       */
       System.out.println("contido de un fichero de la colección");
       
       ResourceSet recursos2 = servicio.queryResource("proba.xml","/PLAY/fm");
       
       ResourceIterator it2 = recursos2.getIterator();
       
       Resource r2;
       
       while(it2.hasMoreResources()){
           
           r2 = it2.nextResource();
           
           System.out.println(r2.getContent());
           
       }
       
       
      
       /*
       OPERACIONS de ACTUALIZACION DUN so  DOCUMENTO (recurso) dunha colecion
       */
       
        
        
        
        
        

    }

}
