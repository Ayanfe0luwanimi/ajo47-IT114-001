package Project.client.views;

import javax.swing.*;

import Project.client.Card;
import Project.client.Client;
import Project.client.ICardControls;

import java.awt.*;
import java.io.IOException;
public class CategoryPanel extends JPanel {
    public CategoryPanel(ICardControls controls) {
        super(new BorderLayout(5, 5)); // Border layout with gaps

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS)); // Using a vertical box layout

        JLabel addNewLabel = new JLabel("Select a preferred category");
        addNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
        addNewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(addNewLabel);

        JComboBox<String> categoryDropdown = new JComboBox<>(new String[]{"Category 1", "Category 2"});
        categoryDropdown.setMaximumSize(new Dimension(200, categoryDropdown.getPreferredSize().height)); // Set max size
        categoryDropdown.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(categoryDropdown);

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(e -> {
            String selectedCategory = (String) categoryDropdown.getSelectedItem();
            try {
                Client.INSTANCE.SubmitCategory(selectedCategory);
                JOptionPane.showMessageDialog(this, "Preferred category updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        content.add(submitButton);

        JButton back = new JButton("Go Back");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener((event) -> {
            controls.previous();
        });
        this.add(back, BorderLayout.SOUTH);

        setPreferredSize(new Dimension(400, 300));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(content, BorderLayout.CENTER);
        this.setName(Card.CATEGORY.name());
    }
}
