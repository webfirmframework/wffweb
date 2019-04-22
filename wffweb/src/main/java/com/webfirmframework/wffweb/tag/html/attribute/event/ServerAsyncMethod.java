/*
 * Copyright 2014-2019 Web Firm Framework
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
 */
package com.webfirmframework.wffweb.tag.html.attribute.event;

import java.io.Serializable;

import com.webfirmframework.wffweb.tag.html.AbstractHtml;
import com.webfirmframework.wffweb.tag.html.attribute.core.AbstractAttribute;
import com.webfirmframework.wffweb.wffbm.data.WffBMObject;

@FunctionalInterface
public interface ServerAsyncMethod extends Serializable {

    public static class Event {

        private AbstractHtml sourceTag;

        private String serverMethodName;

        private final AbstractAttribute sourceAttribute;

        private final Object serverSideData;

        public Event(final String serverMethodName) {
            this.serverMethodName = serverMethodName;
            sourceAttribute = null;
            serverSideData = null;
        }

        public Event(final AbstractHtml sourceTag,
                final AbstractAttribute sourceAttribute) {
            super();
            this.sourceTag = sourceTag;
            this.sourceAttribute = sourceAttribute;
            serverSideData = null;
        }

        /**
         * @param serverMethodName
         * @param serverSideData
         * @since 3.0.2
         */
        public Event(final String serverMethodName,
                final Object serverSideData) {
            sourceAttribute = null;
            this.serverMethodName = serverMethodName;
            this.serverSideData = serverSideData;
        }

        /**
         * @param sourceTag
         * @param sourceAttribute
         * @param serverSideData
         * @since 3.0.2
         */
        public Event(final AbstractHtml sourceTag,
                final AbstractAttribute sourceAttribute,
                final Object serverSideData) {
            super();
            this.sourceTag = sourceTag;
            this.sourceAttribute = sourceAttribute;
            this.serverSideData = serverSideData;
        }

        /**
         * @return the sourceTag
         */
        public AbstractHtml getSourceTag() {
            return sourceTag;
        }

        /**
         * @param sourceTag
         *                      the sourceTag to set
         * @deprecated The use of this method is not encouraged. Use constructor
         *             initialization instead.
         */
        @Deprecated
        public void setSourceTag(final AbstractHtml sourceTag) {
            this.sourceTag = sourceTag;
        }

        public String getServerMethodName() {
            return serverMethodName;
        }

        /**
         * @param serverMethodName
         * @author WFF
         * @deprecated The use of this method is not encouraged. Use constructor
         *             initialization instead.
         */
        @Deprecated
        public void setServerMethodName(final String serverMethodName) {
            this.serverMethodName = serverMethodName;
        }

        /**
         * the source attribute from which the event is generated.
         *
         * @return the sourceAttribute
         * @since 2.1.2
         */
        public AbstractAttribute getSourceAttribute() {
            return sourceAttribute;
        }

        /**
         * @return the server side data passed in the event attribute argument
         * @since 3.0.2
         */
        public Object getServerSideData() {
            return serverSideData;
        }

    }

    public abstract WffBMObject asyncMethod(WffBMObject data, Event event);

}
