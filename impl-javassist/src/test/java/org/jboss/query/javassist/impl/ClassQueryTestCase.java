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
package org.jboss.query.javassist.impl;

import java.lang.annotation.Annotation;

import javassist.CtClass;

import org.jboss.query.javassist.api.ClassQuery;
import org.jboss.query.javassist.api.Query;
import org.jboss.query.test.AbstractClassQueryTest;

/**
 * ClassQueryTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ClassQueryTestCase extends AbstractClassQueryTest<CtClass, String, ClassQuery>
{
   @Override
   protected ClassQuery createTypeQuery(Class<?> type)
   {
      return Query.forClass().withType(type.getName());
   }
   
   @Override
   protected ClassQuery createAnnotationQuery(Class<? extends Annotation> annotation)
   {
      return Query.forClass().withAnnotation(annotation.getName());
   }

   @Override
   protected ClassQuery createNameQuery(String expression)
   {
      return Query.forClass().withName(expression);
   }

   @Override
   protected ClassQuery createTypeAndAnnotationQuery(Class<?> type, Class<? extends Annotation> annotation)
   {
      return Query.forClass().withType(type.getName()).withAnnotation(annotation.getName());
   }

   @Override
   protected String[] convertInput(Class<?>... inputs)
   {
      return ConvertUtil.convertInput(inputs);
   }

   @Override
   protected String extractName(CtClass obj)
   {
      return obj.getName();
   }
}
