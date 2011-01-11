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

import static org.jboss.query.reflection.impl.AssertList.containsFieldName;

import java.lang.reflect.Field;
import java.util.Collection;

import org.jboss.query.reflection.impl.test.Loadable;
import org.jboss.query.reflection.impl.test.Testable;
import org.junit.Assert;
import org.junit.Test;

/**
 * FieldQueryTestCase
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
public class FieldQueryTestCase extends AbstractTestBase
{
   @Test
   public void shouldFindFieldsWithAnnotation() throws Exception
   {
      Collection<Field> result = run(
            Query.forField()
               .withAnnotation(Loadable.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(2, result.size());
      Assert.assertTrue(containsFieldName("fieldOne", result));
      Assert.assertTrue(containsFieldName("fieldTwo", result));
   }

   @Test
   public void shouldFindFieldWithTypeAndAnnotation() throws Exception
   {
      Collection<Field> result = run(
            Query.forField()
               .withAnnotation(Testable.class)
               .withType(String.class));
      
      Assert.assertNotNull(result);
      Assert.assertEquals(1, result.size());
      Assert.assertTrue(containsFieldName("fieldOne", result));
   }
}
