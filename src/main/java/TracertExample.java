import java.io.File;
import java.io.IOException;

public class TracertExample {
    public static void main(String[] args) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("tracert", "iessanclemente.net");

        File outputFile = new File("outputTracert.txt");
        processBuilder.redirectOutput(outputFile);

        if (outputFile.exists()){
            outputFile.delete();
        }

        Process process = null;
        try {
            process = processBuilder.start();

            Thread.sleep(8000);

            process.destroy();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (outputFile.exists()) {
            System.out.println("File outputTracert.txt created successfully");
        } else {
            System.out.println("Error creating outputTracert.txt");
        }
    }
}
