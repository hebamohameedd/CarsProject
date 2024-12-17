package Cars;

import javax.swing.*;
import java.awt.*;

public class Howtoplay extends JPanel {
    private Image howToPlayImage;
    private JButton backButton;

    public Howtoplay(CardLayout cardLayout, JPanel contentPanel) {
        howToPlayImage = new ImageIcon("src//Assets//Howtoplay.png").getImage();
        setLayout(null);

        backButton = createStyledButton("Back ");
        backButton.setLocation(20, 500);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "Homepage"));
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (howToPlayImage != null) {
            g.drawImage(howToPlayImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setSize(200, 60);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        return button;
    }
}
