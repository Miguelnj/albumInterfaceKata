import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Miguel on 09/12/2016.
 */
public class DialogoAlbum extends JDialog {
    private JTextArea albumField;
    private Album album;
    public DialogoAlbum(String name,JFrame parent,Boolean b){
        super(parent,b);
        album = new Album(name);

        setLayout(new BorderLayout());
        albumField = new JTextArea(25, 50);
        add(new JScrollPane(albumField),BorderLayout.CENTER);
        albumField.setEditable(false);
        albumField.setText(album.toString());

        add(createSouthPanel(),BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createSouthPanel(){
        JButton addSong = new JButton("Añadir Canción");
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        JPanel panel = new JPanel(new FlowLayout());

        aceptar.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
        cancelar.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        addSong.addActionListener(e -> {
            DialogoCancion d = new DialogoCancion(DialogoAlbum.this,true);
            Cancion songToAdd = d.getSong();
            if(songToAdd != null){
                album.añadeCanción(d.getSong());
                albumField.setText(album.toString());
            }
        });

        panel.add(addSong);
        panel.add(aceptar);
        panel.add(cancelar);
        return panel;
    }

    public Album getAlbum() {
        return album;
    }
}
