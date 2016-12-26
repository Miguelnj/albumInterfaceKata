/**
 * Clase Cancion que representa canciones
 *
 * @author JDGD
 * @vesion 17/11/2016
 *
 */
public class Cancion implements Comparable {
    private String título;     // nombre de la canción
    private String intérprete; // nombre del intérprete
    private int duración;      // duración de la canción en segundos

    /**
     * Constructor al que se le pasa el título,
     * el intérprete y la duración de la canción
     *
     * @param título nombre de la canción
     * @param intérprete nombre del intérprete
     * @param duración tiempo de la canción en segundos
     */
    public Cancion(String título, String intérprete, int duración) {
        this.título = título;
        this.intérprete = intérprete;
        this.duración = duración;
    }

    /**
     * Devuelve el nombre de la canción
     *
     * @return título de la canción
     */
    public String dameTítulo() {
        return título;
    }

    /**
     * Devuelve el nombre del intérprete de la canción
     *
     * @return intérprete de la canción
     */
    public String dameIntérprete() {
        return intérprete;
    }

    /**
     * Devuelve la duración de la canción
     *
     * @return duración de la canción
     */
    public int dameDuración() {
        return duración;
    }

    /**
     * @return String con la concatenación entre corchetes del título,
     * el intérprete y la duración de la canción, separados por un espacio.
     */
    public String toString() {
        return "[Título:"+título+" intérprete:"+intérprete+" duración:"+duración+"]";
    }
    @Override
    public boolean equals(Object c) {
        return c instanceof Cancion && this.título.equals(((Cancion) c).dameTítulo());
    }
    @Override
    public int compareTo(Object c){
        if(c instanceof Cancion){
            if(this.título.equals(((Cancion) c).dameTítulo())){
                return 0;
            }
        }
        return -1;
    }
    @Override
    public int hashCode(){
        return título.hashCode() + intérprete.hashCode();
    }
}
