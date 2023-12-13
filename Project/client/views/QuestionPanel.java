package Project.client.views;

import javax.swing.*;

import Project.client.Card;
import Project.client.Client;
import Project.client.ICardControls;

import java.awt.*;
import java.io.IOException;
public class QuestionPanel extends JPanel {
    public QuestionPanel(ICardControls controls) {
        super(new BorderLayout(5, 5)); // Grid layout with 6 rows, 2 columns, and gaps
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel addNewLabel = new JLabel("Add New Question");
        addNewLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set bold font for the label
        addNewLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center align the label
        addNewLabel.setVerticalAlignment(SwingConstants.BOTTOM); // Align label to the bottom
        content.add(addNewLabel); // Add label to the panel

        // UCID: AJO47    DATE: 12/13/2023      
        JLabel categoryLabel = new JLabel("Category:");
        JComboBox<String> categoryDropdown = new JComboBox<>(new String[]{"Category 1", "Category 2"});
        JLabel questionLabel = new JLabel("Question:");
        JTextField questionTextField = new JTextField();

        JLabel optionsLabel = new JLabel("Three options, separated by commas:");
        JTextField optionsTextField = new JTextField();

        JLabel answerLabel = new JLabel("Answer:");
        JTextField answerTextField = new JTextField();
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            if (questionTextField.getText().isEmpty() ||
                optionsTextField.getText().isEmpty() ||
                answerTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Empty Fields", JOptionPane.WARNING_MESSAGE);
            } else {
                String category = (String) categoryDropdown.getSelectedItem();
                String question = questionTextField.getText();
                String options = optionsTextField.getText();
                String answer = answerTextField.getText();
                String newOptions = options.replace(",", "|");
                String newFormattedQuestion = category + ";" + question + ";" + newOptions + ";" + answer;
                try {
                    Client.INSTANCE.savequestion(newFormattedQuestion);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                JOptionPane.showMessageDialog(this, "Question saved successfully to the system", "Success", JOptionPane.INFORMATION_MESSAGE);

                // Optionally, clear the fields after submission
                questionTextField.setText("");
                optionsTextField.setText("");
                answerTextField.setText("");
            }
        });

        JButton back = new JButton("Go Back");
        back.addActionListener((event) -> {
            controls.previous();
        });
        this.add(back, BorderLayout.SOUTH);

        // Add components to the panel
        content.add(categoryLabel);
        content.add(categoryDropdown);
        content.add(questionLabel);
        content.add(questionTextField);
        content.add(optionsLabel);
        content.add(optionsTextField);
        content.add(answerLabel);
        content.add(answerTextField);
        content.add(submitButton);

        // Set panel properties and dimensions if needed
        setPreferredSize(new Dimension(400, 300));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(content, BorderLayout.CENTER);
        this.setName(Card.QUESTION.name());
    }
}
