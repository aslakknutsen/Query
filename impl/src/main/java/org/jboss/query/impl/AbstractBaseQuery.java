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
package org.jboss.query.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.query.api.Annotated;
import org.jboss.query.api.Executable;
import org.jboss.query.api.Generic;
import org.jboss.query.api.Named;
import org.jboss.query.api.Parameterized;
import org.jboss.query.api.Typed;

/**
 * BaseClassQuery
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public abstract class AbstractBaseQuery<T, INPUT, ANNOTATION, RESULT> implements 
         Executable<RESULT, INPUT>, 
         Annotated<T, ANNOTATION>, 
         Typed<T, INPUT>, 
         Parameterized<T, INPUT>,
         Generic<T, INPUT>,
         Named<T>
{
   private Set<INPUT> genericTypes = null;
   private Set<INPUT> parameterTypes = null;
   private Set<ANNOTATION> annotations = null;
   private INPUT type = null;
   private String nameExpression;
   
   public AbstractBaseQuery()
   {
      genericTypes = new HashSet<INPUT>();
      parameterTypes = new HashSet<INPUT>();
      annotations = new HashSet<ANNOTATION>();
   }

   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.api.Generic#withGenericType(java.lang.Object)
    */
   @Override
   public T withGenericType(INPUT genericType)
   {
      this.genericTypes.add(genericType);
      return covarientReturn();
   }

   @Override
   public T withParameterType(INPUT parameterType)
   {
      this.parameterTypes.add(parameterType);
      return covarientReturn();
   }

   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.api.Typed#withType(java.lang.Object)
    */
   @Override
   public T withType(INPUT type)
   {
      this.type = type;
      return covarientReturn();
   }

   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.api.Annotated#withAnnotation(java.lang.Object)
    */
   @Override
   public T withAnnotation(ANNOTATION annotation)
   {
      this.annotations.add(annotation);
      return covarientReturn();
   }

   /* (non-Javadoc)
    * @see org.jboss.query.api.Named#withName(java.lang.String)
    */
   @Override
   public T withName(String expression)
   {
      this.nameExpression = expression;
      return covarientReturn();
   }
   
   protected Set<ANNOTATION> getAnnotations()
   {
      return annotations;
   }
   
   protected Set<INPUT> getGenericTypes()
   {
      return genericTypes;
   }
   
   protected Set<INPUT> getParameterTypes()
   {
      return parameterTypes;
   }
   
   protected INPUT getType()
   {
      return type;
   }
   
   /**
    * @return the nameExpression
    */
   public String getNameExpression()
   {
      return nameExpression;
   }
   
   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.api.Executable#run(X[])
    */
   @Override
   public Collection<RESULT> run(INPUT... sources)
   {
      return run(Arrays.asList(sources));
   }

   /* (non-Javadoc)
    * @see org.jboss.arquillian.impl.query.api.Executable#run(java.util.Collection)
    */
   @Override
   public Collection<RESULT> run(Collection<INPUT> sources)
   {
      List<RESULT> targets = new ArrayList<RESULT>(); // class
      List<RESULT> matches = new ArrayList<RESULT>(); // method
      for(INPUT source : sources)
      {
         targets.addAll(extractAllTargets(source));
      }
      for(RESULT target : targets)
      {
         if(match(target))
         {
            matches.add(target);
         }
      }
      return matches;
   }

   protected T covarientReturn() 
   {
      return getActualClass().cast(this);
   }

   protected abstract Class<? extends T> getActualClass();
   
   protected abstract Collection<RESULT> extractAllTargets(INPUT source);
   protected abstract boolean match(RESULT target);
}
