import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A small JOCL sample.
 */
public class OpenCLPart1 {

    private static int n;
    private static float bi[];
    private static float b[];
    private static float c[];
    private static float A[][];
    private static float A1[][];
    private static float A2[][];
    private static float B[][];
    private static float C[][];
    private static float AC[][];
    private static float y1[];
    private static float y2[];
    private static float y3[][];
    private static float x1[][];
    private static float x11[][];
    private static float x2[][];
    private static float x31[];
    private static float x3[][];
    private static float x[][];

    public static void main(String[] args) {
        initialize();

        long startTime = System.currentTimeMillis();
        MatrixCounter.n = n;

        Thread countBi = new Thread() {
            public void run() {
                bi = new float[n];
                countBi();
            }
        };
        countBi.start();

        Thread countB = new Thread() {
            public void run() {
                b = MatrixCounter.generateArray();
            }
        };
        countB.start();

        Thread countC = new Thread() {
            public void run() {
                c = MatrixCounter.generateArray();
            }
        };
        countC.start();

        A = MatrixCounter.generateMatrix();

        joinThreads(countBi, countB, countC);
        countBi = null;
        countB = null;
        countC = null;

        Thread countY1 = new Thread() {
            public void run() {
                y1 = MatrixCounter.multiplyVectorMatrix(bi, A);
                bi = null;
                A = null;
            }
        };
        countY1.start();

        Thread countA1 = new Thread() {
            public void run() {
                A1 = MatrixCounter.generateMatrix();
            }
        };
        countA1.start();

        Thread countA2 = new Thread() {
            public void run() {
                A2 = MatrixCounter.generateMatrix();
            }
        };
        countA2.start();

        C = new float[n][n];
        calculateC();

        joinThreads(countY1, countA1, countA2);
        countY1 = null;
        countA1 = null;
        countA2 = null;

        Thread countY2 = new Thread() {
            public void run() {
                b = MatrixCounter
                        .multiplyNumberVector(14, b);
                c = MatrixCounter
                        .multiplyNumberVector(14, c);
                float[] sum = MatrixCounter.sumVectors(b, c);
                b = null;
                c = null;
                y2 = OpenCLRunner.multiplyVectorMatrix(sum, A1);
                sum = null;
                A1 = null;
            }
        };
        countY2.start();

        Thread countAB = new Thread() {
            public void run() {
                AC = MatrixCounter.multiplyMatrixes(A2, C);
                C = null;
                A2 = null;
            }
        };
        countAB.start();

        B = MatrixCounter.generateMatrix();

        joinThreads(countY2, countAB);
        countY2 = null;
        countAB = null;

        y3 = MatrixCounter.substractMatrixes(AC, B);
        AC = null;
        B = null;

        Thread countX10 = new Thread() {
            public void run() {
                x1 = MatrixCounter.multiplyMatrixes(
                        MatrixCounter.multiplyMatrixes(y3, y3), y3
                );
            }
        };
        countX10.start();

        Thread countX11 = new Thread() {
            public void run() {
                x11 = MatrixCounter.multiplyColumnRow(y1,y1);
            }
        };
        countX11.start();

        Thread countX2 = new Thread() {
            public void run() {
                x2 = MatrixCounter.multiplyColumnRow(y2, y1);
            }
        };
        countX2.start();

        x31 = MatrixCounter.multiplyRowMatrix(y2,y3);
        joinThreads(countX10, countX11, countX2);
        countX10 = null;
        countX2 = null;
        countX11 = null;

        Thread countX1 = new Thread() {
            public void run() {
                y2=null;
                x1 = MatrixCounter.multiplyMatrixes(x1, x11);
                x11 = null;
            }
        };
        countX1.start();

        x3 = MatrixCounter.multiplyNumberMatrix(
                MatrixCounter.multiplyRowColumn(x31,y1),y3
        );

        x = MatrixCounter.sumMatrixes(x1, x2, x3);

        MatrixFileWriter.write(x, "results.txt");

        long endTime = System.currentTimeMillis();;
        System.out.println("Обрахунок зайняв " +
                (endTime - startTime) / 1000.0 + " секунд");
    }

    public static void initialize() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введiть розмiрнiсть: ");
        n = scan.nextInt();
    }

    public static void countBi() {
        for (int i = 0; i < n; i++) {
            bi[i] = ((i+1) % 2) == 0 ? (i+1)
                    : (15.0f / (float) (i+1));
        }
    }

    public static void calculateC() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = 14.0f / ((float)
                        ((i + 1) + Math.pow((j + 1), 4))) ;
            }
        }
    }

    public static void joinThreads(Thread... threads) {
        try {
            for (Thread t: threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
