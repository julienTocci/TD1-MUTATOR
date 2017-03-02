import org.apache.log4j.Logger;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtElement;


/**
 * Created by Akh on 02/03/2017.
 */
public class SwitchProcessor extends AbstractProcessor<CtElement> {
    static Logger logger = Logger.getLogger(SwitchProcessor.class);

    public void process(CtElement element) {


        // On verifie si element est un switch
        if (element instanceof CtSwitch<?>) {
            CtSwitch<?> sw = (CtSwitch<?>) element;
            boolean hasDefault=false;
            int nbOfCases=sw.getCases().size();

            for(int i=0;i<nbOfCases;i++) {
                CtCase currentCase = sw.getCases().get(i);
                CtExpression currentCaseExpression = currentCase.getCaseExpression();
                if (currentCaseExpression == null) {
                    hasDefault = true;
                }
            }

            //Si on entre dans ce if, le switch possede bien un default
            if(hasDefault){
                logger.info("There is a default case in this switch");

            }

            //Si on entre ici, le switch ne possede pas de default
            else {
                //On ajoute un commentaire avant le switch indiquant qu'il n'y a pas de default dans ce switch
                CtCodeSnippetStatement com = getFactory().Core().createCodeSnippetStatement();
                com.setValue("//Warning : there is no default in this switch");
                sw.insertBefore(com);

                logger.info("There is no default case in this switch");

            }

            /* Les deux lignes de code suivantes permettent d'enlever le premier case d'un switch
            La raison pour laquelle elles ont ete commentees est que bien qu'elles suppriment correctement le premier
            case, certains tests maven devenaient bloques dans une boucle infinie
            */
            /*    CtCase caseElt=sw.getCases().get(0);
                sw.removeCase(caseElt);
            */


        }
    }
}
