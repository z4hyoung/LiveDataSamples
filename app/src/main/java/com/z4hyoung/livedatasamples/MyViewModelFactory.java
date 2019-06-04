package com.z4hyoung.livedatasamples;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

// 自定义Factory
class MyViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final MyViewModel mModel;

    MyViewModelFactory(@NonNull String name) {
        mModel = new MyViewModel(name);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isInstance(mModel)) {
            //noinspection unchecked
            return (T) mModel;
        } else {
            return super.create(modelClass);
        }
    }
}
