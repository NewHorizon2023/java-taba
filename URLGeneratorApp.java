import java.util.Scanner;

public class URLGeneratorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter '1' to generate URLs or '2' to validate URLs:");
        int choice = Integer.parseInt(scanner.nextLine().trim());

        switch (choice) {
            case 1:
                generateURLs(scanner);
                break;
            case 2:
                validateURLs(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter '1' for generate or '2' for validate.");
                break;
        }

        scanner.close();

    }

    private static void generateURLs(Scanner scanner) {
        System.out.println("How many URLs would you like to generate?");
        int numberOfURLs = Integer.parseInt(scanner.nextLine());

        // Array to store the URLs
        String[] urls = new String[numberOfURLs];
        URLGenerator urlGenerator = new URLGenerator();

        // Loop to get company names and generate URLs
        for (int i = 0; i < numberOfURLs; i++) {
            System.out.println("Enter company name " + (i + 1) + ":");
            String companyName = scanner.nextLine();
            urlGenerator.setCompanyName(companyName);
            urlGenerator.compute();
            urls[i] = urlGenerator.getCompanyName();
        }

        // Output the generated URLs
        System.out.println("\nGenerated URLs:");
        for (String url : urls) {
            System.out.println(url);
        }
    }

    private static void validateURLs(Scanner scanner) {
        System.out.println("Enter the number of URLs you would like to validate:");
        int numberOfURLs = Integer.parseInt(scanner.nextLine());

        // Array to store the URLs
        String[] urls = new String[numberOfURLs];

        // Prompt the user to provide those URLs
        for (int i = 0; i < numberOfURLs; i++) {
            System.out.println("Please enter URL number " + (i + 1) + ":");
            urls[i] = scanner.nextLine();
        }

        // Validate the URLs and obtain the results
        URLGenerator urlGenerator = new URLGenerator();
        boolean[] validationResults = urlGenerator.validateURLs(urls);

        // Display the results on the screen
        System.out.println("\nValidation Results:");
        for (int i = 0; i < numberOfURLs; i++) {
            System.out.println("URL " + (i + 1) + " (" + urls[i] + ") is valid: " + validationResults[i]);
        }
    }
}