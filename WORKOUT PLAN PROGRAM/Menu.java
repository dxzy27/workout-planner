import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        setTitle("Workout Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame

        // Custom panel with a background image
        JPanel mainPanel = new JPanel() {
            private Image backgroundImage = new ImageIcon("image1.jpg").getImage();

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel("Workout Planner");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE); // Adjust the color to contrast the background
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Top button panel
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false); // Make the panel transparent to show the background
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        // Create buttons
        JButton bmiButton = new JButton("Calculate BMI");
        JButton loseWeightButton = new JButton("Lose Weight Program");
        JButton buildMuscleButton = new JButton("Build Muscle");
        JButton savedPlansButton = new JButton("Saved Plans");

        // Add action listeners
        bmiButton.addActionListener(e -> {
            BMICalculator bmiCalculator = new BMICalculator();
            bmiCalculator.showBMIWindow(Menu.this);
        });

        loseWeightButton.addActionListener(e -> {
            LoseWeight loseWeight = new LoseWeight();
            loseWeight.setVisible(true);
        });

        buildMuscleButton.addActionListener(e -> BuildMuscle.main(null));

        savedPlansButton.addActionListener(e -> {
            SavedPlans savedPlans = new SavedPlans();
            savedPlans.setVisible(true);
        });

        // Add buttons to the top panel
        topPanel.add(bmiButton);
        topPanel.add(buildMuscleButton);
        topPanel.add(loseWeightButton);

        // Components to the main panel
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(topPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Saved Plans button separately
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false); // Transparent
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(savedPlansButton);

        mainPanel.add(bottomPanel);

        // Add main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Menu());
    }
}
