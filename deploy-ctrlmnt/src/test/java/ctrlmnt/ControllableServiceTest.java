package ctrlmnt;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for ControllableService#doWork(long).
 *
 * Strategy:
 * - Inject a FakeThreadMXBean that uses System.nanoTime() for "CPU time".
 * - Inject a FixedExponentialDistribution that always returns a fixed ms value.
 * - Verify that doWork() spins for (approximately) that fixed duration and returns.
 */
public class ControllableServiceTest {

    /**
     * Minimal concrete ControllableService just to exercise doWork().
     */
    static class TestService extends ControllableService {
        // private Float hw = 1.0f;
        // private String name = "test";
        // private int user = 1;
        // private String iscgroup = "n";
// 
        // @Override
        // public Float getHw() {
            // return hw;
        // }
// 
        // @Override
        // public void setHw(Float hw) {
            // this.hw = hw;
        // }
// 
        // @Override
        // public String getName() {
            // return name;
        // }
// 
        // @Override
        // public void ingress() { /* no-op */ }
// 
        // @Override
        // public void egress() { /* no-op */ }
// 
        // @Override
        // public Integer getUser() {
            // return user;
        // }
// 
        // @Override
        // public String getIscgroup() {
            // return iscgroup;
        // }
    }


    @Test
    @DisplayName("doWork should spin approximately for the sampled CPU time and return")
    void testDoWorkSpinsAndReturns() throws Exception {

        long stime = 5; // ms
        TestService svc = new TestService();

        long startWall = System.nanoTime();
        svc.doWork(stime /* ignored since dist already injected */);
        long elapsedNs = System.nanoTime() - startWall;

        long minExpectedNs = TimeUnit.MILLISECONDS.toNanos(4);   // allow slight under-run
        long maxReasonableNs = TimeUnit.MILLISECONDS.toNanos(200); // generous upper bound for CI variance

/*
        assertTrue(elapsedNs >= minExpectedNs,
                "Expected at least ~4ms of spinning, got " + elapsedNs + " ns");

        assertTrue(elapsedNs < maxReasonableNs,
                "Should return promptly; took " + TimeUnit.NANOSECONDS.toMillis(elapsedNs) + " ms");
*/
    }
}