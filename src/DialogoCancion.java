import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Miguel on 09/12/2016.
 */

public class DialogoCancion extends JFrame{
    private JTextField titulo,interprete,duracion;
    public DialogoCancion(JFrame frame){
        super("Dialogo Canción");
        setLayout(new BorderLayout());

        JPanel northPanel = createNorthPanel();
        JPanel middlePanel = createmiddlePanel();
        JPanel southPanel = createSouthPanel();

        add(northPanel,BorderLayout.NORTH);
        add(middlePanel,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setLocationRelativeTo(frame);
    }

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
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            DialogoAlbum d = CreadorAlbum.getDialogoAlbum();
            Album album = d.getAlbum();
            if(Integer.parseInt(duracion.getText()) == 0){
                album.añadeCanción(new Cancion(titulo.getText(),interprete.getText()
                        ,Integer.parseInt(duracion.getText())));
            }else{
                album.añadeCanción(new Cancion(titulo.getText(),interprete.getText()
                        ,0));
            }
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    d.refreshTextArea();
                }
            });
        });
        cancelar.addActionListener(e -> setVisible(false));
        duracion = new JTextField();
        duracion.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent e)
            {
                char caracter = e.getKeyChar();

                // Verificar si la tecla pulsada no es un digito
                if(caracter < '0' || caracter > '9' && caracter != '\b'){
                    e.consume();  // ignorar el evento de teclado
                }
            }
        });
        panel.add(new JLabel("Duración"),BorderLayout.WEST);
        panel.add(duracion,BorderLayout.CENTER);
        panel.add(secondPanel,BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createmiddlePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        interprete = new JTextField();
        panel.add(new JLabel("Intérprete"),BorderLayout.WEST);
        panel.add(interprete,BorderLayout.CENTER);
        return panel;
    }

    private JPanel createNorthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        titulo = new JTextField();
        panel.add(new JLabel("Título"),BorderLayout.WEST);
        panel.add(titulo,BorderLayout.CENTER);
        return panel;
    }
}
