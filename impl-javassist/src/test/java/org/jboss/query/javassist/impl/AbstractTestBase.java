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

import org.jboss.query.javassist.api.ClassQuery;
import org.jboss.query.javassist.api.FieldQuery;
import org.jboss.query.javassist.api.MethodQuery;
import org.jboss.query.javassist.impl.test.QueryTestInterface;
import org.jboss.query.javassist.impl.test.QueryTestScenario;
import org.jboss.query.javassist.impl.test.QueryTestScenario2;

/**
 * AbstractTestBase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public abstract class AbstractTestBase
{
   private String[] searchables = new String[] {
         QueryTestInterface.class.getName(),
         QueryTestScenario.class.getName(),
         QueryTestScenario2.class.getName()
   };
   
   protected Collection<CtClass> run(ClassQuery query)
   {
      return query.run(searchables);
   }

   protected Collection<CtMethod> run(MethodQuery query)
   {
      return query.run(searchables);
   }

   protected Collection<CtField> run(FieldQuery query)
   {
      return query.run(searchables);
   }
}
