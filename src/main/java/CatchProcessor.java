
import org.apache.log4j.Level;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCatch;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Reports warnings when empty catch blocks are found.
 */
public class CatchProcessor extends AbstractProcessor<CtCatch> {

    private static final Random random = new Random();

    public void process(CtCatch element) {
        if (element.getBody().getStatements().size() == 0) {
            // for instance we can mutate operators
            if (element instanceof CtBinaryOperator<?>) {
                CtBinaryOperator<?> op = (CtBinaryOperator<?>)element;
                List<BinaryOperatorKind> arithmeticOperators =
                Arrays.asList(
                        BinaryOperatorKind.PLUS,
                        BinaryOperatorKind.MINUS,
                        BinaryOperatorKind.MUL,
                        BinaryOperatorKind.DIV
                );
                // let's mutate arithmethic operators
                if (arithmeticOperators.contains(op.getKind())) {
                    op.setKind(arithmeticOperators.get(random.nextInt(arithmeticOperators.size())));
                }

            }
        }
    }
}