package com.adrosonic.adrocafe.loginmvvm.ui.modules.authentication;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.adrosonic.adrocafe.loginmvvm.data.SingleLiveEvent;
import com.adrosonic.adrocafe.loginmvvm.data.User;
import com.adrosonic.adrocafe.loginmvvm.repository.PrefManager;
import com.adrosonic.adrocafe.loginmvvm.repository.local.AppDatabase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public class AuthViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private PrefManager prefManager;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
        prefManager = new PrefManager(application);
    }

    private MutableLiveData<String> editTextUsername = new MutableLiveData<String>();

    private MutableLiveData<String> editTextPassword = new MutableLiveData<String>();

    private MutableLiveData<String> editTextPasswordConfirm = new MutableLiveData<String>();

    private SingleLiveEvent<String> _navigateTo = new SingleLiveEvent<>();

    public LiveData<String> navigateTo = _navigateTo;

    public ObservableBoolean isValid = new ObservableBoolean(false);

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

    public MutableLiveData<String> getEditTextPasswordConfirm(){
        return editTextPasswordConfirm;
    }

    public void setEditTextPasswordConfirm(MutableLiveData<String> editTextPasswordConfirm) {
        this.editTextPasswordConfirm = editTextPasswordConfirm;
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
                                prefManager.saveLogin(true);
                                _navigateTo.setValue("Landing");
                            }else {
                                Log.i("Login", "Unsuccessful");
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
        _navigateTo.setValue("Sign Up");
    }

    public void onRegisterClick(){
        if (getEditTextUsername().getValue() != null && getEditTextPassword().getValue() != null){
            String username = getEditTextUsername().getValue();
            final String password = getEditTextPassword().getValue();
            appDatabase.userDao().insertUser(new User(username, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {
                            Log.i("insert user","complete");
                            prefManager.saveLogin(true);
                            _navigateTo.setValue("Landing");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("insert user","incomplete");
                        }
                    });
        }
    }

}
