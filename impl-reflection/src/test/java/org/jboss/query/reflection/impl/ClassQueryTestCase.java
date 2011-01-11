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
package org.jboss.query.reflection.impl;

import static org.jboss.query.reflection.impl.AssertList.containsClassName;
import java.lang.reflect.Method;
import java.util.Collection;

import org.jboss.query.reflection.impl.test.Loadable;
import org.jboss.query.reflection.impl.test.QueryTestInterface;
import org.jboss.query.reflection.impl.test.QueryTestScenario;
import org.jboss.query.reflection.impl.test.QueryTestScenario2;
import org.jboss.query.reflection.impl.test.Testable;
import org.junit.Assert;
import org.junit.Test;

/**
 * ClassQueryTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ClassQueryTestCase extends AbstractTestBase
{
   /**
    * Should only return concrete classes, interfaces are ignored in type check
    */
   @Test
   public void shouldFindClassWithType() throws Exception
   {
      Collection<Class<?>> result = run(
            Query.forClass()
               .withType(QueryTestInterface.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(2, result.size());
      Assert.assertTrue(containsClassName("QueryTestScenario", result));
      Assert.assertTrue(containsClassName("QueryTestScenario2", result));
   }
   
   @Test
   public void shouldFindClassWithTypeAndAnnotation() throws Exception
   {
      Collection<Class<?>> result = run( 
         Query.forClass()
               .withAnnotation(Testable.class)
               .withType(QueryTestInterface.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(1, result.size());
      Assert.assertTrue(containsClassName("QueryTestScenario", result));
   }

   @Test
   public void shouldFindClassWithTypeGenericType() throws Exception
   {
      Collection<Class<?>> result = run(
            Query.forClass()
               .withGenericType(String.class)
               .withType(QueryTestInterface.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(1, result.size());
      Assert.assertTrue(containsClassName("QueryTestScenario2", result));
   }

   @Test
   public void shouldFindMethodsWithinAClassQuery() throws Exception
   {
      Collection<Method> result = Query.forMethod()
         .withAnnotation(Loadable.class)
         .run(
            Query.forClass()
               .withAnnotation(Testable.class)
               .run(QueryTestScenario.class, QueryTestScenario2.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(2, result.size());
   }
}
