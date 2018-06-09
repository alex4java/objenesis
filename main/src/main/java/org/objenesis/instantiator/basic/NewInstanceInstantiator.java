/**
 * Copyright 2006-2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.objenesis.instantiator.basic;

import org.objenesis.ObjenesisException;
import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/**
 * 最简单的实例化器，调用Class.newInstance()来调用默认的public构造函数，仅仅是这样而已。
 *
 * @author Joe Walnes
 * @see ObjectInstantiator
 */
@Instantiator(Typology.NOT_COMPLIANT)
public class NewInstanceInstantiator<T> implements ObjectInstantiator<T> {

   private final Class<T> type;

   public NewInstanceInstantiator(Class<T> type) {
      this.type = type;
   }

   public T newInstance() {
      try {
         return type.newInstance();
      } catch (Exception e) {
         throw new ObjenesisException(e);
      }
   }

}
