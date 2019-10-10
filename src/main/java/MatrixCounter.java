
import java.util.Random;

public class MatrixCounter {
    public static int n;

    private static Random random = new Random();

    public static float[] generateArray() {
        float[] temp = new float[n];
        for (int i = 0; i < n; i++) {
            temp[i] = random.nextInt(10) + 1;
        }
        return temp;
    }

    public static float[][] generateMatrix() {
        float[][] temp = new float[n][n];
        for (int i = 0; i < n; i++) {
            temp[i] = generateArray();
        }
        return temp;
    }

    public static float[] sumVectors( float[] vector1,
                                       float[] vector2) {
        for (int i = 0; i < n; i++) {
            vector1[i] += vector2[i];
        }
        return vector1;
    }

    public static float[][] substractMatrixes( float[][] matrix1,
                                                float[][] matrix2) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix1[i][j] -= matrix2[i][j];
            }
        }
        return matrix1;
    }

    public static float[] multiplyNumberVector(float number,
                                                float[] vector) {
        for (int i = 0; i < n; i++) {
            vector[i] *= number;
        }
        return vector;
    }

    public static float[][] multiplyNumberMatrix(float number,
                                                  float[][] matrix) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] *= number;
            }
        }
        return matrix;
    }

    public static float[] multiplyVectorMatrix(float[] vector,
                                                float[][] matrix) {
        float sum;
        float result[] = new float[n];

        for (int i = 0; i < n; i++) {
            sum = 0.0f;
            for (int j = 0; j < n; j++) {
                sum += matrix[i][j] * vector[j];
            }
            result[i] = sum;
        }
        return result;
    }

    public static float[][] multiplyMatrixes(float[][] matrix1,
                                              float[][] matrix2) {
        float result[][] = new float[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }

    public static float[][] multiplyColumnRow(float[] vector1,
                                               float[] vector2) {
        float result[][] = new float[n][n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                result[j][k] = vector1[j] * vector2[k];
            }
        }
        return result;
    }

    public static float[] multiplyRowMatrix(float[] vector,
                                             float[][] matrix) {
        float result[] = new float[n];
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                result[j] += vector[k] * matrix[k][j];
            }
        }
        return result;
    }

    public static float multiplyRowColumn(float[] vector1,
                                           float[] vector2) {
        float result = 0;
        for (int j = 0; j < n; j++) {
            result += vector1[j] * vector2[j];
        }
        return result;
    }

    public static float[][] sumMatrixes(float[][]... matrixes) {
        boolean mark = true;
        float[][] result = null;

        for(float[][] matrix: matrixes) {
            if (mark) {
                result = matrix;
                mark = false;
                continue;
            }

            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    result[j][k] += matrix[j][k];
                }
            }
        }
        return result;
    }
}
