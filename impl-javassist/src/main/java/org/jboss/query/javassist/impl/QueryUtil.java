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

import java.util.Collection;

import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;

/**
 * QueryUtil
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
final class QueryUtil
{
   private QueryUtil() {}
   
   
   public static boolean matchAnnotations(Collection<String> search, AnnotationsAttribute annotation)
   {
      if(annotation == null && search.size() > 0)
      {
         return false;
      }
      for(String annotationType : search)
      {
         if(!contains(annotationType, annotation.getAnnotations()))
         {
            return false;
         }
      }
      return true;
   }
   
   private static boolean contains(String annotationType, Annotation[] annotations)
   {
      if(annotations == null || annotations.length == 0)
      {
         return false;
      }
      for(Annotation annotation : annotations)
      {
         if(annotation.getTypeName().equals(annotationType))
         {
            return true;
         }
      }
      return false;
   }

}
