import javax.swing.*;
import java.awt.*;
import java.io.*;

public class SavedPlans extends JFrame {

    public SavedPlans() {
        // Set up the frame
        setTitle("Saved Workout Plans");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closes the window but doesn't exit the app
        setLayout(new BorderLayout());

        // Create a JTextArea to display the saved plans
        JTextArea savedPlansArea = new JTextArea();
        savedPlansArea.setEditable(false);  // Disable editing the text area

        // Read the saved plans from the file and display them
        try (BufferedReader br = new BufferedReader(new FileReader("saved_plans.txt"))) {
            String line;
            StringBuilder plans = new StringBuilder();
            while ((line = br.readLine()) != null) {
                plans.append(line).append("\n");
            }
            savedPlansArea.setText(plans.toString());
        } catch (IOException ex) {
            savedPlansArea.setText("No saved plans found or error reading file.");
        }

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(savedPlansArea);
        add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SavedPlans savedPlans = new SavedPlans();
            savedPlans.setVisible(true);
        });
    }
}