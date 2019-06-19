package com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adrosonic.adrocafe.loginmvvm.data.SingleLiveEvent;
import com.adrosonic.adrocafe.loginmvvm.data.User;
import com.adrosonic.adrocafe.loginmvvm.repository.local.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class AuthViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
    }

    private MutableLiveData<String> editTextUsername = new MutableLiveData<String>();

    private MutableLiveData<String> editTextPassword = new MutableLiveData<String>();

    private SingleLiveEvent<String> _navigateTo = new SingleLiveEvent<>();

    public LiveData<String> navigateTo = _navigateTo;


    public MutableLiveData<String> getEditTextUsername() {
        return editTextUsername;
    }

    public void setEditTextUsername(MutableLiveData<String> editTextUsername) {
        this.editTextUsername = editTextUsername;
    }

    public MutableLiveData<String> getEditTextPassword() {
        return editTextPassword;
    }

    public void setEditTextPassword(MutableLiveData<String> editTextPassword) {
        this.editTextPassword = editTextPassword;
    }

    public void onLoginClick(){
        if (getEditTextUsername().getValue() != null && getEditTextPassword().getValue() != null){
            String username = getEditTextUsername().getValue();
            final String password = getEditTextPassword().getValue();
            appDatabase.userDao().getUserByName(username)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableSubscriber<User>() {
                        @Override
                        public void onNext(User user) {
                            if (user.getPassword().equals(password)){
                                Log.i("Login", "Successful");
                                _navigateTo.setValue("Landing");
                            }else {
                                Log.i("Login", "Unsuccessful");
                                _navigateTo.setValue("Landing");
                            }
                        }

                        @Override
                        public void onError(Throwable t) {
                            Log.i("get user",t.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.i("get user", "complete");
                        }
                    });
        }else Log.i("Value","null");
    }

    public void onSignUpClick(){
        _navigateTo.setValue("Landing");
    }

}
