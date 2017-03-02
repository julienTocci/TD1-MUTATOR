import spoon.processing.AbstractProcessor;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtLiteral;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by david on 02/03/17.
 */
public class IntComparaisonProcessor extends AbstractProcessor<CtElement> {
    private static final Random random = new Random();

    public void process(CtElement element) {

        // for instance we can mutate operators
        if (element instanceof CtLiteral<?>) {
            CtLiteral ctLiteral = (CtLiteral<?>) element;
            String type = ctLiteral.getType().getSimpleName();

            if(type.equals("int")){
                ctLiteral.setValue((Integer)ctLiteral.getValue()+1);
            }
        }
    }
}
