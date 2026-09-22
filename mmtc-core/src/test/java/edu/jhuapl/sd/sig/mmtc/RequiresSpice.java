package edu.jhuapl.sd.sig.mmtc;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a test class as requiring the native JNISpice library. Such classes are skipped
 * automatically on platforms where the library cannot be loaded (see {@link SpiceExecutionCondition}).
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(SpiceExecutionCondition.class)
public @interface RequiresSpice {
}
