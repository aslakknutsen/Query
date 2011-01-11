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

import static org.jboss.query.reflection.impl.AssertList.containsMethodName;

import java.lang.reflect.Method;
import java.util.Collection;

import org.jboss.query.reflection.impl.test.Loadable;
import org.junit.Assert;
import org.junit.Test;

/**
 * MethodQueryTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class MethodQueryTestCase extends AbstractTestBase
{
   @Test
   public void shouldFindMethodWithTypeAndAnnotation() throws Exception
   {
      Collection<Method> result = run(
            Query.forMethod()
               .withAnnotation(Loadable.class)
               .withType(String.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(1, result.size());
      Assert.assertTrue(containsMethodName("methodOne", result));
   }
}
