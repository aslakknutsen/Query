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

import java.util.Arrays;
import java.util.Collection;

import javassist.ClassPool;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;

import org.jboss.query.impl.AbstractBaseQuery;
import org.jboss.query.javassist.api.MethodQuery;

/**
 * {@link MethodQuery}
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class JavassistMethodQuery 
   extends AbstractBaseQuery<MethodQuery, String, String, CtMethod> 
   implements MethodQuery
{
   private ClassPool pool = ClassPool.getDefault();

   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#extractAllTargets(java.lang.Object)
    */
   @Override
   protected Collection<CtMethod> extractAllTargets(String source)
   {
      try
      {
         return Arrays.asList(pool.get(source).getDeclaredMethods());
      }
      catch (NotFoundException e)
      {
         throw new RuntimeException(e);
      }
   }
   
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.impl.AbstractBaseQuery#match(java.lang.Object)
    */
   @Override
   protected boolean match(CtMethod target)
   {
      try
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
            if(!target.getReturnType().getName().equals(getType()))
            {
               return false;
            }
         }
         
         // Annotated
         if(!QueryUtil.matchAnnotations(
               getAnnotations(), 
               (AnnotationsAttribute)target.getMethodInfo2().getAttribute(AnnotationsAttribute.visibleTag)))
         {
            return false;
         }
      }
      catch (NotFoundException e)
      {
         throw new RuntimeException(e);
      }
      return true;
   }

   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#getActualClass()
    */
   @Override
   protected Class<? extends MethodQuery> getActualClass()
   {
      return JavassistMethodQuery.class;
   }
}
