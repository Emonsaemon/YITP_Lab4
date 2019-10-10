import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MatrixCounterTest {

    @Test
    public void generateArrayTest() {
        MatrixCounter mCounter = new MatrixCounter();
        mCounter.n = 1;
        float[] generated = mCounter.generateArray();
        assertEquals(mCounter.n, generated.length);
    }
}
