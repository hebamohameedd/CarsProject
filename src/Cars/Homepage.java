package Cars;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Homepage extends JPanel {
    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton howToPlayButton;
    private JButton exitButton;
    private JButton signInButton;
    private Image backgroundImage;
    private String playerName = "";

    public Homepage() {
        backgroundImage = new ImageIcon("src//Assets//HomePage1.png").getImage();
        setLayout(null);

        singlePlayerButton = createStyledButton("Single Player");
        singlePlayerButton.setLocation(300, 150);

        multiPlayerButton = createStyledButton("Multi Player");
        multiPlayerButton.setLocation(300, 225);

        howToPlayButton = createStyledButton("How to Play");
        howToPlayButton.setLocation(300, 300);

        exitButton = createExitButton("Exit");
        exitButton.setLocation(10, 10);

        signInButton = createStyledButton("Sign In");
        signInButton.setSize(100, 40);
        signInButton.setLocation(100, 10);

        add(singlePlayerButton);
        add(multiPlayerButton);
        add(howToPlayButton);
        add(exitButton);
        add(signInButton);
    }

    public void setButtonActions(ActionListener actionListener) {
        singlePlayerButton.addActionListener(actionListener);
        multiPlayerButton.addActionListener(actionListener);
        howToPlayButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
        signInButton.addActionListener(actionListener);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setSize(200, 60);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        return button;
    }

    private JButton createExitButton(String text) {
        JButton button = new JButton(text);
        button.setSize(100, 40);
        button.setOpaque(true);
        button.setBackground(Color.RED);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }

        if (!playerName.isEmpty()) {
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.setColor(Color.WHITE);
            FontMetrics metrics = g.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth("Welcome, " + playerName + "!")) / 2;
            int y = metrics.getAscent() + 10;
            g.drawString("Welcome, " + playerName + "!", x, y);
        }
    }


    public void showSignInDialog() {
        String name = JOptionPane.showInputDialog(this, "Enter your name:", "Sign In", JOptionPane.PLAIN_MESSAGE);

        if (name != null && !name.trim().isEmpty()) {
            playerName = name.trim(); // Update the player's name
            JOptionPane.showMessageDialog(this, "Welcome, " + playerName + "!", "Sign In Successful", JOptionPane.INFORMATION_MESSAGE);
            repaint(); // Redraw the panel to show the updated name
        } else {
            JOptionPane.showMessageDialog(this, "You must enter a name to sign in.", "Sign In Failed", JOptionPane.WARNING_MESSAGE);
        }
    }
}

