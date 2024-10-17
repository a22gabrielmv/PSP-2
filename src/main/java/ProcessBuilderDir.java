import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ProcessBuilderDir {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Name location:  ");
        String location = scanner.nextLine();

        ProcessBuilder pb=new ProcessBuilder();

        pb.directory(new File(location));

        pb.command("cmd.exe", "/c", "dir");

        Process process=pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("\nProcess exited with code: " + exitCode);
    }
}

