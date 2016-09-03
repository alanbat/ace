/* Java Ace Toolkit by Javier Santo Domingo (j-a-s-d@coderesearchlabs.com) */

package ace.math;

/**
 * Utility class for mathematic operations with integers.
 */
public class Integers {
    
    // UNBOXED
    
    public static final int add(final int... numbers) {
        int result = NUMBERS.I0;
        for (final int number : numbers) {
            result += number;
        }
        return result;
    }
    
    public static final int subtract(final int... numbers) {
        int result = numbers.length < 2 ? NUMBERS.I0 : numbers[0] * 2;
        for (final int number : numbers) {
            result -= number;
        }
        return result;
    }
    
    public static final int max(final int... numbers) {
        int result = Integer.MIN_VALUE;
        for (final int number : numbers) {
            result = Math.max(result, number);
        }
        return result;
    }
    
    public static final int min(final int... numbers) {
        int result = Integer.MAX_VALUE;
        for (final int number : numbers) {
            result = Math.min(result, number);
        }
        return result;
    }
    
    // BOXED
    
    public static final Integer boxedAdd(final Integer... numbers) {
        Integer result = NUMBERS.I0;
        for (final Integer number : numbers) {
            if (number != null) {
                result += number;
            }
        }
        return result;
    }
    
    public static final Integer boxedSubtract(final Integer... numbers) {
        Integer result = null;
        if (numbers.length > 0) {
            result = numbers.length == 1 ? NUMBERS.I0 : numbers[0] * 2;
            for (final Integer number : numbers) {
                if (number != null) {
                    result -= number;
                }
            }
        }
        return result;
    }
    
    public static final Integer boxedMax(final Integer... numbers) {
        Integer result = Integer.MIN_VALUE;
        for (final Integer number : numbers) {
            if (number != null) {
                result = Math.max(result, number);
            }
        }
        return result;
    }
    
    public static final Integer boxedMin(final Integer... numbers) {
        Integer result = Integer.MAX_VALUE;
        for (final Integer number : numbers) {
            if (number != null) {
                result = Math.min(result, number);
            }
        }
        return result;
    }
    
}