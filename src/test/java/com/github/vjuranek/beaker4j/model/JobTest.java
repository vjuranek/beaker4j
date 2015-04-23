package com.github.vjuranek.beaker4j.model;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

public class JobTest {
    
    @Test 
    public void parseResultXml() throws JAXBException, URISyntaxException {
        File file  = new File(getClass().getResource("job.xml").toURI());
        JAXBContext jaxbContext = JAXBContext.newInstance(Job.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Job result = (Job) jaxbUnmarshaller.unmarshal(file);
        
        //java.lang.System.out.println(result);
        List<Object> nwr = result.getNotifyOrWhiteboardOrRecipeSet();
        for(Object o : nwr) {
            if(o instanceof RecipeSet) {
                //java.lang.System.out.println(o.getClass().getName());
                RecipeSet rc = (RecipeSet)o;
                List<Recipe>rss = rc.getRecipe();
                for(Recipe r : rss) {
                    //java.lang.System.out.println("Recipe: " + ((Recipe)r).getGuestrecipeOrAutopickOrKickstart().size());
                    List<Object> gr = r.getGuestrecipeOrAutopickOrKickstart();
                    for(Object t : gr) {
                        //java.lang.System.out.println("GR: " + t.getClass().getName());
                        if(t instanceof Task) {
                            for(Result res : ((Task)t).getResults().getResult()) {
                                java.lang.System.out.println("Task: " + res.getResult());
                            }
                        }
                    }
                }
            }
        }
        /*assertEquals(2138849, result.getId());
        assertEquals("/distribution/reservesys", result.getPath());
        assertEquals(TaskResult.Pass, result.getResult());
        assertEquals(0.0, result.getScore());
        assertEquals("(Pass)", result.getResultStr());*/
    }

}
