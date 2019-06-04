package com.z4hyoung.livedatasamples;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

class MyViewModel extends ViewModel {

    private final String mPrefix;

    private int mNumber = 1;

    // 构造函数带有参数，使用自定义的Factory
    MyViewModel(@NonNull String name) {
        mPrefix = name;
    }

    private final MutableLiveData<String> mText = new MutableLiveData<>();
    private final MutableLiveData<Integer> mSeq = new MutableLiveData<>();

    private final BiFunction<Integer, Integer, Integer> mFunction = new BiFunction<Integer, Integer, Integer>() {
        @NonNull
        @Override
        public Integer apply(@NonNull Integer integer1, @NonNull Integer integer2) {
            return integer1 + integer2;
        }
    };

    // 自然数和的数列
    private final LiveData<Integer> mAccumulatorSeq = MyTransformations.scan(mSeq, mFunction);

    LiveData<String> getText() {
        return mText;
    }

    LiveData<Integer> getSequence() {
        return mAccumulatorSeq;
    }

    void updateText(@NonNull String text) {
        mText.setValue(mPrefix + "_" + text);
    }

    void increaseSequence() {
        mSeq.setValue(mNumber++);
    }
}
