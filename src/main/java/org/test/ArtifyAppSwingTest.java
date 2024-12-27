package org.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArtifyAppSwingTest {

    private ArtifyAppSwing app;
    private JTextField txtTitle;
    private JTextField txtCategory;
    private JTextArea txtDescription;
    private JLabel imgPreview;
    private DefaultTableModel tableModel;
    private JTable artTable;
    private File selectedFile;

    @BeforeEach
    void setUp() {
        app = new ArtifyAppSwing();
        txtTitle = app.getTxtTitle();
        txtCategory = app.getTxtCategory();
        txtDescription = app.getTxtDescription();
        imgPreview = app.getImgPreview();
        tableModel = app.getTableModel();
        artTable = app.getArtTable();
    }
    @Test
    public void testAddArtSuccess() {
        // Set up input data
        app.getTxtTitle().setText("Art Title");
        app.getTxtCategory().setText("Category");
        app.getTxtDescription().setText("Description of the artwork");
        // Simulate file selection
        app.setSelectedFile(new File("path/to/image.jpg"));

        // Add art
        app.handleAddArt();

        // Verify that the table has 1 row
        assertEquals(1, app.getTableModel().getRowCount());
        // Verify that the data in the first row matches the input
        assertEquals("Art Title", app.getTableModel().getValueAt(0, 0));
        assertEquals("Category", app.getTableModel().getValueAt(0, 1));
        assertEquals("Description of the artwork", app.getTableModel().getValueAt(0, 2));
    }

    @Test
    void testAddArtFailureEmptyTitle() {
        // Kosongkan judul
        txtTitle.setText("");
        txtCategory.setText("Lukisan");
        txtDescription.setText("Deskripsi");

        // Simulasikan pemilihan gambar
        selectedFile = new File("path/to/image.jpg");
        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
        imgPreview.setIcon(imageIcon);

        // Menambahkan karya ke tabel
        app.handleAddArt();

        // Pastikan tabel tidak bertambah barisnya
        assertEquals(0, tableModel.getRowCount());
    }
    @Test
    public void testDeleteArtSuccess() {
        // Set up data
        app.getTxtTitle().setText("Art Title");
        app.getTxtCategory().setText("Category");
        app.getTxtDescription().setText("Description of the artwork");
        app.setSelectedFile(new File("path/to/image.jpg"));

        // Add art
        app.handleAddArt();

        // Verify the row was added
        assertEquals(1, app.getTableModel().getRowCount());

        // Select the row to delete
        app.getArtTable().setRowSelectionInterval(0, 0);

        // Delete art
        app.handleDeleteArt();

        // Verify the row was removed
        assertEquals(0, app.getTableModel().getRowCount());
    }


    @Test
    void testDeleteArtNoSelection() {
        // Cek ketika tidak ada baris yang dipilih untuk dihapus
        app.handleDeleteArt();

        // Pastikan tidak ada perubahan di tabel
        assertEquals(0, tableModel.getRowCount());
    }

    @Test
    void testHandleUploadImage() {
        JFileChooser fileChooser = mock(JFileChooser.class);
        when(fileChooser.getSelectedFile()).thenReturn(new File("path/to/image.jpg"));
        app.handleUploadImage();

        // Verifikasi bahwa gambar berhasil dipilih dan ditampilkan
        assertNotNull(imgPreview.getIcon());
    }

    @Test
    void testClearFields() {
        // Set nilai di form
        txtTitle.setText("Judul Karya");
        txtCategory.setText("Kategori");
        txtDescription.setText("Deskripsi");
        imgPreview.setIcon(new ImageIcon("path/to/image.jpg"));

        // Panggil clearFields
        app.clearFields();

        // Pastikan semua field dikosongkan
        assertEquals("", txtTitle.getText());
        assertEquals("", txtCategory.getText());
        assertEquals("", txtDescription.getText());
        assertNull(imgPreview.getIcon());
    }
}
