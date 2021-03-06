/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
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
package org.jboss.arquillian.showcase.extension.autodiscover;

import javax.enterprise.inject.Produces;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
@RunWith(Arquillian.class)
public class AutodiscoverCDITestCase
{
   @Deployment
   public static WebArchive deploy() {
      return ShrinkWrap.create(WebArchive.class)
            .addClasses(AccountService.class, Manager.class)
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }

   @Mock @Produces
   private static AccountService service;
   
   @Before
   public void setupMock() {
      Mockito.when(service.withdraw(100)).thenReturn(100);      
   }
   
   @Test
   public void shouldBeAbleToMock(Manager manager) throws Exception {
      Assert.assertEquals(100, manager.execute(100));
   }
}
