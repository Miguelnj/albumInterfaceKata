import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Miguel on 09/12/2016.
 */

public class DialogoCancion extends JDialog{
    private JTextField titulo,interprete,duracion;
    private Cancion songToAdd;

    /**
     * Constructor del Dialogo Cancion
     * @param j recibe el JDialog de DialogoAlbum
     * @param b Recibe un booleano para establecer la modalidad
     */
    public DialogoCancion(JDialog j, Boolean b){
        super(j,b);
        setLayout(new BorderLayout());

        JPanel northPanel = createNorthPanel();
        JPanel middlePanel = createmiddlePanel();
        JPanel southPanel = createSouthPanel();

        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Creamos el panel sur
     * @return retornamos el panel sur creado
     */
    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel secondPanel = new JPanel(new FlowLayout());
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        secondPanel.add(aceptar);
        secondPanel.add(cancelar);

        aceptar.addActionListener(e -> {
            setVisible(false);
            dispose();

            if(duracion.getText().isEmpty()){
                songToAdd = new Cancion(titulo.getText(),interprete.getText()
                        ,0);
            }else{
                songToAdd = new Cancion(titulo.getText(),interprete.getText()
                        ,Integer.parseInt(duracion.getText()));
            }
        });
        cancelar.addActionListener(e -> {
            setVisible(false);
            songToAdd = null;
        });
        /*
         * Establecemos una máscara para la duración , no se pueden introducir mas que numeros
         */
        duracion = new JTextField();
        duracion.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(caracter < '0' || caracter > '9'){
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        panel.add(new JLabel("Duración"),BorderLayout.WEST);
        panel.add(duracion,BorderLayout.CENTER);
        panel.add(secondPanel,BorderLayout.SOUTH);
        return panel;
    }

    /**
     * Creamos el panel del centro
     * @return retornamos el panel creado
     */
    private JPanel createmiddlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        interprete = new JTextField();
        panel.add(new JLabel("Intérprete"),BorderLayout.WEST);
        panel.add(interprete,BorderLayout.CENTER);
        return panel;
    }

    /**
     * Creamos el panel norte
     * @return retornamos el panel norte creado
     */
    private JPanel createNorthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        titulo = new JTextField();
        panel.add(new JLabel("Título"),BorderLayout.WEST);
        panel.add(titulo,BorderLayout.CENTER);
        return panel;
    }

    /**
     * Getter para acceder desde DialogoAlbum a la cancion proporcionada por esta clase
     * @return
     */
    public Cancion getSong() {
        return songToAdd;
    }
}