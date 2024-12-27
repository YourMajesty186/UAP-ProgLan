package org.test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ArtifyAppSwing extends JFrame {

    private final JTextField txtTitle;
    private final JTextField txtCategory;
    private final JTextArea txtDescription;
    private final JLabel imgPreview;
    private final DefaultTableModel tableModel;
    private final JTable artTable;
    private File selectedFile;

    public ArtifyAppSwing() {
        setTitle("Artify - Galeri Seni Digital");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Ubah tampilan utama
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 248, 255));
        add(mainPanel);

        // Panel kiri: Form
        JPanel formPanel = new JPanel(new GridLayout(9, 1, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Tambah Karya Baru"));
        formPanel.setBackground(new Color(255, 255, 240));
        mainPanel.add(formPanel, BorderLayout.WEST);

        JLabel lblTitle = new JLabel("Judul:");
        txtTitle = new JTextField();
        JLabel lblCategory = new JLabel("Pelukis:");
        txtCategory = new JTextField();
        JLabel lblDescription = new JLabel("Deskripsi:");
        txtDescription = new JTextArea(5, 20);
        JScrollPane scrollDescription = new JScrollPane(txtDescription);

        JLabel lblImage = new JLabel("Gambar:");
        imgPreview = new JLabel();
        imgPreview.setPreferredSize(new Dimension(150, 150));
        imgPreview.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton btnUpload = new JButton("Unggah Gambar");
        btnUpload.setBackground(new Color(135, 206, 250));
        btnUpload.setForeground(Color.WHITE);
        btnUpload.addActionListener(e -> handleUploadImage());

        JButton btnAdd = new JButton("Tambah Karya");
        btnAdd.setBackground(new Color(60, 179, 113));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.addActionListener(e -> handleAddArt());

        JButton btnDelete = new JButton("Hapus Karya");
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.addActionListener(e -> handleDeleteArt());

        formPanel.add(lblTitle);
        formPanel.add(txtTitle);
        formPanel.add(lblCategory);
        formPanel.add(txtCategory);
        formPanel.add(lblDescription);
        formPanel.add(scrollDescription);
        formPanel.add(lblImage);
        formPanel.add(imgPreview);
        formPanel.add(btnUpload);
        formPanel.add(btnAdd);
        formPanel.add(btnDelete);

        // Panel tengah: Tabel
        String[] columnNames = {"Judul", "Pelukis", "Deskripsi", "Gambar"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 3 ? ImageIcon.class : String.class;
            }
        };

        artTable = new JTable(tableModel);
        artTable.setRowHeight(150);
        artTable.getColumnModel().getColumn(3).setPreferredWidth(150);
        artTable.setBackground(new Color(248, 248, 255));
        artTable.setGridColor(new Color(220, 220, 220));
        JScrollPane tableScroll = new JScrollPane(artTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Galeri Karya Seni"));

        mainPanel.add(tableScroll, BorderLayout.CENTER);

        // Tambahkan header atau banner
        JLabel headerLabel = new JLabel("Selamat Datang di Artify", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(70, 130, 180));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(headerLabel, BorderLayout.NORTH);

        addExampleArt();

        // Pastikan selectedFile diinisialisasi sebagai null
        selectedFile = null; // Inisialisasi null
    }

    // Getter untuk properti
    public JTextField getTxtTitle() {
        return txtTitle;
    }

    public JTextField getTxtCategory() {
        return txtCategory;
    }

    public JTextArea getTxtDescription() {
        return txtDescription;
    }

    public JLabel getImgPreview() {
        return imgPreview;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JTable getArtTable() {
        return artTable;
    }

    // Metode untuk upload gambar, tambah, hapus karya, dan lainnya tetap sama
    public void handleUploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage()
                    .getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            imgPreview.setIcon(imageIcon);
        }
    }

    public void handleAddArt() {
        try {
            String title = txtTitle.getText().trim();
            String category = txtCategory.getText().trim();
            String description = txtDescription.getText().trim();

            if (title.isEmpty() || category.isEmpty() || description.isEmpty() || selectedFile == null) {
                throw new Exception("Semua field harus diisi!");
            }

            // Tambahkan ke tabel
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage()
                    .getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            tableModel.addRow(new Object[]{title, category, description, imageIcon});
            clearFields();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void handleDeleteArt() {
        int selectedRow = artTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang ingin dihapus!", "Error", JOptionPane.WARNING_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus karya ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        }
    }

    public void addExampleArt() {
        try {
            // Gambar contoh diambil dari URL
            String exampleImageUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiSUSDkujHuwAFz3avwpbQoaSlXfwvdqqVoVQUICWkdBu7DORw15tX7T7OLrddg5wox-penNzWNgk-z0Bx3mP83NAyQbTarZlpxSlF2z3zLfOVtKdZJCr3JkOOlnGlXaLaOG29OyG0zTuXQ/w1200-h630-p-k-no-nu/Mona+Lisa+by+Leonardo+da+Vinci.jpg"; // Gantilah dengan URL gambar yang valid
            URL imageUrl = new URL(exampleImageUrl);
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(imageUrl));

            // Menambahkan contoh karya seni ke dalam tabel
            String title = "Mona Lisa";
            String category = "Leonardo Da Vinci";
            String description = "merupakn sebuah lukisan yang dibuat pada jaman renaisance";
            tableModel.addRow(new Object[]{title, category, description, imageIcon});
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat gambar dari URL.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    public void clearFields() {
        txtTitle.setText("");
        txtCategory.setText("");
        txtDescription.setText("");
        imgPreview.setIcon(null);
        selectedFile = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArtifyAppSwing app = new ArtifyAppSwing();
            app.setVisible(true);
        });
    }

    public void setSelectedFile(File selectedFile) {
        this.selectedFile = selectedFile ;

    }
}
