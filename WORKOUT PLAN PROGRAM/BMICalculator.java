import javax.swing.*;

public class BMICalculator {
    private double height; // Store height in cm
    private double weight; // Store weight in kg
    private double bmi;    // Store calculated BMI

    public void showBMIWindow(JFrame parentFrame) {
        String heightStr = JOptionPane.showInputDialog(parentFrame, "Enter height in centimeters (e.g., 175):");
        String weightStr = JOptionPane.showInputDialog(parentFrame, "Enter weight in kilograms (e.g., 70):");

        try {
            // Parse inputs and calculate BMI
            height = Double.parseDouble(heightStr);
            weight = Double.parseDouble(weightStr);

            if (height <= 0 || weight <= 0) {
                throw new IllegalArgumentException("Height and weight must be positive numbers.");
            }

            double heightMeters = height / 100.0; // Convert height to meters
            bmi = weight / (heightMeters * heightMeters);

            String category;
            if (bmi < 18.5) {
                category = "Underweight";
            } else if (bmi < 24.9) {
                category = "Normal weight";
            } else if (bmi < 29.9) {
                category = "Overweight";
            } else {
                category = "Obesity";
            }

            JOptionPane.showMessageDialog(parentFrame, String.format("Your BMI is: %.2f (%s)", bmi, category));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parentFrame, "Invalid input. Please enter numeric values.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(parentFrame, ex.getMessage());
        }
    }

    // Getter for BMI details
    public String getBMIDetails() {
        return String.format("Height: %.2f cm\nWeight: %.2f kg\nBMI: %.2f\n", height, weight, bmi);
    }
}
