package Project.client.views;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Project.client.Card;
import Project.client.ICardControls;

public class Menu extends JMenuBar {
    public Menu(ICardControls controls) {
        JMenu roomsMenu = new JMenu("Rooms");
        JMenuItem roomsSearch = new JMenuItem("Search");
        JMenu questionMenu = new JMenu("Add new question");
        JMenu category = new JMenu("Category");

                // Creating the "Add new question" menu item
                JMenuItem addNewQuestion = new JMenuItem("Add new question");
                JMenuItem newcategory=new JMenuItem("Configure a category");
        
                // ActionListener for the "Add new question" menu item
                addNewQuestion.addActionListener((event) -> {
                    controls.show(Card.QUESTION.name()); // Invoke the method to show the QUESTION panel
                });

                newcategory.addActionListener((event) -> {
                    controls.show(Card.CATEGORY.name()); // Invoke the method to show the QUESTION panel
                });

                // Add the "Add new question" menu item to the "Add new question" menu
                questionMenu.add(addNewQuestion);
                category.add(newcategory);
        roomsSearch.addActionListener((event) -> {
            controls.show(Card.ROOMS.name());
        });
        roomsMenu.add(roomsSearch);
        this.add(category);
        this.add(questionMenu);
        this.add(roomsMenu);
    }
}
