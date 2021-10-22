
package LABORATORIO8;

import java.util.*;
import java.util.Map.Entry;

public class Envios {
     public static void main(String[] args) {
         
        Scanner sc = new Scanner(System.in);
        String menu;
    
        int opcion; 
        HashMap <Integer, String []> estados = new HashMap<>(); 
        HashMap <Integer, Paquete> paquetes = new HashMap<>();
         
         
         do{
             System.out.println("   ****MENÚ****    \n\n1.Ingresar Paquete"
                 + "\n2.Dar estado a paquete\n3.Mostrar paquete\n4.Paquete por estado");
            System.out.println("\nescriba aquí: ");
             opcion = sc.nextInt();
             
         switch (opcion) {
             case 1:
                 IngresarPaquete(paquetes, estados);
                 //llenadoautomatico(paquetes, estados);
                 break;
             case 2:
                 estadosPaquete(paquetes, estados);
                 break;
             case 3:
                 mostrarPaquetes(paquetes);
                 break;
             case 4:
                 MostrarPorEstado(paquetes, estados);
                 break;
             default:
                 break;
         }
             System.out.println("\nDESEA REGRESAR AL MENÚ <si> <no>");
             System.out.println("escriba aquí: ");
             menu = sc.next();
             System.out.println("");
         }
         while(menu.equalsIgnoreCase("si"));
    }
     
     
     public static void mostrarPaquetes(HashMap <Integer, Paquete> paquetes){
         
         int ide;
        for (Entry <Integer, Paquete> p : paquetes.entrySet()) {
            
            System.out.println( "\n IDE: "+ p.getKey() +"\n"+ p.getValue());
        }
     }
     public static void llenadoautomatico(HashMap <Integer, Paquete> paquetes,
             HashMap <Integer, String []> estados){
         
        int identificador, kilos;
        double costo;
        String nombre, rpt= "no";
        Paquete paquet = new Paquete();
        for (int i = 0; i < 20; i++) {
        nombre = "paquete0"+i;
        identificador = (int) (Math.random()*1000+1);
        kilos = (int) (Math.random()*5+1);
        costo = (int) (Math.random()*5+1);
        
        paquet.setCosto(costo);
        paquet.setKilos(kilos);
        paquet.setNombre(nombre);
        String[] estad = new String[4];
             System.out.println("\n"+"ide"+identificador+"\n"+paquet);
        estados.put(identificador, estad);
        paquetes.put(identificador, paquet);
            if (i%2==0) {
                estados.get(identificador)[0] = "recibido";
                estados.get(identificador)[3] = "entregado";
            }
            else if (i%3==0) {
                estados.get(identificador)[0] = "recibido";
                estados.get(identificador)[1] = "progreso";
            }
            else if (i%5==0) {
                estados.get(identificador)[0] = "recibido";
                estados.get(identificador)[2] = "falloentrega";
            }
        }
     }
     
     public static void IngresarPaquete(HashMap <Integer, Paquete> paquetes,
             HashMap <Integer, String []> estados){
         
        Scanner sc = new Scanner(System.in);
        int identificador, kilos;
        double costo;
        String nombre, rpt= "no";
        Paquete paquet = new Paquete();
        
        System.out.print("\nIngrese identificador de paquete: ");
        identificador = sc.nextInt();
        
        while(paquetes.containsKey(identificador)){
           
            System.out.println("ESTE PAQUETE YA SE ENCUENTRA REGISTRADO\n"
                    + "¿Desea modificarlo? escriba <si> o escriba <no>"
                    + "para ingresar otro paquete");
            rpt = sc.next();
            if(rpt.equals("si")){
                break;
            }
            
            if(rpt.equals("no")){
                System.out.println("Ingrese identificador de paquete");
                identificador = sc.nextInt();
            }
        }
        
        System.out.print("Ingrese nombre de paquete: ");
        nombre = sc.next();
        System.out.print("Ingrese kilos: ");
        kilos = sc.nextInt();
        System.out.print("Ingrese costo: ");
        costo = sc.nextDouble();
        
        paquet.setCosto(costo);
        paquet.setKilos(kilos);
        paquet.setNombre(nombre);
        String[] estad = new String[4];
        
        estados.put(identificador, estad);
        paquetes.put(identificador, paquet);
        
        
    }
     
    public static void estadosPaquete(HashMap <Integer, Paquete> paquetes
            , HashMap <Integer, String []> estados){
        Scanner sc = new Scanner(System.in);
        
        
        int identificador,pos=-1;
        String estado="";
        boolean guardado = false;
        
        System.out.println("\nIngrese identificador");
        identificador = sc.nextInt();
        System.out.println("Escriba estado\n-Recibido\n-Progreso\n-FalloEntrega\n-Entregado");
        System.out.println("\nescriba aquí:");
        estado = sc.next();
        System.out.println("");
        
        if(estado.equalsIgnoreCase("Progreso")){
            pos = 1;
        }
        else if (estado.equalsIgnoreCase("FalloEntrega")){
            pos = 2;
        }
        else if (estado.equalsIgnoreCase("Entregado")){
            pos = 3;
        }
        
        //VERIFICA SI EL ESTADO YA ESTA REGISTRADO
        for (int i = 0; i < estados.get(identificador).length; i++) {
            if (estado.equalsIgnoreCase(estados.get(identificador)[i])) {
                guardado = true;
            }
        }
        
        //SI NO SE ENCUENTRA EL PAQUETE
        if (!paquetes.containsKey(identificador)) {
            
            System.out.println("NO SE HA ENCONTRADO UN PAQUETE CON ESE INDICADOR");
        }
        
        //SI SE ENCUENTRA EL PAQUETE
        else{
        
        if(guardado==true){
            System.out.println("¡ESTE ESTADO YA SE ENCUENTRA REGISTRADO!");
        }
        else{
            //SI EL PAQUETE YA HA SIDO ENTREGADO NO ADMITE MAS ESTADOS
            //PUESTO QUE NO TENDRÍA SENTIDO
            if(("entregado").equals(estados.get(identificador)[3])){
                System.out.println("ESTE PAQUETE YA NO ADMITE MAS ESTADOS");
                }
            
            else{
                
                //PARA LOS ESTADOS DIFERENTES A "RECIBIDO"--
                //PARA QUE ESTOS ESTADOS PUEDAN AGENDARSE EL PAQUETE DEBE TENER--
                //EL ESTADO RECIBIDO
                if(("recibido").equals(estados.get(identificador)[0]) 
                && !estado.equalsIgnoreCase("recibido")){
                     estados.get(identificador)[pos] = estado;
                     System.out.println("SE GUARDÓ EL ESTADO EXITOSAMENTE");
                }
                //AQUÍ NO SE TIENE EL ESTADO RECIBIDO
                else if (estado.equalsIgnoreCase("recibido")){
                    estados.get(identificador)[0] = estado;
                    System.out.println("SE GUARDÓ EL ESTADO EXITOSAMENTE");
                }
                else{
                System.out.println("LO SENTIMOS.\nEL PAQUETE PRIMERO DEBE TENER ESTADO: <RECIBIDO>");
                }
                }
        }
        }
            
        }
    
    
        public static void MostrarPorEstado(HashMap <Integer, Paquete> paquetes
            , HashMap <Integer, String []> estados){
            
            Scanner sc = new Scanner(System.in);
            
            String estado;
            
            System.out.println("\nIngrese el estado");
            estado = sc.next();
            
            for (Map.Entry<Integer, String[]> entry : estados.entrySet()) {
                
                for (int i = 0; i < entry.getValue().length; i++) {
                    if(estado.equalsIgnoreCase(entry.getValue()[i])){
                        System.out.println("**PAQUETE IDE: "+ entry.getKey()+"**");
                        System.out.println(paquetes.get(entry.getKey()));
                    }
                }
            }
        
        }
}
