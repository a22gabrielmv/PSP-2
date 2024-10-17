import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

public class ProcessBuilderSet {
    public static void main(String[] args) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();

            Map<String, String> environment = processBuilder.environment();

            environment.put("NUM1", "10");
            environment.put("NUM2", "40");

            processBuilder.command("cmd.exe", "/c", "set NUM1 & set NUM2 & set /a RESULT=%NUM1%+%NUM2%");

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nProcess exited with code: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
