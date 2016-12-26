import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Miguel on 20/12/2016.
 */
public class CreadorAlbum extends JFrame {
    private JTextField name;
    private static DialogoAlbum d;
    public CreadorAlbum(JFrame frame){
        super("Eligiendo título...");
        setLayout(new BorderLayout());
        name = new JTextField();
        add(name,BorderLayout.CENTER);

        JPanel panel = createSouthPanel();
        add(panel,BorderLayout.SOUTH);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }

    private JPanel createSouthPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        aceptar.addActionListener(new AceptarListener());
        cancelar.addActionListener(new CancelarListener());
        panel.add(aceptar);
        panel.add(cancelar);
        return panel;
    }

    private class AceptarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            Album album;
            if(name.getText().isEmpty()){
                album = new Album("Unnamed");
            }else{
                album = new Album(name.getText());
            }

            d = new DialogoAlbum(album);
            dispose();
        }
    }
    private class CancelarListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
            dispose();
        }
    }

    public static DialogoAlbum getDialogoAlbum(){
        return d;
    }
}
