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

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.annotations.Instantiator;
import org.objenesis.instantiator.annotations.Typology;

/**
 * 该实例化器永远只返回null
 *
 * @author Henri Tremblay
 */
@Instantiator(Typology.NOT_COMPLIANT)
public class NullInstantiator<T> implements ObjectInstantiator<T> {

   public NullInstantiator(Class<T> type) {
   }

   /**
    * @return 永远返回null
    */
   @Override
   public T newInstance() {
      return null;
   }
}
