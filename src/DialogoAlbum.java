import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Miguel on 09/12/2016.
 */
public class DialogoAlbum extends JFrame {
    private static Album album;
    private JTextArea albumField;
    public DialogoAlbum(Album album){
        super("Album Interface");
        setLayout(new BorderLayout());
        DialogoAlbum.album = album;
        albumField = new JTextArea(25, 50);
        add(new JScrollPane(albumField),BorderLayout.CENTER);
        albumField.setEditable(false);

        JPanel southPanel = createSouthPanel();
        add(southPanel,BorderLayout.SOUTH);
        albumField.setText(album.toString());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createSouthPanel(){
        JButton addSong = new JButton("Añadir Canción");
        JButton aceptar = new JButton("Aceptar");
        JButton cancelar = new JButton("Cancelar");
        JPanel panel = new JPanel(new FlowLayout());
        addSong.addActionListener(new AddSongButtonListener());
        aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        panel.add(addSong);
        panel.add(aceptar);
        panel.add(cancelar);
        return panel;
    }

    private class AddSongButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new DialogoCancion(DialogoAlbum.this);
        }
    }
    public Album getAlbum(){
        return album;
    }
    public void refreshTextArea(){
        album.toString();
    }
}
