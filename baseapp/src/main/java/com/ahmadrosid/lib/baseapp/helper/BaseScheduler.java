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

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ocittwo on 1/28/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */
public enum BaseScheduler {
    BASE_SCHEDULER;

    public static BaseScheduler pluck() {
        return BASE_SCHEDULER;
    }

    private final Observable.Transformer newThread;
    private final Observable.Transformer io;
    private final Observable.Transformer computation;

    BaseScheduler() {
        newThread = new Observable.Transformer() {
            @Override
            public Observable call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        io = new Observable.Transformer() {
            @Override
            public Observable call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };

        computation = new Observable.Transformer() {
            @Override
            public Observable call(Object o) {
                return ((Observable) o).subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public <T> Observable.Transformer<T, T> applySchedulers(Type type) {
        switch (type) {
            case NEW_THREAD:
                return (Observable.Transformer<T, T>) newThread;
            case IO:
                return (Observable.Transformer<T, T>) io;
            case COMPUTATION:
                return (Observable.Transformer<T, T>) computation;
        }

        return (Observable.Transformer<T, T>) newThread;
    }

    public enum Type {
        NEW_THREAD, IO, COMPUTATION
    }
}
