package view;

import model.MarsPhoto;
import controllers.Container;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

    public class MarsPhotoViewerFrame extends JFrame {
        private JComboBox<String> comboBox;
        private JTextField textField;
        private JTable table;
        private JButton btnFilter, btnViewImage;
        private Container container;

        public MarsPhotoViewerFrame() throws Exception {
            setTitle("Mars Photos");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);

            container = new Container();

            String[] columnNames = {"ID", "Sol", "Camera", "URL-Image", "Earth Date", "Rover-Name", "Full-name"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            table = new JTable(model);

            JScrollPane scrollPane = new JScrollPane(table);

            comboBox = new JComboBox<>();
            comboBox.addItem("Sequential Filtering");
            comboBox.addItem("Parallel Filtering");

            textField = new JTextField(8);
            btnFilter = new JButton("Filter");
            btnViewImage = new JButton("View Image");

            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

            topPanel.add(comboBox);
            topPanel.add(Box.createRigidArea(new Dimension(0, 3)));
            topPanel.add(textField);
            topPanel.add(Box.createRigidArea(new Dimension(0, 3)));
            topPanel.add(btnFilter);
            topPanel.add(Box.createRigidArea(new Dimension(0, 3)));
            topPanel.add(btnViewImage);

            setLayout(new BorderLayout());
            add(topPanel, BorderLayout.NORTH);
            add(scrollPane, BorderLayout.CENTER);


            btnFilter.addActionListener(e -> {
                try {
                    String filterValue = textField.getText().trim();
                    List<MarsPhoto> photos;

                    switch ((String) comboBox.getSelectedItem()) {
                        case "Sequential Filtering":
                            photos = container.getByDateSequential(container.loadTable(), filterValue);
                            break;
                        case "Parallel Filtering":
                            photos = container.getByDateParallel(filterValue);
                            break;
                        default:
                            photos = container.loadTable();
                            break;
                    }

                    displayPhotos(photos);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });


            btnViewImage.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String imageUrl = (String) table.getValueAt(selectedRow, 3);
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        new MarsPanel(imageUrl); // Just print the image URL
                    } else {
                        JOptionPane.showMessageDialog(this, "Image URL is not available.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a row from the table.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            displayPhotos(container.loadTable());
            setVisible(true);
        }

        private void displayPhotos(List<MarsPhoto> photos) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            for (MarsPhoto photo : photos) {
                model.addRow(new Object[]{
                        photo.getId(),
                        photo.getSol(),
                        photo.getCamera().getName(),
                        photo.getImg_src(),
                        photo.getEarth_date(),
                        photo.getRover().getName(),
                        photo.getCamera().getFull_name()
                });
            }
        }
    }