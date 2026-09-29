# JAPROJPR09

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

### Perform calculations

Let us now update the functions to perform calculations.

### Task

Please update the `user_input()` function to take inputs for `a` and `b` in order. Ensure that `numbers[0]` is assigned the value of `a` and `numbers[1]` is assigned the value of `b` after the inputs are provided.

Click on 'Run' to review how your code functions in the console.
Click on 'Submit' if you completed your task.

## Solution

**Language:** C++  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-29T16:04:07.647Z  

```cpp

        } else {
            return "Exit the program";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(calculatorDisplay());

        System.out.print("Select the operation: ");
        int userChoice = scanner.nextInt();

        String result = calculatorFunction(userChoice, scanner);
        System.out.println(result);

        scanner.close();
    }
}
```

---

[View on CodeChef](https://www.codechef.com/problems/JAPROJPR09)