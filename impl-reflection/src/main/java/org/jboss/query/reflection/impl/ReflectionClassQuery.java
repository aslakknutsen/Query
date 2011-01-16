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
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jboss.query.impl.AbstractBaseQuery;
import org.jboss.query.reflection.api.ClassQuery;

/**
 * {@link ClassQuery}
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ReflectionClassQuery 
   extends AbstractBaseQuery<ClassQuery, Class<?>, Class<? extends Annotation>, Class<?>> 
   implements ClassQuery 
{
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#extractAllTargets(java.lang.Object)
    */
   @Override
   protected Collection<Class<?>> extractAllTargets(Class<?> source)
   {
      List<Class<?>> result = new ArrayList<Class<?>>();
      result.add(source);
      return result;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#match(java.lang.Object)
    */
   @Override
   protected boolean match(Class<?> target)
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
         if(!getType().isAssignableFrom(target) || target.isInterface())
         {
            return false;
         }
      }
      
      // Annotated
      if(!QueryUtil.matchAnnotations(target, getAnnotations()))
      {
         return false;
      }
      
      // Generic
      if(getGenericTypes().size() > 0 && getGenericTypes().size() != target.getGenericInterfaces().length)
      {
         return false;
      }

      int index = 0;
      for(Class<?> genericType : getGenericTypes())
      {
         Type type = target.getGenericInterfaces()[index];
         if(type instanceof ParameterizedType)
         {
            ParameterizedType paramType = (ParameterizedType)type;
            for(Type actualType : paramType.getActualTypeArguments())
            {
               if(actualType != genericType)
               {
                  return false;
               }
            }
         }
         else
         {
            return false;
         }
      }
      return true;
   }

   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#getActualClass()
    */
   @Override
   protected Class<? extends ClassQuery> getActualClass()
   {
      return ReflectionClassQuery.class;
   }
}
