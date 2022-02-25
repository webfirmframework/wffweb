/*
 * Copyright 2014-2022 Web Firm Framework
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
package com.webfirmframework.wffweb.server.page;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

/**
 * Note: only for internal use.
 *
 * @since 12.0.0-beta.4
 */
final class LocalStorageImpl implements LocalStorage {

    private final AtomicInteger getItemIdGenerator = new AtomicInteger(0);

    private final AtomicInteger setItemIdGenerator = new AtomicInteger(0);

    private final AtomicInteger setTokenIdGenerator = new AtomicInteger(0);

    private final AtomicInteger removeItemIdGenerator = new AtomicInteger(0);

    private final AtomicInteger clearItemsIdGenerator = new AtomicInteger(0);

    // key: id by setItemIdGenerator
    final Map<Integer, LSConsumerEventRecord> setItemConsumers = new ConcurrentHashMap<>(2);

    // key: id by getItemIdGenerator
    final Map<Integer, LSConsumerEventRecord> getItemConsumers = new ConcurrentHashMap<>(2);

    // key: id by removeItemIdGenerator
    final Map<Integer, LSConsumerEventRecord> removeItemConsumers = new ConcurrentHashMap<>(2);

    // key: id by clearItemsIdGenerator
    final Map<Integer, LSConsumerEventRecord> clearItemsConsumers = new ConcurrentHashMap<>(2);

    private final Map<String, BrowserPage> browserPages;

    final Map<String, TokenWrapper> tokenWrapperByKey = new ConcurrentHashMap<>(2);

    LocalStorageImpl(final Map<String, BrowserPage> browserPages) {
        this.browserPages = browserPages;
    }

    @Override
    public void setItem(final String key, final String value) {
        this.setItem(key, value, null);
    }

    @Override
    public void setItem(final String key, final String value, final Consumer<LocalStorage.Event> successConsumer) {

        if (value == null) {
            removeAndGetItem(key, successConsumer);
            return;
        }
        final int id = setItemIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();
        final boolean callback = successConsumer != null;
        if (callback) {
            final LSConsumerEventRecord record = new LSConsumerEventRecord(successConsumer,
                    new LocalStorage.Event(key, operationTimeMillis, new ItemData(value, operationTimeMillis)));
            setItemConsumers.put(id, record);
        }

        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.setLocalStorageItem(id, key, value, operationTimeMillis, callback);
        }
    }

    @Override
    public void getItem(final String key, final Consumer<Event> consumer) {
        Objects.requireNonNull(consumer);
        final int id = getItemIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();
        final LSConsumerEventRecord record = new LSConsumerEventRecord(consumer,
                new LocalStorage.Event(key, operationTimeMillis));
        getItemConsumers.put(id, record);
        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.getLocalStorageItem(id, key);
        }
    }

    @Override
    public void removeItem(final String key) {
        this.removeItem(key, null);
    }

    @Override
    public void removeItem(final String key, final Consumer<Event> consumer) {

        final int id = removeItemIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();

        final boolean callback = consumer != null;
        if (callback) {
            final LSConsumerEventRecord record = new LSConsumerEventRecord(consumer,
                    new LocalStorage.Event(key, operationTimeMillis));
            removeItemConsumers.put(id, record);
        }

        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.removeLocalStorageItem(id, key, operationTimeMillis, callback);
        }
    }

    @Override
    public void removeAndGetItem(final String key, final Consumer<Event> consumer) {
        Objects.requireNonNull(consumer);
        final int id = removeItemIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();
        final LSConsumerEventRecord record = new LSConsumerEventRecord(consumer,
                new LocalStorage.Event(key, operationTimeMillis));
        removeItemConsumers.put(id, record);
        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.removeAndGetLocalStorageItem(id, key, operationTimeMillis);
        }
    }

    @Override
    public void clear() {
        this.clear(null);
    }

    @Override
    public void clearItems() {
        this.clearItems(null);
    }

    @Override
    public void clearTokens() {
        clearTokens(null);
    }

    @Override
    public void clear(final Consumer<Event> consumer) {
        final int id = clearItemsIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();
        final boolean callback = consumer != null;
        if (callback) {
            final LSConsumerEventRecord record = new LSConsumerEventRecord(consumer,
                    new LocalStorage.Event(operationTimeMillis));
            clearItemsConsumers.put(id, record);
        }

        removeAllTokens(operationTimeMillis);

        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.clearLocalStorage(id, operationTimeMillis, callback);
        }
    }

    private void removeAllTokens(final long operationTimeMillis) {
        final Queue<String> toRemoveTokens = new ArrayDeque<>();
        for (final Map.Entry<String, TokenWrapper> entry : tokenWrapperByKey.entrySet()) {
            if (operationTimeMillis >= entry.getValue().getUpdatedTimeMillis()) {
                toRemoveTokens.add(entry.getKey());
            }
        }
        String key = null;
        while ((key = toRemoveTokens.poll()) != null) {
            tokenWrapperByKey.remove(key);
        }
    }

    @Override
    public void clearItems(final Consumer<Event> consumer) {
        final int id = clearItemsIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();
        final boolean callback = consumer != null;
        if (callback) {
            final LSConsumerEventRecord record = new LSConsumerEventRecord(consumer,
                    new LocalStorage.Event(operationTimeMillis));
            clearItemsConsumers.put(id, record);
        }
        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.clearLocalStorageItems(id, operationTimeMillis, callback);
        }
    }

    @Override
    public void clearTokens(final Consumer<Event> consumer) {
        final int id = clearItemsIdGenerator.incrementAndGet();
        final long operationTimeMillis = System.currentTimeMillis();
        final boolean callback = consumer != null;
        if (consumer != null) {
            final LSConsumerEventRecord record = new LSConsumerEventRecord(consumer,
                    new LocalStorage.Event(operationTimeMillis));
            clearItemsConsumers.put(id, record);
        }
        removeAllTokens(operationTimeMillis);
        for (final BrowserPage browserPage : browserPages.values()) {
            browserPage.clearLocalStorageTokens(id, operationTimeMillis, callback);
        }
    }

    @Override
    public synchronized void setToken(final String key, final String value) {
        if (key == null) {
            return;
        }
        final long operationTimeMillis = System.currentTimeMillis();
        if (value != null) {
            final TokenWrapper tokenWrapper = tokenWrapperByKey.computeIfAbsent(key, k -> new TokenWrapper());
            final long stamp = tokenWrapper.lock.writeLock();
            try {
                final String localToken = tokenWrapper.getValue();
                if (!value.equals(localToken)) {
                    final Collection<BrowserPage> bps = browserPages.values();
                    if (bps.size() > 0) {
                        final int id = setTokenIdGenerator.incrementAndGet();
                        final boolean updated = tokenWrapper.setTokenAndWriteTime(value, null, operationTimeMillis, id);
                        if (updated) {
                            for (final BrowserPage browserPage : bps) {
                                browserPage.setLocalStorageToken(id, key, value, operationTimeMillis);
                            }
                        }
                    }
                }
            } finally {
                tokenWrapper.lock.unlockWrite(stamp);
            }
        } else {
            final TokenWrapper tokenWrapper = tokenWrapperByKey.get(key);
            if (tokenWrapper != null) {
                final long stamp = tokenWrapper.lock.writeLock();
                try {
                    if (tokenWrapper.getValue() != null) {
                        final Collection<BrowserPage> bps = browserPages.values();
                        if (bps.size() > 0 && tokenWrapperByKey.remove(key) != null) {
                            final int id = setTokenIdGenerator.incrementAndGet();
                            final boolean updated = tokenWrapper.setTokenAndWriteTime(null, null, operationTimeMillis,
                                    id, key, tokenWrapperByKey);
                            if (updated) {
                                for (final BrowserPage browserPage : bps) {
                                    browserPage.removeLocalStorageToken(id, key, operationTimeMillis);
                                }
                            }
                        }
                    }
                } finally {
                    tokenWrapper.lock.unlockWrite(stamp);
                }

            }
        }
    }

    @Override
    public Item getToken(final String key) {
        final TokenWrapper tokenWrapper = tokenWrapperByKey.get(key);
        return tokenWrapper != null ? new TokenData(tokenWrapper.getValue(), tokenWrapper.getUpdatedTimeMillis())
                : null;
    }

    @Override
    public void removeToken(final String key) {
        setToken(key, null);
    }
}