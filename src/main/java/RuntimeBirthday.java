import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class RuntimeBirthday {

    public static void main(String[] args) {
        try {
            Random random = new Random();
            int casos = random.nextInt(5) + 1;

            String[] commandArgs = new String[casos + 1];
            commandArgs[0] = String.valueOf(casos);
            for (int i = 1; i <= casos; i++) {
                commandArgs[i] = String.valueOf(random.nextInt(100) + 1);
            }

            String jarFilePath = "src/main/resources/BirthdayArgs.jar";
            String command = "java -cp " + jarFilePath + " org.example.BirthdayArgs " + String.join(" ", commandArgs);

            Process process = Runtime.getRuntime().exec(command);

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            boolean hasError = false;

            System.out.println("Cases: "+ commandArgs[0]);

            for (int i=1; i < commandArgs.length; i++){
                System.out.println(commandArgs[i]);
            }

            while ((line = inputReader.readLine()) != null) {
                System.out.println("Output: " + line);
            }

            while ((line = errorReader.readLine()) != null) {
                System.err.println("Error: " + line);
                hasError = true;
            }

            int exitCode = process.waitFor();

            if (exitCode == 0 && !hasError) {
                System.out.println("\nBirthdayArgs executed successfully.");
            } else {
                System.err.println("\nBirthdayArgs execution failed with exit code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
