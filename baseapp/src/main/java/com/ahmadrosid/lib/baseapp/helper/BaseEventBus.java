/*
 * Copyright (c) 2017 Ahmad Rosid.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.ahmadrosid.lib.baseapp.helper;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by ocittwo on 1/8/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public class BaseEventBus {
    public static BaseEventBus INSTANCE = new BaseEventBus();
    private final Subject<Object, Object> mSubject = new SerializedSubject<>(PublishSubject.create());

    public static BaseEventBus getInstance() {
        return INSTANCE;
    }

    public <T> Subscription register(final Class<T> tClass, Action1<T> onNext) {
        return mSubject
                .filter(o -> o.getClass().equals(tClass))
                .map(o -> (T) o)
                .subscribe(onNext);
    }

    public void post(Object event) {
        mSubject.onNext(event);
    }
}
