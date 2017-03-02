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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;


/**
 * Created by justin on 02/03/17.
 */
public class ContractProcessor extends AbstractProcessor<CtElement> {
    private static final Random random = new Random();

    public void process(CtElement element) {
        if (element instanceof CtClass<?>) {
            CtClass ct = (CtClass<?>) element;
            String type = ct.getSimpleName();

            if(type.equals("Contracts")){
                CtConstructor cons = (CtConstructor) ct.getConstructors().toArray()[0];
                CtFor f = (CtFor) cons.getBody().getStatement(4);
                CtCodeSnippetStatement body = getFactory().Code().createCodeSnippetStatement(
                        "org.json.JSONObject obj=array.getJSONObject(i);\n" +
                                "            contractList.put(Resource.toResource(obj.getString(\"resource\")), 0);\n" +
                                "            exploitedResourceList.put(Resource.toResource((obj.getString(\"resource\"))),0);\n" +
                                "            Resource res=Resource.toResource((obj.getString(\"resource\")));\n" +
                                "            if(!res.isPrimary()){\n" +
                                "                manufacturedList.put(Resource.toResource(obj.getString(\"resource\")),0);\n" +
                                "                java.util.List<javafx.util.Pair<Resource,Integer>> requirements=res.requiredPrimary(res,1);\n" +
                                "                for (javafx.util.Pair<Resource, Integer> requirement : requirements) {\n" +
                                "                    exploitedResourceList.put(requirement.getKey(), 0);\n" +
                                "                }\n" +
                                "            }"
                );
                f.setBody(body);
                System.out.println(cons.toString());

            }
        }
    }
}
