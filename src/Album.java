import java.util.LinkedList;
import java.util.List;

/**
 * Created by Miguel on 22/11/2016.
 */
/**
 * Album suministra una secuencia de canciones
 */
public class Album implements Comparable{
    private String name;
    private List<Cancion> songs;
    /**
     * Configura el nombre del Album y la lista.
     *
     * @param name nombre del Album
     */
    public Album(String name){
        songs = new LinkedList<>();
        this.name = name;
    }
    /**
     * Devuelve el nombre del album
     *
     * @return nombre del Album
     */
    public String dameNombre(){
        return this.name;
    }
    /**
     * Devuelve la cantidad de canciones almacenadas en el Album
     *
     * @return tamaño de la lista = numero de canciones
     */
    public int númeroDeCanciones(){
        return songs.size();
    }
    /**
     * @param identificator identificador de la cancion a devolver
     * @return Cancion dada por identificador o null si no se encuentra.
     */
    public Cancion dameCancion(int identificator){
        if(identificator < 0 || identificator > songs.size()){
            return null;
        }
        return songs.get(identificator);
    }
    /**
     * Metodo que añade una cancion
     *
     * @param song song to be added
     */
    public void añadeCanción(Cancion song){
        songs.add(song);
    }
    /**
     * Metodo que elimina una cancion de la posicion determinada
     *
     * @param identificator posicion de la cancion que sera eliminada
     */
    public void quitaCanción(int identificator){
        if(!(identificator < 0 || identificator > songs.size())){
            songs.remove(identificator);
        }
    }
    /**
     * Devuelve la duración total del album
     *
     * @return
     */
    public int duración(){
        int duracion = 0;
        for (Cancion song :songs) {
            duracion += song.dameDuración();
        }
        return duracion;
    }

    /**
     * Método que pasa a String el album y sus canciones
     *
     * @return la String con el titulo del album y las canciones que
     */
    public String toString() {
        String res = this.name + ":" + "\n";
        for (int i = 0; i < númeroDeCanciones(); i++) {
            res +=(i+1) + ") " + songs.get(i) + "\n";
        }
        return res;
    }

    /**
     *
     * @param  c es e objeto el cual queremos comparar
     * @return el resultado de la comparacion
     */
    @Override
    public int compareTo(Object c){
        if(c instanceof Album){
            if(this.name.equals(((Album) c).name)){
                return 0;
            }
        }
        return -1;
    }

    /**
     *
     * @param c objeto a comparar
     * @return devolvemos true o false según la comparacion
     */
    @Override
    public boolean equals (Object c){
        return c instanceof Album && this.hashCode() == c.hashCode();
    }
}
