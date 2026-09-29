# JAPROJPR10

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Adding the loop

Great going so far.

Did you notice that your calculator runs the operation only once?

Let us update the code to ensure that we are able to perform multiple operations.

### Task

We have added a `while` loop to your code.

- Update the code in the IDE to ensure that the loop exits / Calculator operation stops only when defined by you by using the 'break' clause

Click on 'Run' to review how your code functions in the console.
Click on 'Submit' if you completed your task.

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-29T16:06:41.725Z  

```cpp
            // Display the calculator menu
            System.out.println(calculatorDisplay());

            System.out.print("Select the operation: ");
            int userChoice = scanner.nextInt();

            // Process the user choice and get result
            String result = calculatorFunction(userChoice, scanner);
            System.out.println(result);

            // Exit the loop when user chooses 3
            if (userChoice == 3) {
                break;
            }
        }

        // Close scanner
        scanner.close();
    }
}

        while (true) {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

```

---

[View on CodeChef](https://www.codechef.com/problems/JAPROJPR10)