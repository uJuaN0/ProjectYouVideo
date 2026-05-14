import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TrainingTests {

    public static void main(String[] args) throws Exception {
        runAllTests();
    }

    private static void runAllTests() throws Exception {
        for (int i = 1; i <= 9; i++) {
            runTest(
                    "training/input" + i + "_training.txt",
                    "training/output" + i + "_training.txt"
            );
        }
    }

    private static void runTest(String inputPath, String expectedOutputPath) throws Exception {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        String input = Files.readString(Path.of(inputPath), StandardCharsets.UTF_8);
        String expected = Files.readString(Path.of(expectedOutputPath), StandardCharsets.UTF_8);

        ByteArrayInputStream fakeIn =
                new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream fakeOutBytes = new ByteArrayOutputStream();
        PrintStream fakeOut = new PrintStream(fakeOutBytes, true, StandardCharsets.UTF_8);

        try {
            System.setIn(fakeIn);
            System.setOut(fakeOut);

            Main.main(new String[0]);

        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }

        String actual = fakeOutBytes.toString(StandardCharsets.UTF_8);

        if (normalize(actual).equals(normalize(expected))) {
            System.out.println("[PASS] " + inputPath);
        } else {
            System.out.println("[FAIL] " + inputPath);
            showDifference(expected, actual);
        }
    }

    private static String normalize(String text) {
        return text
                .replace("\r\n", "\n")
                .replace("\r", "\n")
                .trim();
    }

    private static void showDifference(String expected, String actual) {
        String[] expLines = normalize(expected).split("\n");
        String[] actLines = normalize(actual).split("\n");

        int max = Math.max(expLines.length, actLines.length);

        for (int i = 0; i < max; i++) {
            String exp = i < expLines.length ? expLines[i] : "<missing>";
            String act = i < actLines.length ? actLines[i] : "<missing>";

            if (!exp.equals(act)) {
                System.out.println("First difference at line " + (i + 1));
                System.out.println("EXPECTED: " + exp);
                System.out.println("ACTUAL  : " + act);
                return;
            }
        }

        System.out.println("Difference found, but no single line mismatch identified.");
        System.out.println("----- EXPECTED -----");
        System.out.println(expected);
        System.out.println("----- ACTUAL -----");
        System.out.println(actual);
    }
}