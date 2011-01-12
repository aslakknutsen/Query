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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import javassist.bytecode.AnnotationsAttribute;

import org.jboss.query.impl.AbstractBaseQuery;
import org.jboss.query.javassist.api.ClassQuery;

/**
 * {@link ClassQuery}
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class JavassistClassQuery 
   extends AbstractBaseQuery<ClassQuery, String, String, CtClass> 
   implements ClassQuery 
{
   private ClassPool pool = ClassPool.getDefault();
   
   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#extractAllTargets(java.lang.Object)
    */
   @Override
   protected Collection<CtClass> extractAllTargets(String source)
   {
      List<CtClass> result = new ArrayList<CtClass>();
      try
      {
         result.add(pool.get(source));
      }
      catch (NotFoundException e)
      {
         throw new RuntimeException(e);
      }
      return result;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.query.impl.AbstractBaseQuery#match(java.lang.Object)
    */
   @Override
   protected boolean match(CtClass target)
   {
      try
      {
         if(getGenericTypes().size() > 0)
         {
            throw new UnsupportedOperationException("GenericTypes not supported by Javassist");
         }
         
         if(!QueryUtil.matchAnnotations(
               getAnnotations(), 
               (AnnotationsAttribute)target.getClassFile2().getAttribute(AnnotationsAttribute.visibleTag)))
         {
            return false;
         }
         
         if(getType() != null)
         {
            if(target.isInterface() || !target.subtypeOf(pool.get(getType())))
            {
               return false;
            }
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
   protected Class<? extends ClassQuery> getActualClass()
   {
      return JavassistClassQuery.class;
   }
}
