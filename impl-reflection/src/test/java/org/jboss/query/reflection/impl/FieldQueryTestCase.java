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
import java.lang.reflect.Field;

import org.jboss.query.reflection.api.FieldQuery;
import org.jboss.query.reflection.api.Query;
import org.jboss.query.test.AbstractFieldQueryTest;

/**
 * FieldQueryTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class FieldQueryTestCase extends AbstractFieldQueryTest<Field, Class<?>, FieldQuery>
{
   @Override
   protected FieldQuery createTypeQuery(Class<?> type)
   {
      return Query.forField().withType(type);
   }

   @Override
   protected FieldQuery createAnnotationQuery(Class<? extends Annotation> annotation)
   {
      return Query.forField().withAnnotation(annotation);
   }
   
   @Override
   protected FieldQuery createTypeAndAnnotationQuery(Class<?> type, Class<? extends Annotation> annotation)
   {
      return Query.forField().withType(type).withAnnotation(annotation);
   }

   @Override
   protected Class<?>[] convertInput(Class<?>... inputs) 
   {
      return inputs;
   }
   
   @Override
   protected String extractName(Field obj)
   {
      return obj.getName();
   }
}
