package com.example.salomon.aplicacionmovil.presenter;

import android.content.Context;
import android.icu.lang.UCharacter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Salomon on 27/03/2018.
 */

public abstract class Presenter<T extends Presenter.view> {
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private T view;

    public T getView(){
        return view;
    }

    public void setView(T view){
        this.view = view;
    }

    public void initialize(){
    }

    public void terminate(){
        dispose();
    }

    void addDisposableObserver(Disposable disposable) {
        compositeDisposable.add(disposable);
    }


    private void dispose() {
        if(!compositeDisposable.isDisposed()){
            compositeDisposable.dispose();
        }
    }

    public interface view{
        Context context();
    }
}
