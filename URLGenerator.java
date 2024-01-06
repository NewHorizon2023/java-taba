public class URLGenerator {

    private String companyName;

    public URLGenerator() {

    }

    /**
     * Validates an array of URLs based on specific rules.
     *
     * @param urls Array of URLs to validate
     * @return Array of booleans indicating validity of each URL
     */
    public boolean[] validateURLs(String[] urls) {
        boolean[] results = new boolean[urls.length];
        
        for (int i = 0; i < urls.length; i++) {
            String url = urls[i].toLowerCase(); // Convert URL to lower case for uniformity
            results[i] = isValidURL(url); // Validate the URL and store the result
        }
        
        return results;
    }

    /**
     * Checks if the URL is valid based on predefined rules.
     *
     * @param url URL to validate
     * @return true if the URL is valid, false otherwise
     */
    private boolean isValidURL(String url) {
        // Check if the URL contains "amazon" or "aws"
        if (!url.contains("amazon") && !url.contains("aws")) {
            return false;
        }

        // Check if the URL length is within the valid range
        if (url.length() < 5 || url.length() > 16) {
            return false;
        }

        // Check if the URL contains only valid characters
        return url.matches("[a-zA-Z0-9_/.]*");
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    // Method to generate URL based on the given company name
    public void compute() {
        String protocol;
        String domain;
        String path;

        // a) Selecting the protocol
        if (companyName.toLowerCase().contains("google")) {
            protocol = "https://";
        } else {
            protocol = "http://";
        }

        // b) Removing 'Inc.', 'Ltd.', and 'LLC'
        companyName = removeSubstrings(companyName);

        // c) Eliminating all spaces and replacing with hyphens
        domain = companyName.replace(" ", "-");

        // e) Adding '.ie' or '.com' based on the number of vowels
        int vowelCount = countVowels(companyName);
        if (vowelCount % 2 == 0) {
            domain += ".com";
        } else {
            domain += ".ie";
        }

        // f) Setting the path based on pairs of consonants
        int pairCount = countConsonantPairs(companyName);
        if (pairCount == 0) {
            path = "/index";
        } else if (pairCount >= 1 && pairCount <= 3) {
            path = "/contactDetails";
        } else {
            path = "/basket";
        }

        // Building the full URL
        this.companyName = protocol + domain.toLowerCase() + path;
    }

    private String removeSubstrings(String companyName) {
        String[] unwantedSubstrings = {"Inc.", "Ltd.", "LLC"};
        for (String unwanted : unwantedSubstrings) {
            // Split the company name around the unwanted substring
            String[] parts = companyName.split(unwanted);
            // Rebuild the company name without the unwanted part
            StringBuilder companyNameBuilder = new StringBuilder();
            for (String part : parts) {
                companyNameBuilder.append(part);
            }
            companyName = companyNameBuilder.toString();
        }
        return companyName;
    }

    private int countVowels(String input) {
        int count = 0;
        // Convert the input string to an array of characters for iteration
        char[] characters = input.toCharArray();
        for (char c : characters) {
            // Check if the character is a vowel by comparing with vowels explicitly
            if (c == 'a' || c == 'A' ||
                    c == 'e' || c == 'E' ||
                    c == 'i' || c == 'I' ||
                    c == 'o' || c == 'O' ||
                    c == 'u' || c == 'U') {
                count++;
            }
        }
        return count;
    }

    private int countConsonantPairs(String input) {
        int pairs = 0;
        input = input.toLowerCase(); // Make the input lowercase for simplicity
        for (int i = 0; i < input.length() - 1; i++) {
            if (isConsonant(input.charAt(i)) && isConsonant(input.charAt(i + 1))) {
                pairs++;
            }
        }
        return pairs;
    }

    private boolean isConsonant(char c) {
        char[] consonants = {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'};
        c = Character.toLowerCase(c); // convert to lowercase to check
        for (char cons : consonants) {
            if (c == cons) {
                return true;
            }
        }
        return false;
    }
}
