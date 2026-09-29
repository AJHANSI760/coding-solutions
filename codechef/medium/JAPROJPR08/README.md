# JAPROJPR08

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Handle user choice

Now, the user is expected to input his desired operation.

Based on the user input - our program needs to initiate the Addition / Subtraction / Exit operation

### Task

Update the `calculatorFunction(user_choice)` function in the IDE to write the conditions based on user choice.

Click on 'Run' to review how your code functions in the console.
Click on 'Submit' if you completed your task.

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-29T16:01:14.550Z  

```cpp
    public static void main(String[] args) {

        // Create a Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Display the calculator menu
        System.out.println(calculatorDisplay());

        // Take user input
        System.out.print("Select the operation: ");
        int userChoice = scanner.nextInt();

        // Process the user's choice
        String value = calculatorFunction(userChoice);
        System.out.println(value);

        // Close the scanner
        scanner.close();
    }
}

```

---

[View on CodeChef](https://www.codechef.com/problems/JAPROJPR08)