
package LABORATORIO8;


public class Paquete {
    
    private String nombre;
  private double costo;
  private int kilos;

  public void setNombre(String nom){
    nombre = nom;
  }
  public void setCosto(double cos){
    costo = cos;
  }
  public void setKilos(int kil){
    kilos = kil;
  }
  
  public String getNombre(){
    return nombre;
  }

  public double getCosto(){
    return costo;
  }

  public int getKilos(){
    return kilos; 
  }
    @Override
  public String toString(){
      return nombre + "\nkilos: "+kilos+"\nCosto: "+costo;
  }
  
}
