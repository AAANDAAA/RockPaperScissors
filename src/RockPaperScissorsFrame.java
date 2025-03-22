import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class RockPaperScissorsFrame extends JFrame {
    private int playerWins = 0, computerWins = 0, ties = 0;  // Track wins and ties
    private JTextArea resultArea;  // Text area to display game results
    private JTextField playerWinsField, computerWinsField, tiesField;  // Text fields to show stats


    public RockPaperScissorsFrame() {
        // Set up the main window properties
        setTitle("Rock Paper Scissors Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit the game when closing window
        setSize(500, 500);  // Set window size
        setLocationRelativeTo(null);  // Center the window on the screen

        // Control panel for the buttons (Rock, Paper, Scissors, Quit)
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("Choose Gesture"));
        controlPanel.setLayout(new GridBagLayout());  // Use GridBagLayout for flexible components

        JButton rockButton = createButton("Rock", "rock.png");
        JButton paperButton = createButton("Paper", "paper.png");
        JButton scissorsButton = createButton("Scissors", "scissors.png");
        JButton quitButton = new JButton("Quit");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        controlPanel.add(rockButton, gbc);

        gbc.gridy = 1;
        controlPanel.add(paperButton, gbc);

        gbc.gridy = 2;
        controlPanel.add(scissorsButton, gbc);

        gbc.gridy = 3;
        controlPanel.add(quitButton, gbc);

        quitButton.addActionListener(e -> System.exit(0));

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(3, 2));  // 3 rows and 2 columns

        statsPanel.add(new JLabel("Player Wins:"));
        playerWinsField = new JTextField("0", 5);
        playerWinsField.setEditable(false);  // Make text field non-editable
        statsPanel.add(playerWinsField);

        statsPanel.add(new JLabel("Computer Wins:"));
        computerWinsField = new JTextField("0", 5);
        computerWinsField.setEditable(false);
        statsPanel.add(computerWinsField);

        statsPanel.add(new JLabel("Ties:"));
        tiesField = new JTextField("0", 5);
        tiesField.setEditable(false);
        statsPanel.add(tiesField);

        // Results area where the game results will be displayed
        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.CENTER);  // Add buttons to the center
        add(statsPanel, BorderLayout.NORTH);  // Add stats to the top
        add(scrollPane, BorderLayout.SOUTH);  // Add results area to the bottom
    }


    private JButton createButton(String text, String iconName) {
        JButton button = new JButton(text);  // Create the button with text
        ImageIcon icon = new ImageIcon(getClass().getResource(iconName));

        // Get the image from the icon and resize it to a specific size
        Image image = icon.getImage(); // Transform the ImageIcon into an Image
        Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // Resize to 50x50 pixels

        // Set the resized image as the icon for the button
        button.setIcon(new ImageIcon(resizedImage));

        // Add action listener to the button to play the game when clicked
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playGame(text);  // Call playGame() when the button is clicked
            }
        });

        return button;
    }


    private void playGame(String playerMove) {
        // Computer's move is chosen randomly from the 3 possible moves
        String[] moves = {"Rock", "Paper", "Scissors"};
        String computerMove = moves[(int) (Math.random() * 3)];

        // Determine the result of the game (who wins or if it's a tie)
        String result = determineWinner(playerMove, computerMove);

        // Update win/tie counts based on the result
        if (result.contains("Player")) playerWins++;
        else if (result.contains("Computer")) computerWins++;
        else ties++;

        // Update the stats on the screen
        playerWinsField.setText(String.valueOf(playerWins));
        computerWinsField.setText(String.valueOf(computerWins));
        tiesField.setText(String.valueOf(ties));

        // Append the result to the results area
        resultArea.append(result + "\n");
    }


    private String determineWinner(String playerMove, String computerMove) {
        // If both moves are the same, it's a tie
        if (playerMove.equals(computerMove)) return playerMove + " vs " + computerMove + " (Tie)";
        // Check the conditions for player winning
        if (playerMove.equals("Rock") && computerMove.equals("Scissors")) return "Rock breaks Scissors (Player wins)";
        if (playerMove.equals("Paper") && computerMove.equals("Rock")) return "Paper covers Rock (Player wins)";
        if (playerMove.equals("Scissors") && computerMove.equals("Paper")) return "Scissors cuts Paper (Player wins)";
        // If the player didn't win, then the computer must have won
        return computerMove + " beats " + playerMove + " (Computer wins)";
    }

}

