/*
 * Copyright since 2014 Web Firm Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @author WFF
 */
package com.webfirmframework.wffweb.data;

import java.io.Serializable;

/**
 *
 * @author WFF
 * @since 1.0.0
 */
public interface Bean extends Serializable, Cloneable {
    /**
     * @return the value of the bean.
     * @author WFF
     * @since 1.0.0
     */
    public abstract String getValue();
}
