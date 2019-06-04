package com.z4hyoung.livedatasamples;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

final class MyTransformations {
    // 实现类似ReactiveX中的scan操作符，参考http://reactivex.io/documentation/operators/scan.html

    /**
     * 实现类似<a href="http://reactivex.io/documentation/operators/scan.html">ReactiveX中的scan操作符</a>
     */
    @MainThread
    static <T> LiveData<T> scan(@NonNull LiveData<T> source, @NonNull final BiFunction<T, T, T> scanFunction) {
        final MediatorLiveData<T> result = new MediatorLiveData<>();
        result.addSource(source, new Observer<T>() {
            boolean first = true;
            T accumulator;

            @Override
            public void onChanged(T t) {
                if (first) {
                    accumulator = t;
                    result.setValue(t);
                    first = false;
                } else {
                    accumulator = scanFunction.apply(accumulator, t);
                    result.setValue(accumulator);
                }
            }
        });
        return result;
    }
}
