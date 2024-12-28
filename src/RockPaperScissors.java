import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.Border;

public class RockPaperScissors {
    private final static String ROCK = "Rock";
    private final static String PAPER = "Paper";
    private final static String SCISSORS = "Scissors";
    private final static String WIN_MESSAGE = "You win!";
    private final static String LOSE_MESSAGE = "CPU wins!";
    private final static String TIE_MESSAGE = "You tied!";

    private String playerChoice = "";
    private String cpuChoice = "";

    // cpu's action will be random
    Random random = new Random();

    // one button for each playable option
    JButton rockButton = new JButton(RockPaperScissors.ROCK);
    JButton paperButton = new JButton(RockPaperScissors.PAPER);
    JButton scissorsButton = new JButton(RockPaperScissors.SCISSORS);
    JButton resetButton = new JButton("Reset");
    JPanel buttonPanel = new JPanel();

    // create window to play game
    int boardWidth = 400;
    int boardHeight = 400;
    int choiceWidth = 80;
    int choiceHeight = 80;
    JFrame frame = new JFrame("Rock Paper Scissors");
    JPanel gamePanel = new JPanel() {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Image playerImage = null;
            String playerImagePath = "";
            Image cpuImage = null;
            String cpuImagePath = "";

            // show player and cpu choices
            try {
                if (playerChoice != "") {
                    switch (playerChoice) {
                        case RockPaperScissors.ROCK:
                            playerImagePath = "./Images/rock.png";
                            break;
                        case RockPaperScissors.PAPER:
                            playerImagePath = "./Images/paper.png";
                            break;
                        case RockPaperScissors.SCISSORS:
                            playerImagePath = "./Images/scissors.png";
                            break;
                        default:
                            break;
                    }
                    playerImage = new ImageIcon(getClass().getResource(playerImagePath)).getImage();
                }

                if (cpuChoice != "") {
                    switch (cpuChoice) {
                        case RockPaperScissors.ROCK:
                            cpuImagePath = "./Images/rock.png";
                            break;
                        case RockPaperScissors.PAPER:
                            cpuImagePath = "./Images/paper.png";
                            break;
                        case RockPaperScissors.SCISSORS:
                            cpuImagePath = "./Images/scissors.png";
                            break;
                        default:
                            break;
                    }
                    cpuImage = new ImageIcon(getClass().getResource(cpuImagePath)).getImage();
                }

                g.drawImage(playerImage, 100, 100, choiceWidth, choiceHeight, null);
                g.drawImage(cpuImage, 200, 100, choiceWidth, choiceHeight, null);

                // print out result message
                String message = determineWinner(playerChoice, cpuChoice);
                g.setFont(new Font("Arial", Font.PLAIN, 30));
                g.setColor(Color.blue);
                g.drawString(message, 120, 250);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    RockPaperScissors() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel.setLayout(new BorderLayout());
        gamePanel.setBackground(new Color(255, 255, 255));
        frame.add(gamePanel);

        rockButton.setFocusable(false);
        buttonPanel.add(rockButton);
        paperButton.setFocusable(false);
        buttonPanel.add(paperButton);
        scissorsButton.setFocusable(false);
        buttonPanel.add(scissorsButton);
        resetButton.setFocusable(false);
        resetButton.setVisible(false);
        buttonPanel.add(resetButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        rockButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerChoice = ROCK;

                // randomize cpu choice
                makeCPUChoice();
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);
                scissorsButton.setEnabled(false);
                resetButton.setEnabled(true);
                resetButton.setVisible(true);
                gamePanel.repaint();
            }
        });

        paperButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerChoice = PAPER;

                makeCPUChoice();
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);
                scissorsButton.setEnabled(false);
                resetButton.setEnabled(true);
                resetButton.setVisible(true);
                gamePanel.repaint();
            }
        });

        scissorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerChoice = SCISSORS;

                makeCPUChoice();
                rockButton.setEnabled(false);
                paperButton.setEnabled(false);
                scissorsButton.setEnabled(false);
                resetButton.setEnabled(true);
                resetButton.setVisible(true);
                gamePanel.repaint();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
    }

    private String determineWinner(String playerPick, String cpuPick) {
        if (playerPick == "" || cpuPick == "")
            return "";
        if (playerPick == cpuPick)
            return TIE_MESSAGE;
        else if (playerPick == ROCK) {
            if (cpuPick == SCISSORS)
                return WIN_MESSAGE;
            else
                return LOSE_MESSAGE;
        } else if (playerPick == PAPER) {
            if (cpuPick == ROCK)
                return WIN_MESSAGE;
            else
                return LOSE_MESSAGE;
        } else if (playerPick == SCISSORS) {
            if (cpuPick == PAPER)
                return WIN_MESSAGE;
            else
                return LOSE_MESSAGE;
        }
        return "";
    }

    public void makeCPUChoice() {
        int choice = random.nextInt(3);
        if (choice == 0)
            cpuChoice = ROCK;
        else if (choice == 1)
            cpuChoice = PAPER;
        else
            cpuChoice = SCISSORS;
    }

    public void resetGame() {
        playerChoice = "";
        cpuChoice = "";
        resetButton.setVisible(false);
        resetButton.setEnabled(false);
        rockButton.setEnabled(true);
        paperButton.setEnabled(true);
        scissorsButton.setEnabled(true);
        gamePanel.repaint();
    }
}
