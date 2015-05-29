import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
/**
 * Write a description of class Equipo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Equipo
{
    private ArrayList<Jugador>jugadores;
    private String nombre;

    /**
     * Constructor for objects of class Equipo
     */
    public Equipo(String nombre, int numJugadores)
    {
        this.nombre = nombre;
        Random rnd = new Random();
        int clave = rnd.nextInt(numJugadores - 1) + 1;
        jugadores = new ArrayList<>();
        int crack = rnd.nextInt(numJugadores*2);
        boolean esCrack = false;
        for (int i = 1; i<=numJugadores; i++){
            if(i == crack)
            {
                esCrack = true;
            }
            else
            {
                esCrack = false;
            }
            if(i==1){
                jugadores.add(new Portero(i, esCrack));
            }
            else if(i == clave){
                jugadores.add(new Lider(i, esCrack));
            }
            else{
                jugadores.add(new JugadorCampo(i, esCrack));
            }
        }
    }

    public double valoracion(){
        return 0.01;
    }

    /**
     * Devuelve una alineacion titular de jugadores
     */
    public ArrayList<Jugador> alinear(){
        ArrayList<Jugador> alineacion = new ArrayList();
        for(Jugador jugador : jugadores){
            if(jugador instanceof Portero){
                alineacion.add(jugador);
            }
        }
        for(Jugador jugador : jugadores){
            if(jugador instanceof Lider){
                alineacion.add(jugador);
            }
        }
        HashSet<Jugador> jugadoresTotales = new HashSet<>();
        for(Jugador jugador : jugadores){
            if(!(jugador instanceof Lider) && !(jugador instanceof Portero)){
                jugadoresTotales.add(jugador);
            }
        }
        Iterator<Jugador> it = jugadoresTotales.iterator();
        int cont = 0;
        while(cont < 9){
            Jugador titular = it.next();
            alineacion.add(titular);
            cont++;
        }
        return alineacion;
    }
    
    /**
     * Devuelve la plantilla de jugadores
     */
    public ArrayList<Jugador> getPlantilla()
    {
        return jugadores;
    }
    
    /**
     * Devuelve el nombre del equipo
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * Entrena al equipo. Existe una probabilidad de que alguno de los jugadores mejore su estado de forma.
     */
    public void entrenar()
    {
        Random rand = new Random();
        // Representa el esfuerzo del jugador. Si el esfuerzo es mayor que el estado de forma, la forma aumentara en uno
        int esfuerzo;
        int forma;
        for(int i = 0; i < jugadores.size(); i++)
        {
            esfuerzo = rand.nextInt(11);
            forma = jugadores.get(i).getForma();
            // Si es mayor, aumenta la forma en uno
            if (esfuerzo > forma)
            {
                forma++;
                jugadores.get(i).setForma(forma);
            }
        }
    }
}






