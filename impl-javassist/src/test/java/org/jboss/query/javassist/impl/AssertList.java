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

import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;

/**
 * AssertList
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public final class AssertList
{
   private AssertList() { }
   
   public static boolean containsFieldName(String name, Collection<CtField> result) 
   {
      for(CtField desc : result)
      {
         if(desc.getName().equals(name))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean containsMethodName(String name, Collection<CtMethod> result) 
   {
      for(CtMethod desc : result)
      {
         if(desc.getName().equals(name))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean containsClassName(String name, Collection<CtClass> result) 
   {
      for(CtClass desc : result)
      {
         if(desc.getSimpleName().equals(name))
         {
            return true;
         }
      }
      return false;
   }
}