package rpn.calc;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalculatorUtils {

    public static DecimalFormat provideDecimalFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("0.##########");
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        return decimalFormat;
    }
}
