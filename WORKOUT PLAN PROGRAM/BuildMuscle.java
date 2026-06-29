import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class BuildMuscle {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Workout Suggestion");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window
        frame.setSize(600, 450);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        JLabel selectLabel = new JLabel("Select Workout Type:");
        selectLabel.setBounds(10, 20, 180, 25);
        panel.add(selectLabel);

        JRadioButton bodyWeightButton = new JRadioButton("Bodyweight");
        bodyWeightButton.setBounds(190, 20, 100, 25);
        panel.add(bodyWeightButton);

        JRadioButton dumbbellButton = new JRadioButton("Dumbbell");
        dumbbellButton.setBounds(290, 20, 100, 25);
        panel.add(dumbbellButton);

        ButtonGroup workoutGroup = new ButtonGroup();
        workoutGroup.add(bodyWeightButton);
        workoutGroup.add(dumbbellButton);

        JLabel intensityLabel = new JLabel("Select Workout Intensity:");
        intensityLabel.setBounds(10, 60, 180, 25);
        panel.add(intensityLabel);

        JRadioButton lowIntensityButton = new JRadioButton("Low");
        lowIntensityButton.setBounds(190, 60, 100, 25);
        panel.add(lowIntensityButton);

        JRadioButton moderateIntensityButton = new JRadioButton("Moderate");
        moderateIntensityButton.setBounds(290, 60, 100, 25);
        panel.add(moderateIntensityButton);

        JRadioButton highIntensityButton = new JRadioButton("High");
        highIntensityButton.setBounds(390, 60, 100, 25);
        panel.add(highIntensityButton);

        ButtonGroup intensityGroup = new ButtonGroup();
        intensityGroup.add(lowIntensityButton);
        intensityGroup.add(moderateIntensityButton);
        intensityGroup.add(highIntensityButton);

        JLabel daySelectionLabel = new JLabel("Select Workout Days:");
        daySelectionLabel.setBounds(10, 100, 180, 25);
        panel.add(daySelectionLabel);

        JCheckBox mondayCheckBox = new JCheckBox("Monday");
        mondayCheckBox.setBounds(10, 130, 100, 25);
        panel.add(mondayCheckBox);

        JCheckBox tuesdayCheckBox = new JCheckBox("Tuesday");
        tuesdayCheckBox.setBounds(120, 130, 100, 25);
        panel.add(tuesdayCheckBox);

        JCheckBox wednesdayCheckBox = new JCheckBox("Wednesday");
        wednesdayCheckBox.setBounds(230, 130, 120, 25);
        panel.add(wednesdayCheckBox);

        JCheckBox thursdayCheckBox = new JCheckBox("Thursday");
        thursdayCheckBox.setBounds(350, 130, 100, 25);
        panel.add(thursdayCheckBox);

        JCheckBox fridayCheckBox = new JCheckBox("Friday");
        fridayCheckBox.setBounds(10, 160, 100, 25);
        panel.add(fridayCheckBox);

        JCheckBox saturdayCheckBox = new JCheckBox("Saturday");
        saturdayCheckBox.setBounds(120, 160, 100, 25);
        panel.add(saturdayCheckBox);

        JCheckBox sundayCheckBox = new JCheckBox("Sunday");
        sundayCheckBox.setBounds(230, 160, 100, 25);
        panel.add(sundayCheckBox);

        JButton submitButton = new JButton("Get Suggestion");
        submitButton.setBounds(10, 200, 200, 25);
        panel.add(submitButton);

        // Create a scrollable suggestion area
        JLabel suggestionLabel = new JLabel("<html>Suggested Workout: <br></html>");
        suggestionLabel.setVerticalAlignment(JLabel.TOP); // Align the content to the top
        JScrollPane scrollPane = new JScrollPane(suggestionLabel);
        scrollPane.setBounds(10, 240, 550, 150); // Adjust the size to fit the panel
        panel.add(scrollPane);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    suggestionLabel.setText("<html>Suggested Workout: <br></html>\n"); // Clear previous suggestion

                    // **Validation for Workout Type**
                    String workoutType;
                    if (bodyWeightButton.isSelected()) {
                        workoutType = "Bodyweight";
                    } else if (dumbbellButton.isSelected()) {
                        workoutType = "Dumbbell";
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a workout type!", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the method if no type is selected
                    }

                    // **Validation for Workout Intensity**
                    String intensity;
                    if (lowIntensityButton.isSelected()) {
                        intensity = "L";
                    } else if (moderateIntensityButton.isSelected()) {
                        intensity = "M";
                    } else if (highIntensityButton.isSelected()) {
                        intensity = "H";
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a workout intensity!", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the method if no intensity is selected
                    }

                    // **Validation for Workout Days**
                    boolean[] selectedDays = {
                        mondayCheckBox.isSelected(),
                        tuesdayCheckBox.isSelected(),
                        wednesdayCheckBox.isSelected(),
                        thursdayCheckBox.isSelected(),
                        fridayCheckBox.isSelected(),
                        saturdayCheckBox.isSelected(),
                        sundayCheckBox.isSelected()
                    };

                    boolean atLeastOneDaySelected = false;
                    for (boolean day : selectedDays) {
                        if (day) {
                            atLeastOneDaySelected = true;
                            break;
                        }
                    }

                    if (!atLeastOneDaySelected) {
                        JOptionPane.showMessageDialog(null, "Please select at least one workout day!", "Error", JOptionPane.ERROR_MESSAGE);
                        return; // Exit the method if no days are selected
                    }

                    String[] dayNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                    String fileName = "Build Muscle Program/" + workoutType + "," + intensity + ".txt";
                    String workoutPlan = readPlanForSelectedDays(fileName, selectedDays, dayNames, workoutType, intensity);

                    // Display workout plan
                    suggestionLabel.setText("<html>Suggested Workout: <br>" + workoutPlan.replace("\n", "<br>") + "</html>");

                    // Save the workout plan to saved_plans.txt
                    savePlanToFile(workoutPlan);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            private String readPlanForSelectedDays(String fileName, boolean[] selectedDays, String[] dayNames, String workoutType, String intensity) {
                StringBuilder plan = new StringBuilder();
                boolean includeDay = false;
                String currentDay = "";

                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        for (int i = 0; i < dayNames.length; i++) {
                            if (line.trim().equalsIgnoreCase(dayNames[i])) {
                                includeDay = selectedDays[i];
                                if (includeDay && !line.equalsIgnoreCase(currentDay)) {
                                    if (plan.length() == 0) {
                                        plan.append(workoutType).append(" ").append(getIntensityName(intensity)).append(" Workout\n")
                                            .append("========================================\n\n");
                                    }
                                    plan.append("---------------------------------\n");
                                    plan.append("              ").append(dayNames[i]).append("\n");
                                    currentDay = line.trim();
                                }
                                break;
                            }
                        }

                        if (includeDay && !line.trim().equalsIgnoreCase(currentDay) && !line.trim().isEmpty()) {
                            plan.append(line.trim()).append("\n");
                        }
                    }

                    if (plan.length() == 0) {
                        plan.append("No workout plan found for the selected days.");
                    } else {
                        plan.append("========================================");
                    }
                } catch (IOException ex) {
                    plan.append("Error reading file or file not found: ").append(fileName);
                }

                return plan.toString();
            }

            private String getIntensityName(String intensityCode) {
                switch (intensityCode) {
                    case "L": return "Low-Intensity";
                    case "M": return "Moderate-Intensity";
                    case "H": return "High-Intensity";
                    default: return "Unknown";
                }
            }

            private void savePlanToFile(String planContent) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("saved_plans.txt", true))) {
                    writer.write(planContent);
                    writer.write("\n------------------------------------------------------\n");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving the plan to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
