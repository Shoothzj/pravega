/**
 * Copyright Pravega Authors.
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
package io.pravega.segmentstore.server.logs;

import io.pravega.common.io.SerializationException;
import io.pravega.test.common.ErrorInjector;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Serializer for TestLogItem.
 */
class TestSerializer implements Serializer<TestLogItem> {
    private ErrorInjector<SerializationException> deserializationErrorInjector;

    void setDeserializationErrorInjector(ErrorInjector<SerializationException> injector) {
        this.deserializationErrorInjector = injector;
    }

    @Override
    public void serialize(OutputStream output, TestLogItem item) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public TestLogItem deserialize(InputStream input) throws IOException {
        ErrorInjector<SerializationException> errorInjector = this.deserializationErrorInjector;
        if (errorInjector != null) {
            errorInjector.throwIfNecessary();
        }

        return new TestLogItem(input);
    }
}
