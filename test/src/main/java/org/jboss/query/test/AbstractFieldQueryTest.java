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

import java.lang.annotation.Annotation;

import org.jboss.query.api.FieldQuery;
import org.jboss.query.test.model.Cat;
import org.jboss.query.test.model.Female;
import org.junit.Test;

/**
 * AbstractFieldQueryTest
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public abstract class AbstractFieldQueryTest<RESULT, INPUT, QUERY extends FieldQuery<? extends QUERY, INPUT, ?, RESULT>> 
      extends AbstractQueryTest<RESULT, INPUT, QUERY>
{
   @Test
   public void shouldMatchOnType() throws Exception
   {
      validate(createTypeQuery(Cat.class), "mother", "father");
   }
   
   @Test 
   public void shouldMatchOnAnnotation() throws Exception
   {
      validate(createAnnotationQuery(Female.class), "mother", "mother");
   }

   @Test 
   public void shouldMatchOnTypeAndAnnotation() throws Exception
   {
      validate(createTypeAndAnnotationQuery(Cat.class, Female.class), "mother");
   }

   protected abstract QUERY createTypeQuery(Class<?> type);
   
   protected abstract QUERY createAnnotationQuery(Class<? extends Annotation> annotation);
   
   protected abstract QUERY createTypeAndAnnotationQuery(Class<?> type, Class<? extends Annotation> annotation);
}
