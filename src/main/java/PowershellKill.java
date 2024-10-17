import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PowershellKill {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("List of running processes:");
            if (!executeCommand("powershell.exe Get-Process | Select-Object Name,Id")) {
                return;
            }

            System.out.println("\nName the process to kill: ");
            String programName = scanner.nextLine();

            if (!executeCommand("powershell.exe Get-Process -Name " + programName)) {
                System.out.println("No process found with the name: " + programName);
                return;
            }

            if (executeCommand("powershell.exe Stop-Process -Name " + programName)) {
                System.out.println("Process killed successfully.");
            } else {
                System.err.println("Failed to kill the process.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static boolean executeCommand(String command) throws Exception {
        Process process = Runtime.getRuntime().exec(command);

        try (BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
             BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {

            String line;
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }

            boolean hasError = false;
            while ((line = stderr.readLine()) != null) {
                System.err.println("Error: " + line);
                hasError = true;
            }

            int exitCode = process.waitFor();
            return exitCode == 0 && !hasError;
        }
    }
}
