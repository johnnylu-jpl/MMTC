package edu.jhuapl.sd.sig.mmtc;

import edu.jhuapl.sd.sig.mmtc.util.TimeConvert;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Disables SPICE-dependent tests when the native JNISpice library cannot be loaded on the
 * current platform. The library only ships as a Linux x86-64 build, so on other platforms
 * (e.g. macOS/arm64) the whole test class is skipped rather than failing with
 * {@link UnsatisfiedLinkError}. Where the library is present (e.g. CI), tests run normally.
 */
public class SpiceExecutionCondition implements ExecutionCondition {

    private static final ConditionEvaluationResult RESULT = evaluateSpiceAvailability();

    private static ConditionEvaluationResult evaluateSpiceAvailability() {
        try {
            TimeConvert.loadSpiceLib();
            return ConditionEvaluationResult.enabled("Native JNISpice library is available");
        } catch (Throwable t) {
            return ConditionEvaluationResult.disabled(
                    "Native JNISpice library could not be loaded on this platform ("
                            + System.getProperty("os.name") + "/" + System.getProperty("os.arch") + "): " + t.getMessage());
        }
    }

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        return RESULT;
    }
}
