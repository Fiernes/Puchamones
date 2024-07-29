import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class prueba extends JPanel {
    private JTextField nameField;
    private JLabel selectedImageLabel;
    private String selectedImagePath;
    private JLabel lastSelectedLabel;

    public prueba() {
        // Configuración del layout
        setLayout(new BorderLayout());

        // Panel para el campo de texto
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);
        add(inputPanel, BorderLayout.NORTH);

        // Panel para las imágenes
        JPanel imagesPanel = new JPanel();
        imagesPanel.setLayout(new GridLayout(0, 3));

        File imagesDir = new File("src/imagenes");
        if (imagesDir.isDirectory()) {
            for (File file : imagesDir.listFiles()) {
                if (file.isFile() && isImageFile(file)) {
                    try {
                        BufferedImage img = ImageIO.read(file);
                        ImageIcon icon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
                        JLabel imageLabel = new JLabel(icon);
                        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        imageLabel.addMouseListener(new MouseAdapter() {
                            public void mousePressed(MouseEvent evt) {

                                if (lastSelectedLabel != null) {
                                    lastSelectedLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                }

                                selectedImagePath = file.getAbsolutePath();
                                selectedImageLabel.setIcon(icon);

                                imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                                lastSelectedLabel = imageLabel;
                            }
                        });
                        imagesPanel.add(imageLabel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        add(new JScrollPane(imagesPanel), BorderLayout.CENTER);

        // Panel para la imagen seleccionada y el botón
        JPanel selectionPanel = new JPanel();
        selectedImageLabel = new JLabel();
        selectionPanel.add(selectedImageLabel);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (selectedImagePath != null && !name.isEmpty()) {
                    // Acción cuando se selecciona una imagen y se ingresa el nombre
                    System.out.println("Nombre: " + name + ", Imagen: " + selectedImagePath);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, ingresa un nombre y selecciona una imagen.");
                }
            }
        });
        selectionPanel.add(confirmButton);
        add(selectionPanel, BorderLayout.SOUTH);
    }

    private boolean isImageFile(File file) {
        String[] validExtensions = {"jpg", "jpeg", "png", "gif"};
        String fileName = file.getName().toLowerCase();
        for (String ext : validExtensions) {
            if (fileName.endsWith(ext)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Selecciona una imagen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new prueba());
        frame.setVisible(true);
    }
}
