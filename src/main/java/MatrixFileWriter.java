
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MatrixFileWriter {
    public static void write(float[][] matrix, String filePath) {

        int rows = matrix.length;
        int columns = matrix[0].length;

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(filePath))) {

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    bw.write(matrix[i][j] + "\t  ");
                }
                bw.newLine();
                bw.newLine();
            }
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }

    public static void write(float[] matrix, String filePath) {

        int length = matrix.length;

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(filePath))) {

            for (int i = 0; i < length; i++) {
                    bw.write(matrix[i] + "\t  ");
            }
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
