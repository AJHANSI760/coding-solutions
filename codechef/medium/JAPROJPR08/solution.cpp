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
