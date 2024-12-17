package Cars;
import javax.swing.*;
import java.awt.*;

public class CarsGame extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JLayeredPane layeredPane;
    public String gameMode;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarsGame());
    }

    public CarsGame() {
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);

        Homepage homePage = new Homepage();
        Maps mapsPanel = new Maps(this, cardLayout, contentPanel);
        Levels levelsPanel = new Levels(this, cardLayout, contentPanel);
        Howtoplay howToPlayPanel = new Howtoplay(cardLayout, contentPanel);

        homePage.setButtonActions(e -> {
            String command = e.getActionCommand();
            if ("Single Player".equals(command)) {
                gameMode = "Single Player";
                cardLayout.show(contentPanel, "Maps");
            } else if ("Multi Player".equals(command)) {
                gameMode = "Multi Player";
                cardLayout.show(contentPanel, "Maps");
            } else if ("How to Play".equals(command)) {
                cardLayout.show(contentPanel, "Howtoplay");
            } else if ("Exit".equals(command)) {
                dispose();
            } else if ("Sign In".equals(command)) {
                homePage.showSignInDialog();
            }
        });


        contentPanel.add(homePage, "Homepage");
        contentPanel.add(mapsPanel, "Maps");
        contentPanel.add(levelsPanel, "Levels");
        contentPanel.add(howToPlayPanel, "Howtoplay");

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(800, 600));
        contentPanel.setBounds(0, 0, 800, 600);
        layeredPane.add(contentPanel, JLayeredPane.DEFAULT_LAYER);

        setTitle("Cars Racing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        getContentPane().add(layeredPane);
        setVisible(true);
    }
}
