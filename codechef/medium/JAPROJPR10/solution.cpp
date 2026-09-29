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
