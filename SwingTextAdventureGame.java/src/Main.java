import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class TextAdventureGame {

    private JFrame frame;
    private JTextArea storyTextArea;
    private JButton option1Button, option2Button, quitButton;
    private GameState gameState;

    public TextAdventureGame() {
        gameState = new GameState();

        // Create frame
        frame = new JFrame("Text-Based Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Story text area
        storyTextArea = new JTextArea("Welcome to the Adventure! [You are a knight who has been given a quest to save a village.]");
        storyTextArea.setLineWrap(true);
        storyTextArea.setWrapStyleWord(true);
        storyTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(storyTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(1, 3));
        frame.add(optionsPanel, BorderLayout.SOUTH);

        // Option 1 button
        option1Button = new JButton("Venture into the forest");
        option1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.setCurrentStoryPoint("forest");
                gameState.getPlayer().setHealth(gameState.getPlayer().getHealth() - 20);
                gameState.getPlayer().addItem("Magic Sword");
                updateStoryArea("You venture into the forest and are attacked by goblins! You found a Magic Sword.");
            }
        });
        optionsPanel.add(option1Button);

        // Option 2 button
        option2Button = new JButton("Visit the village");
        option2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameState.setCurrentStoryPoint("village");
                gameState.getPlayer().addItem("Health Potion");
                updateStoryArea("You visit the village and the locals are happy to see a knight. You received a Health Potion.");
            }
        });
        optionsPanel.add(option2Button);

        // Quit button
        quitButton = new JButton("Quit Game");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Quit the game
            }
        });
        optionsPanel.add(quitButton);

        // Set frame visibility (should be the last line in the constructor)
        frame.setVisible(true);
    }

    public void updateStoryArea(String storyUpdate) {
        storyTextArea.setText(storyUpdate + "\n\nYour Health: " + gameState.getPlayer().getHealth() + "\nInventory: " + gameState.getPlayer().getInventory());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextAdventureGame();
            }
        });
    }

    public class Player {
        private int health;
        private ArrayList<String> inventory;

        public Player() {
            health = 100;
            inventory = new ArrayList<>();
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }

        public void addItem(String item) {
            inventory.add(item);
        }

        public ArrayList<String> getInventory() {
            return inventory;
        }
    }

    public class GameState {
        private String currentStoryPoint;
        private Player player;

        public GameState() {
            currentStoryPoint = "start";
            player = new Player();
        }

        public String getCurrentStoryPoint() {
            return currentStoryPoint;
        }

        public void setCurrentStoryPoint(String currentStoryPoint) {
            this.currentStoryPoint = currentStoryPoint;
        }

        public Player getPlayer() {
            return player;
        }
    }
}
