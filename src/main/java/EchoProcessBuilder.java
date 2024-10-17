import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;

public class EchoProcessBuilder {
    public static void main(String[] args) {

        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "echo", "Hello World");


        Map<String, String> environment = processBuilder.environment();

        System.out.println("Execution Environment Variables:");
        for (Map.Entry<String, String> entry : environment.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }


        try {
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nProcess exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
