package Project.client.views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import Project.client.Card;
import Project.client.Client;
import Project.client.ICardControls;
import Project.client.IGameControls;
import Project.client.IGameEvents;
import Project.common.Cell;
import Project.common.CellType;
import Project.common.Character;
import Project.common.Phase;
import Project.common.TimedEvent;
import Project.common.Character.CharacterType;

public class GamePanel extends JPanel implements IGameEvents, IGameControls {
    private CellPanel[][] cells;
    private JPanel gridPanel;
    private ICardControls controls; // Declare the controls field
    private CardLayout cardLayout;
    String curentquestion;
    private DrawingPanel drawingPanel;
        private String newquestion;
    private   String newcategory ;
    private String newquestionText;
    private List<String> newansweroptions=new ArrayList<>();
    String newanswer;
    private JTextField categoryField;
    private JTextField questionField;
    private JButton answerOptionsField1;
    private JButton answerOptionsField2;
    private JButton answerOptionsField3;
    private JLabel textField; 
    JButton restart;
    JLabel timeLabel = new JLabel("");
    TimedEvent currentTimer;
    String Status;

    public GamePanel(ICardControls controls) {
        super(new CardLayout());
        cardLayout = (CardLayout) this.getLayout();
        this.setName(Card.GAME_SCREEN.name());
        this.controls = controls; 
        Client.INSTANCE.addCallback(this);
        // this is purely for debugging
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                System.out.println("GamePanel Resized to " + e.getComponent().getSize());
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // System.out.println("Moved to " + e.getComponent().getLocation());
            }
        });
        createReadyPanel();
        createOptionsPanel();
        createGameView();
        setVisible(false);
        // don't need to add this to ClientUI as this isn't a primary panel(it's nested
        // in ChatGamePanel)
        // controls.addPanel(Card.GAME_SCREEN.name(), this);
    }

        private void createReadyPanel() {
        JPanel readyPanel = new JPanel();
        JButton readyButton = new JButton();
        readyButton.setText("Ready");
        readyButton.addActionListener(l -> {
            try {
                Client.INSTANCE.sendReadyStatus();
                Client.INSTANCE.getRandomQuestionAndAnswer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        JButton spectator=new JButton();
        spectator.setText("Spectator");
        spectator.addActionListener( l -> {
                Status="Spectator";
                
                try {
                    Client.INSTANCE.sendReadyStatus();
                    Client.INSTANCE.getRandomQuestionAndAnswer();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        );

        JButton away= new JButton();
        away.setText("Away");
        away.addActionListener((event) -> {
            try {
                Client.INSTANCE.sendawaysignal();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "You marked yourself away for the game", "Info", JOptionPane.INFORMATION_MESSAGE);
            controls.show(Card.ROOMS.name());
        });

        readyPanel.add(away);

        readyPanel.add(spectator);
 
        readyPanel.add(readyButton);
        this.add(readyPanel);

        
    }

    private void createOptionsPanel() {
        JPanel characterOptions = new JPanel(new FlowLayout(FlowLayout.CENTER));
        characterOptions.setLayout(new GridLayout(0, 2,20,20)); // Set the layout to a single column

        JLabel question = new JLabel();
        timeLabel=new JLabel();
        characterOptions.add(timeLabel);

    
        currentTimer = new TimedEvent(30, () -> {
            currentTimer = null;
        });
        currentTimer.setTickCallback((time) -> {
            timeLabel.setText("Remaining: " + time);
        });
    

        JLabel blank4=new JLabel();

        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField();
        questionField = new JTextField();
        answerOptionsField1 = new JButton();
        answerOptionsField2 = new JButton();
        answerOptionsField3 = new JButton();
        textField = new JLabel();

        JLabel questionLabel = new JLabel("Question:");

        JLabel answerOptionsLabel = new JLabel("Answer options:");
        JLabel blank=new JLabel();
        answerOptionsField1.addActionListener(l -> {
            if(Status=="Spectator"){
                JOptionPane.showMessageDialog(this, "You cannot make a submission since you are a spectator", "Invalid", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                    Client.INSTANCE.submitANswer(answerOptionsField1.getText()+","+newanswer);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
   

        answerOptionsField2.addActionListener(l -> {
            if(Status=="Spectator"){
                JOptionPane.showMessageDialog(this, "You cannot make a submission since you are a spectator", "Invalid", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                    Client.INSTANCE.submitANswer(answerOptionsField2.getText()+","+newanswer);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        answerOptionsField3.addActionListener(l -> {
            if(Status=="Spectator"){
                JOptionPane.showMessageDialog(this, "You cannot make a submission since you are a spectator", "Invalid", JOptionPane.WARNING_MESSAGE);
            }
            else{
                try {
                    Client.INSTANCE.submitANswer(answerOptionsField3.getText()+","+newanswer);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        JLabel blank2=new JLabel();
        JLabel blank3=new JLabel();
        JLabel textField=new JLabel();


    JLabel scoresLabel = new JLabel("Scores:");
    characterOptions.add(scoresLabel);

    

  

    characterOptions.add(scoresLabel);

        characterOptions.add(question);
        characterOptions.add(blank4);
        characterOptions.add(categoryLabel);
        characterOptions.add(categoryField);
        characterOptions.add(questionLabel);
        characterOptions.add(questionField);
        characterOptions.add(answerOptionsLabel);
        characterOptions.add(blank3);
        characterOptions.add(answerOptionsField1);
        characterOptions.add(answerOptionsField2);
        characterOptions.add(answerOptionsField3);
        characterOptions.add(blank2);
        characterOptions.add(textField);

        
        characterOptions.add(blank);
        



        add(characterOptions);
    }

    private void createGameView() {
        gridPanel = new JPanel(new BorderLayout());
    
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
    
        restart = new JButton();
        restart.setText("RESTART");
        restart.addActionListener(l -> {
            try {
                Client.INSTANCE.sendRestart();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(restart);
    
        gridPanel.add(gameOverLabel, BorderLayout.CENTER);
        gridPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        add(gridPanel);
    }
    



    private void resetView() {
        if (gridPanel == null) {
            return;
        }
        if (gridPanel.getLayout() != null) {
            gridPanel.setLayout(null);
        }
        cells = null;
        gridPanel.removeAll();
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void makeGrid(int rows, int columns) {
        resetView();
        cells = new CellPanel[rows][columns];
        gridPanel.setLayout(new GridLayout(rows, columns));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = new CellPanel(this);
                cells[i][j].setType(CellType.NONE, i, j, false);
                gridPanel.add(cells[i][j]);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    @Override
    public void onClientConnect(long id, String clientName, String message) {
    }

    @Override
    public void onClientDisconnect(long id, String clientName, String message) {
    }

    @Override
    public void onMessageReceive(long id, String message) {
    }

    @Override
    public void onReceiveClientId(long id) {
    }

    @Override
    public void onSyncClient(long id, String clientName) {
    }

    @Override
    public void onResetUserList() {
    }

    @Override
    public void onReceiveRoomList(String[] rooms, String message) {
    }

    @Override
    public void onRoomJoin(String roomName) {
    }

    @Override
    public void onReceivePhase(Phase phase) {
        // I'll temporarily do next(), but there may be scenarios where the screen can
        // be inaccurate
        System.out.println("Received phase: " + phase.name());
        if (phase == Phase.READY) {
            if (!isVisible()) {
                setVisible(true);
                this.getParent().revalidate();
                this.getParent().repaint();
                System.out.println("GamePanel visible");
            } else {
                cardLayout.next(this);
            }
        } else if (phase == Phase.SELECTION) {
            cardLayout.next(this);
        } else if (phase == Phase.PREPARING) {
            cardLayout.next(this);
        }
    }

    private void updateUIComponents() {
        categoryField.setText(newcategory);
        questionField.setText(newquestionText);
        answerOptionsField1.setText(newansweroptions.get(0));
        answerOptionsField2.setText(newansweroptions.get(1));
        answerOptionsField3.setText(newansweroptions.get(2));
        textField.setText(newquestion);
    }


    @Override
    public void onReceiveReady(long clientId) {
    }

    @Override
    public void onReceiveCell(List<Cell> cells) {
        for (Cell c : cells) {
            CellPanel target = this.cells[c.getX()][c.getY()];
            target.setType(c.getCellType(), c.getX(), c.getY(), c.isBlocked());
            target.setOccupiedCount(c.getCharactersInCell().size());
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    @Override
    public void onReceiveGrid(int rows, int columns) {
        resetView();
        if (rows > 0 && columns > 0) {
            makeGrid(rows, columns);
        }
    }

    @Override
    public void onReceiveCharacter(long clientId, Character character) {
        // kind of a sideways way of interacting with the ChatPanel, will likely
        // refactor this later
        ChatGamePanel cgp = (ChatGamePanel) this.getParent().getParent();
        cgp.getChatPanel().addText(Client.INSTANCE.getClientNameById(clientId) + " summoned " + character.getName());
    }

    @Override
    public void onClickCell(int x, int y) {
        CellPanel cell = cells[x][y];
        int c = cell.getOccupiedCount();
        drawingPanel.setNumberOfCharacters(c);
        drawingPanel.setCellType(cell.getCellType());
    }

      @Override
    //ucid:ajo47
    //date:  12/12/2023
    //This is the logic for the server showing a random categry and a random question
    public void onQuestionReceive(String message) {
        curentquestion = message;
            String[] objects = message.split("-");
            if (objects.length > 1) {
                List<String> objectList = Arrays.asList(objects);
                Random random = new Random();
                int randomIndex = random.nextInt(objectList.size());
                newquestion = objectList.get(randomIndex).trim();
                String[] parts = newquestion.split(";");
                if (parts.length == 4) {
                    newcategory = parts[0];  // Assign first part to newcategory
                    newquestionText = parts[1];  // Assign second part to newquestionText
                    String[] answerOptions = parts[2].split("\\|");  // Split the third part by '|'
                    newansweroptions = Arrays.asList(answerOptions);  // Put it in a list called newansweroptions
                    newanswer = parts[3];  // Assign fourth part to newanswer
            }
            answerOptionsField2.setText(newansweroptions.get(1));
            updateUIComponents(); 
        }
    }
}
