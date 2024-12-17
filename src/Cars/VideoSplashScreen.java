package Cars;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;

public class VideoSplashScreen extends JPanel {
    private JPanel contentPanel;
    private CardLayout cardLayout;
    private Timer timer;
    private JFXPanel jfxPanel; // JavaFX panel for video

    public VideoSplashScreen(JPanel contentPanel, CardLayout cardLayout) {
        this.contentPanel = contentPanel;
        this.cardLayout = cardLayout;

        // Initialize JavaFX panel
        jfxPanel = new JFXPanel();
        jfxPanel.setBounds(0, 0, 800, 600); // Assuming video size is 800x600
        setLayout(null);

        // Add the JavaFX panel to the Swing panel
        add(jfxPanel);

        // Initialize the video
        Platform.runLater(this::initVideo);
    }

    private void initVideo() {
        // Path to the video file (replace with actual path)
        String videoPath = "src//Assets//video.mp4"; // Replace with your actual video path
        Media media = new Media(new java.io.File(videoPath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        // Set volume (optional, ensure it is not 0)
        mediaPlayer.setVolume(1.0); // Maximum volume (range from 0.0 to 1.0)

        // Create a Group to contain the MediaView
        Group root = new Group(mediaView);

        // Create a scene for the media view
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                javafx.scene.Scene scene = new javafx.scene.Scene(root);
                jfxPanel.setScene(scene);
            }
        });

        // Start playing the video
        mediaPlayer.play();

        // Once the video finishes, stop the timer and show the homepage
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                showHomePage();
            }
        });
    }

    private void showHomePage() {
        // Move to the Homepage after the video ends
        cardLayout.show(contentPanel, "Homepage");
    }
}
