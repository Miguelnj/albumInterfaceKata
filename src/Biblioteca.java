import java.util.*;

/**
 * Created by Miguel on 22/11/2016.
 */
public class Biblioteca {
    private List<Album> listaAlbumes;

    public Biblioteca(){
        listaAlbumes = new LinkedList<>();
    }
    public void añadeÁlbum(Album album){
        listaAlbumes.add(album);
    }

    /**
     *
     * @param name nombre del album requerido
     * @return retornamos el album o null si no se encuentra
     */
    public Album dameÁlbum(String name){
        for (Album album :listaAlbumes) {
            if(album.dameNombre().equals(name)){
                return album;
            }
        }
        return null;
    }

    /**
     *
     * @param name elimina el album que coincide con el nombre
     */
    public void eliminaÁlbum(String name) {
        for (Album album : listaAlbumes) {
            if (album.dameNombre().equals(name)){
                listaAlbumes.remove(album);
                break;
            }
        }
    }

    /**
     *
     * @return Devuelve un set con las canciones que se repiten entre los albumes
     */
    public Set<Cancion> dameCancionesRepetidas(){
        Set<Cancion> cancionesRepetidas = new HashSet<>();
        Set<Cancion> cancionesTotal = new HashSet<>();
        for (Album album:listaAlbumes) {
            for (int i = 0; i < album.númeroDeCanciones(); i++) {
                Cancion cancion = album.dameCancion(i);
                if(!cancionesTotal.add(cancion)){
                    cancionesRepetidas.add(cancion);
                }
                cancionesTotal.add(cancion);
            }
        }
        return cancionesRepetidas;
    }

    /**
     *
     * @return Develve una lista de los intérpretes ordenados por la cantidad de canciones que interpretan y alfabetico
     */
    public List<String> dameIntérpretes(){
        List<Cancion> cancionesTotal = cancionesTotal();
        HashMap<String,Integer> interpretes = new HashMap<>();
        List<String> sortedListtoReturn = new ArrayList<>();

        for (Cancion cancion : cancionesTotal) {
            if(interpretes.containsKey(cancion.dameIntérprete())){
                interpretes.put(cancion.dameIntérprete(),interpretes.get(cancion.dameIntérprete())+1);
            }else{
                interpretes.put(cancion.dameIntérprete(),1);
            }
        }
        Set<Map.Entry<String,Integer>> sortedInterpretes = new TreeSet<>(new newComparator());
        sortedInterpretes.addAll(interpretes.entrySet());

        Iterator<Map.Entry<String,Integer>> iterator = sortedInterpretes.iterator();
        while(iterator.hasNext()){
            sortedListtoReturn.add(iterator.next().getKey());
        }
        return sortedListtoReturn;
    }

    /**
     *
     * @return devolvemos la concatenacion de los albumes de biblioteca y sus canciones
     */
    public String toString(){
        String res = "";
        for (Album album : listaAlbumes) {
            res += album.toString() + "\n";
        }
        return res;
    }

    /**
     *
     * @return método auxiliar para conocer la cantidad total de canciones
     */
    private List<Cancion> cancionesTotal(){
        List<Cancion> cancionesTotal = new LinkedList<>();
        for (Album album:listaAlbumes) {
            for (int i = 0; i < album.númeroDeCanciones(); i++) {
                Cancion cancion = album.dameCancion(i);
                cancionesTotal.add(cancion);
            }
        }
        return cancionesTotal;
    }

    /**
     * Comparador para el metodo intérpretes
     */
    public class newComparator implements Comparator<Map.Entry<String,Integer>>{
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            if(o1.getValue().equals(o2.getValue())){
                return o2.getKey().compareTo(o1.getKey());
            }
            return o2.getValue()-o1.getValue();
        }
    }
}

