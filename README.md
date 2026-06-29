# 🏋️‍♂️ Workout Planner Desktop Application

A Java Swing-based desktop application designed to help users calculate their BMI, generate tailored muscle-building plans, create structured weight-loss diet and fitness guides, and save their custom fitness regimes.

This project is fully configured for the **BlueJ IDE** and can also be compiled and executed directly from the terminal.

---

## ✨ Key Features

*   **📊 BMI Calculator:** Quickly compute Body Mass Index (BMI) using height (cm) and weight (kg) with instant classification (Underweight, Normal, Overweight, Obese).
*   **💪 Muscle-Building Routine Generator:** Create daily schedules selecting between **Bodyweight** or **Dumbbell** exercises across **Low**, **Moderate**, or **High** intensity levels on custom selected days.
*   **🥗 Tailored Weight Loss Planner:** Generate structured workout and diet plans (Balanced, Low-Carb, Intermittent Fasting) adjusted for target weights and gender.
*   **💾 Saved Plans Tracker:** Keep track of all generated plans. The app appends selections to a local text file and displays them in a built-in viewer.
*   **🖼️ Rich Swing GUI:** A custom background graphical interface with image support, scrolling views, and dialog boxes.

---

## 📂 Project Directory Structure

```text
WORKOUT PLAN PROGRAM/
│
├── Build Muscle Program/            # Workout database for building muscle
│   ├── Bodyweight,L.txt             # Bodyweight - Low Intensity plans
│   ├── Bodyweight,M.txt             # Bodyweight - Moderate Intensity plans
│   ├── Bodyweight,H.txt             # Bodyweight - High Intensity plans
│   ├── Dumbbell,L.txt               # Dumbbell - Low Intensity plans
│   ├── Dumbbell,M.txt               # Dumbbell - Moderate Intensity plans
│   └── Dumbbell,H.txt               # Dumbbell - High Intensity plans
│
├── Lose Weight Program/             # Diet & fitness database for weight loss
│   ├── 1-10,F,BD.txt                # 1-10kg to lose, Female, Balanced Diet plan
│   ├── 1-10,M,BD.txt                # 1-10kg to lose, Male, Balanced Diet plan
│   ├── 11-20,F,LCD.txt              # 11-20kg to lose, Female, Low-Carb Diet plan
│   └── ... (Includes 18 configuration variations of weight, gender, and diets)
│
├── BMICalculator.java               # Calculates BMI and classifies weight status
├── BuildMuscle.java                 # Interactive GUI for generating muscle building routines
├── LoseWeight.java                  # Interactive GUI for diet and weight loss planner
├── Menu.java                        # Main dashboard and launcher window
├── SavedPlans.java                  # GUI to read and display saved_plans.txt
│
├── package.bluej                    # BlueJ Project Configuration File
├── image1.jpg                       # Background image for the Main Menu
├── saved_plans.txt                  # User's exported workout & diet plans
└── README.md                        # Documentation (This file)
```

---

## ⚙️ Prerequisites

To run this application, make sure you have the following installed:
*   **Java Development Kit (JDK 8 or higher)**. You can download it from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use [OpenJDK](https://openjdk.org/).
*   *(Optional)* **BlueJ IDE** (highly recommended for academic or quick testing purposes). Download it from [BlueJ's official website](https://bluej.org/).

---

## 🚀 How to Run the Application

### Option 1: Using BlueJ IDE (Recommended)
1. Launch **BlueJ**.
2. Go to **Project** -> **Open Project...** and select the `WORKOUT PLAN PROGRAM` folder.
3. BlueJ will automatically detect the classes and load the dependency diagram.
4. Right-click the `Menu` class box.
5. Select `void main(String[] args)`.
6. Click **OK** on the parameter popup window to launch the GUI.

## 🔍 Detailed Feature Overview

### 1. Main Menu
*   Class: [Menu.java](./Menu.java)
*   The primary control center displaying a styled background (`image1.jpg`) and options to access the BMI Calculator, Muscle Building program, Lose Weight program, or view your saved plans.

### 2. BMI Calculator
*   Class: [BMICalculator.java](./BMICalculator.java)
*   Prompts the user for height in cm and weight in kg. 
*   Computes: $\text{BMI} = \frac{\text{Weight (kg)}}{\left(\text{Height (m)}\right)^2}$
*   Outputs a pop-up showing the calculated value and health category:
    *   `< 18.5`: Underweight
    *   `18.5 - 24.8`: Normal weight
    *   `24.9 - 29.8`: Overweight
    *   `>= 29.9`: Obesity

### 3. Build Muscle Program
*   Class: [BuildMuscle.java](./BuildMuscle.java)
*   **Inputs:** Type (Bodyweight/Dumbbell), Intensity (Low/Moderate/High), and specific workout days (Monday through Sunday).
*   **Action:** Reads exercise data dynamically from the matching text file under the `Build Muscle Program/` directory and filters sections matching the user's selected days.
*   **Export:** Appends the suggested plan with a header to [saved_plans.txt](./saved_plans.txt).

### 4. Lose Weight Program
*   Class: [LoseWeight.java](./LoseWeight.java)
*   **Inputs:** Current weight, Target weight, Gender, and Diet Type (Balanced, Low-Carb, or Intermittent Fasting).
*   **Action:** Validates weight inputs, determines target weight loss category (`1-10kg`, `11-20kg`, or `20+kg`), and loads the appropriate text template from `Lose Weight Program/`.
*   **Export:** Appends the generated diet details and weight tracker log to [saved_plans.txt](./saved_plans.txt).

### 5. Saved Plans Viewer
*   Class: [SavedPlans.java](./SavedPlans.java)
*   Opens a scrollable display frame that loads all exported routines and diet recommendations directly from [saved_plans.txt](./saved_plans.txt), allowing users to review their custom history.

---

## 💾 Data Schema & File Generation

The weight loss and muscle building plans are retrieved based on naming conventions:

### Muscle Building File Naming Format:
`Build Muscle Program/{WorkoutType},{IntensityCode}.txt`
*   `WorkoutType`: `Bodyweight` or `Dumbbell`
*   `IntensityCode`: `L` (Low), `M` (Moderate), `H` (High)

### Weight Loss File Naming Format:
`Lose Weight Program/{GoalCategory},{GenderCode},{DietCode}.txt`
*   `GoalCategory`: `1-10` (if weight difference is 1-10kg), `11-20` (if 11-20kg), `20+` (if >20kg)
*   `GenderCode`: `M` (Male) or `F` (Female)
*   `DietCode`: `BD` (Balanced), `LCD` (Low-Carb), `FD` (Intermittent Fasting)
