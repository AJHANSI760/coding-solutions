
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