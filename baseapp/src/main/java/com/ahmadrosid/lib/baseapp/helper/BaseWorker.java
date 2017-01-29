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

/**
 * Created by ocittwo on 1/28/17.
 *
 * @Author Ahmad Rosid
 * @Email ocittwo@gmail.com
 * @Github https://github.com/ar-android
 * @Web http://ahmadrosid.com
 */

public enum BaseWorker {
    BASE_WORKER;

    public static BaseWorker pluck() {
        return BASE_WORKER;
    }

    public Observable<Object> doInComputation(final Runnable runnable) {
        return Observable.create(subscriber -> {
            runnable.run();
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext("We are done bro!");
                subscriber.onCompleted();
            }
        }).compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.COMPUTATION));
    }

    public Observable<Object> doInIO(final Runnable runnable) {
        return Observable.create(subscriber -> {
            runnable.run();
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext("We are done bro!");
                subscriber.onCompleted();
            }
        }).compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.IO));
    }

    public Observable<Object> doInNewThread(final Runnable runnable) {
        return Observable.create(subscriber -> {
            runnable.run();
            if (!subscriber.isUnsubscribed()) {
                subscriber.onNext("We are done bro!");
                subscriber.onCompleted();
            }
        }).compose(BaseScheduler.pluck().applySchedulers(BaseScheduler.Type.NEW_THREAD));
    }
}
