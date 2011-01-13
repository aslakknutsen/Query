/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.query.test;

import java.util.ArrayList;
import java.util.Collection;

import org.jboss.query.api.Executable;
import org.jboss.query.test.model.Animal;
import org.jboss.query.test.model.Cat;
import org.jboss.query.test.model.Dog;
import org.junit.Assert;

/**
 * AbstractFieldQueryTest
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public abstract class AbstractQueryTest<RESULT, INPUT, QUERY extends Executable<RESULT, INPUT>>
{
   private static final Class<?>[] RUN_INPUT = new Class<?>[]
   {
      Animal.class, 
      Cat.class, 
      Dog.class
   };
   
   protected abstract INPUT[] convertInput(Class<?>... inputs);
   
   protected abstract String extractName(RESULT obj);
   
   protected final void validate(QUERY query, String... expectedResults)
   {
      assertContains(run(query), expectedResults);
   }
   
   private Collection<RESULT> run(QUERY query)
   {
      return query.run(
            convertInput(RUN_INPUT));
   }

   private void assertContains(Collection<RESULT> result, String... names)
   {
      if(names == null || names.length == 0) { Assert.fail("No names were given"); }
      if(result == null) { Assert.fail("Result returned null"); }
      
      Assert.assertEquals(
            "Verify expected return size", 
            names.length, 
            result.size());
      
      Collection<String> resultNames = convertResult(result);
      for(String name : names)
      {
         Assert.assertTrue(
               "Verify " + name + " was found in result", 
               resultNames.contains(name));
      }
   }
   
   private Collection<String> convertResult(Collection<RESULT> inputs)
   {
      Collection<String> output = new ArrayList<String>();
      for(RESULT input: inputs)
      {
         output.add(extractName(input));
      }
      return output;
   }
}
