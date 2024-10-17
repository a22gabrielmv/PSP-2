import java.io.*;
import java.util.Scanner;

public class SumNumbersProcess {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Number of cases: ");
            int cases = Integer.parseInt(scanner.nextLine());

            StringBuilder inputNumbers = new StringBuilder();
            inputNumbers.append(cases).append("\n");

            for (int i = 0; i < cases; i++) {
                //System.out.print("Enter number " + (i + 1) + ": ");
                inputNumbers.append(scanner.nextLine()).append("\n");
            }

            ProcessBuilder pb1 = new ProcessBuilder("java","-classpath","target/classes", "SumNumbers");
            Process process1 = pb1.start();

            try (BufferedWriter writer1 = new BufferedWriter(new OutputStreamWriter(process1.getOutputStream()))) {
                writer1.write(inputNumbers.toString());
                writer1.flush();
            }

            String result1 = readProcessOutput(process1);

            System.out.println("Result from SumNumbers: " + result1);

            ProcessBuilder pb2 = new ProcessBuilder("java","-classpath","target/classes", "SumNumbers2");
            Process process2 = pb2.start();

            try (BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(process2.getOutputStream()))) {
                writer2.write(inputNumbers.toString());
                writer2.flush();
            }

            String result2 = readProcessOutput(process2);

            System.out.println("Result from SumNumbers2: " + result2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readProcessOutput(Process process) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        return output.toString().trim();
    }
}
