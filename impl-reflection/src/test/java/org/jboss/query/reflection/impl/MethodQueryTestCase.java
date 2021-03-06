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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.jboss.query.reflection.api.MethodQuery;
import org.jboss.query.reflection.api.Query;
import org.jboss.query.test.AbstractMethodQueryTest;

/**
 * MethodQueryTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class MethodQueryTestCase extends AbstractMethodQueryTest<Method, Class<?>, MethodQuery>
{
   @Override
   protected MethodQuery createTypeQuery(Class<?> type)
   {
      return Query.forMethod().withType(type);
   }

   @Override
   protected MethodQuery createAnnotationQuery(Class<? extends Annotation> annotation)
   {
      return Query.forMethod().withAnnotation(annotation);
   }
   
   @Override
   protected MethodQuery createNameQuery(String expression)
   {
      return Query.forMethod().withName(expression);
   }

   @Override
   protected MethodQuery createTypeAndAnnotationQuery(Class<?> type, Class<? extends Annotation> annotation)
   {
      return Query.forMethod().withType(type).withAnnotation(annotation);
   }

   @Override
   protected Class<?>[] convertInput(Class<?>... inputs)
   {
      return inputs;
   }

   @Override
   protected String extractName(Method obj)
   {
      return obj.getName();
   }
}
