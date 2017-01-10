import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BibliotecaGUI extends JFrame {
    private JTextArea lista;
    private JButton otro;
    private Biblioteca miBiblioteca;

    private void initBiblioteca(){
        miBiblioteca = new Biblioteca();
        Cancion canción1 = new Cancion("song1","i1",90);
        Cancion canción2 = new Cancion("song2","i1",90);
        Cancion canción3 = new Cancion("song3","i2",120);
        Cancion canción4 = new Cancion("song1","i2",120);
        Cancion canción5 = new Cancion("song3","i2",120);
        Cancion canción6 = new Cancion("song4","i3",150);
        Cancion canción7 = new Cancion("song1","i3",150);

        Album álbum1 = new Album("Álbum 1");
        álbum1.añadeCanción(canción1);
        álbum1.añadeCanción(canción2);
        álbum1.añadeCanción(canción3);

        Album álbum2 = new Album("Álbum 2");
        álbum2.añadeCanción(canción4);
        álbum2.añadeCanción(canción5);
        álbum2.añadeCanción(canción6);
        álbum2.añadeCanción(canción7);

        miBiblioteca.añadeÁlbum(álbum1);
        miBiblioteca.añadeÁlbum(álbum2);
    }

    public BibliotecaGUI(String t){
        super(t);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        initBiblioteca();

        Container p = getContentPane();

        lista = new JTextArea(50,100);
        p.add(new JScrollPane(lista),BorderLayout.CENTER);

        otro = new JButton("Añadir álbum");
        JPanel rellenoBotón = new JPanel();
        rellenoBotón.add(otro);
        p.add(rellenoBotón,BorderLayout.WEST);
        otro.addActionListener(new EscuchaOtro());

        lista.setEditable(false);
        lista.setText(miBiblioteca.toString());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Creamos el ActionListener del botón "otro" cuya función es comenzar la rutina de
     * crear un album y sus derivados.
     */
    class EscuchaOtro implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane.showInputDialog("Introduce the name of your new album");
            DialogoAlbum d = null;
            try{
                if(name.isEmpty()){
                    d = new DialogoAlbum("Unnamed",BibliotecaGUI.this,true);
                }else{
                    d = new DialogoAlbum(name,BibliotecaGUI.this,true);
                }
                if(d.getAlbum() != null){
                    miBiblioteca.añadeÁlbum(d.getAlbum());
                    lista.setText(miBiblioteca.toString());
                }
            }catch(NullPointerException ignored){}
        }
    }

    public static void main(String[] args){
        new BibliotecaGUI("Mi biblioteca");
    }
}
