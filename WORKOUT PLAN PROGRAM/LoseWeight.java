import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoseWeight extends JFrame {

    private JTextField currentWeightField;
    private JTextField weightGoalField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> dietTypeComboBox;
    private JTextArea resultArea;
    
    //GUI
    public LoseWeight() {
        // Set up the frame
        setTitle("Lose Weight Planner");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create UI components
        JLabel currentWeightLabel = new JLabel("Enter Current Weight (kg): ");
        currentWeightField = new JTextField(5);

        JLabel weightGoalLabel = new JLabel("Enter Weight Goal (kg): ");
        weightGoalField = new JTextField(5);

        JLabel genderLabel = new JLabel("Select Gender: ");
        String[] genders = {"Male", "Female"};
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.setPreferredSize(new Dimension(100, 20));

        JLabel dietTypeLabel = new JLabel("Select Diet Type: ");
        String[] dietTypes = {"Balanced", "Low-Carb", "Intermittent Fasting"};
        dietTypeComboBox = new JComboBox<>(dietTypes);
        dietTypeComboBox.setPreferredSize(new Dimension(150, 20));

        JButton submitButton = new JButton("Submit");
        resultArea = new JTextArea(20, 50);
        resultArea.setEditable(false);

        // Set up layout
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(currentWeightLabel);
        inputPanel.add(currentWeightField);
        inputPanel.add(weightGoalLabel);
        inputPanel.add(weightGoalField);
        inputPanel.add(genderLabel);
        inputPanel.add(genderComboBox);
        inputPanel.add(dietTypeLabel);
        inputPanel.add(dietTypeComboBox);
        inputPanel.add(new JLabel()); // Spacer
        inputPanel.add(submitButton);

        add(inputPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);

        // Add event listener
        submitButton.addActionListener(new SubmitButtonListener());
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get input values
            String currentWeightText = currentWeightField.getText();
            String weightGoalText = weightGoalField.getText();
            String gender = (String) genderComboBox.getSelectedItem();
            String dietType = (String) dietTypeComboBox.getSelectedItem();

            // Validate inputs
            int currentWeight, weightGoal;
            try {
                currentWeight = Integer.parseInt(currentWeightText);
                weightGoal = Integer.parseInt(weightGoalText);

                if (currentWeight <= 0 || weightGoal <= 0) {
                    throw new NumberFormatException();
                }
                if (weightGoal >= currentWeight) {
                    resultArea.setText("Weight goal must be less than current weight.");
                    return;
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter positive numbers for weights.");
                return;
            }

            // Calculate weight to lose
            int weightToLose = currentWeight - weightGoal;

            // Determine the weight goal category
            String goalCategory = getGoalCategory(weightToLose);

            // Construct the file name based on the user input
            String genderCode = (gender.equals("Male")) ? "M" : "F";
            String dietCode = getDietCode(dietType);

            // Read the plan from file
            String fileName = "Lose Weight Program/" + goalCategory + "," + genderCode + "," + dietCode + ".txt";//filename generated
            String plan = readPlanFromFile(fileName);

            // Display the result
            String result = "Your Lose Weight Plan:\n"
                    + "Current Weight: " + currentWeight + " kg\n"
                    + "Weight Goal: " + weightGoal + " kg\n"
                    + "Weight to Lose: " + weightToLose + " kg\n"
                    + "Gender: " + gender + "\n"
                    + "Diet Type: " + dietType + "\n"
                    + "Plan: \n" + plan;
            resultArea.setText(result);

            // Save the plan to the "saved_plans.txt" file
            savePlanToFile(result);
        }
        
        //getting the code for file name
        private String getGoalCategory(int weightToLose) {
            if (weightToLose <= 10) {
                return "1-10";
            } else if (weightToLose <= 20) {
                return "11-20";
            } else {
                return "20+";
            }
        }
        
        //getting the code for file name
        private String getDietCode(String dietType) {
            switch (dietType) {
                case "Balanced":
                    return "BD";
                case "Low-Carb":
                    return "LCD";
                case "Intermittent Fasting":
                    return "FD";
                default:
                    return "";
            }
        }
        
        //read file
        private String readPlanFromFile(String fileName) {
            StringBuilder plan = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    plan.append(line).append("\n");
                }
            } catch (IOException e) {
                plan.append("Error reading plan file.");
            }
            return plan.toString();
        }
        
        //saved plan
        private void savePlanToFile(String planContent) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("saved_plans.txt", true))) {
                bw.write(planContent);
                bw.newLine();
                bw.write("------------------------------------------------------");
                bw.newLine();
            } catch (IOException ex) {
                resultArea.setText("Error saving plan.");
            }
        }
    }
    
    //main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoseWeight gui = new LoseWeight();
            gui.setVisible(true);
        });
    }
}
