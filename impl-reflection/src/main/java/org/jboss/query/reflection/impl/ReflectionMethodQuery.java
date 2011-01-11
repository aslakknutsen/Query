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
import java.util.Arrays;
import java.util.Collection;

import org.jboss.query.impl.AbstractBaseQuery;
import org.jboss.query.reflection.api.MethodQuery;

/**
 * {@link MethodQuery}
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class ReflectionMethodQuery 
   extends AbstractBaseQuery<MethodQuery, Class<?>, Class<? extends Annotation>, Method> 
   implements MethodQuery
{
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#extractAllTargets(java.lang.Object)
    */
   @Override
   protected Collection<Method> extractAllTargets(Class<?> source)
   {
      return Arrays.asList(source.getDeclaredMethods());
   }
   
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#match(java.lang.Object)
    */
   @Override
   protected boolean match(Method target)
   {
      if(!QueryUtil.matchAnnotations(target, getAnnotations()))
      {
         return false;
      }
      if(getType() != null)
      {
         if(!target.getReturnType().equals(getType()))
         {
            return false;
         }
      }
      if(getParameterTypes().size() > 0 && getParameterTypes().size() != target.getParameterTypes().length)
      {
         return false;
      }
      int index = 0;
      for(Class<?> paramType : getParameterTypes())
      {
         if(target.getParameterTypes()[index] != paramType)
         {
            return false;
         }
         index++;
      }
      return true;
   }

   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#getActualClass()
    */
   @Override
   protected Class<? extends MethodQuery> getActualClass()
   {
      return ReflectionMethodQuery.class;
   }
}
