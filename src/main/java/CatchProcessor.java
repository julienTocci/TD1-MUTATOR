
import org.apache.log4j.Level;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Reports warnings when empty catch blocks are found.
 */
public class CatchProcessor extends AbstractProcessor<CtElement> {

    private static final Random random = new Random();

    public void process(CtElement element) {

        // for instance we can mutate operators
        if (element instanceof CtBinaryOperator<?>) {
            CtBinaryOperator<?> op = (CtBinaryOperator<?>) element;
            List<BinaryOperatorKind> arithmeticOperators =
                    Arrays.asList(
                            BinaryOperatorKind.PLUS,
                            BinaryOperatorKind.MINUS,
                            BinaryOperatorKind.MUL,
                            BinaryOperatorKind.DIV,
                            BinaryOperatorKind.AND, // &&
                            BinaryOperatorKind.OR
                    );
            // let's mutate arithmethic operators
            if (arithmeticOperators.contains(op.getKind())) {
                if (op.getKind() == BinaryOperatorKind.AND)
                    op.setKind(BinaryOperatorKind.OR);
                else if (op.getKind() == BinaryOperatorKind.OR)
                    op.setKind(BinaryOperatorKind.AND);
            }
        }
    }
}
