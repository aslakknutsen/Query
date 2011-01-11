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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * AssertList
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public final class AssertList
{
   private AssertList() { }
   
   public static boolean containsFieldName(String name, Collection<Field> result) 
   {
      for(Field desc : result)
      {
         if(desc.getName().equals(name))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean containsMethodName(String name, Collection<Method> result) 
   {
      for(Method desc : result)
      {
         if(desc.getName().equals(name))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean containsClassName(String name, Collection<Class<?>> result) 
   {
      for(Class<?> desc : result)
      {
         if(desc.getSimpleName().equals(name))
         {
            return true;
         }
      }
      return false;
   }
}
