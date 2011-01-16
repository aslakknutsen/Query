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
import java.util.Arrays;
import java.util.Collection;

import org.jboss.query.impl.AbstractBaseQuery;
import org.jboss.query.reflection.api.FieldQuery;

/**
 * {@link FieldQuery}
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ReflectionFieldQuery 
   extends AbstractBaseQuery<FieldQuery, Class<?>, Class<? extends Annotation>, Field> 
   implements FieldQuery 
{
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#extractAllTargets(java.lang.Object)
    */
   @Override
   protected Collection<Field> extractAllTargets(Class<?> source)
   {
      return Arrays.asList(source.getDeclaredFields());
   }
   
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#match(java.lang.Object)
    */
   @Override
   protected boolean match(Field target)
   {
      // Named
      if(getNameExpression() != null)
      {
         if(!target.getName().matches(getNameExpression()))
         {
            return false;
         }
      }

      // Typed
      if(getType() != null)
      {
         if(!target.getType().equals(getType()))
         {
            return false;
         }
      }

      // Annotated
      if(!QueryUtil.matchAnnotations(target, getAnnotations()))
      {
         return false;
      }
      return true;
   }

   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#getActualClass()
    */
   @Override
   protected Class<? extends FieldQuery> getActualClass()
   {
      return ReflectionFieldQuery.class;
   }
}
