import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.*;
import spoon.reflect.factory.Factory;
import spoon.reflect.reference.CtReference;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.CtVisitor;
import spoon.reflect.visitor.Filter;
import spoon.reflect.visitor.chain.CtFunction;
import spoon.reflect.visitor.chain.CtQuery;

import java.lang.annotation.Annotation;
import java.util.*;


public class AirScanFlyOnlyProcessor extends AbstractProcessor<CtElement> {
    private static final Random random = new Random();

    public void process(CtElement element) {
        if (element instanceof CtClass<?>) {
            CtClass ct = (CtClass<?>) element;
            String type = ct.getSimpleName();

            if(type.equals("AirScanStrategy")){

                Vector<CtMethod<?>> methods = new Vector<CtMethod<?>>(ct.getMethods());
                for (CtMethod<?> method: methods) {
                    if(method.getSignature().equals("takeDecision")) {
                        System.out.println(method.getBody().toString());

                        CtCodeSnippetStatement body = getFactory().Code().createCodeSnippetStatement(
                                "return Action.fly();\n");
                        method.setBody(body);
                    }
                }



            }
        }
    }
}
