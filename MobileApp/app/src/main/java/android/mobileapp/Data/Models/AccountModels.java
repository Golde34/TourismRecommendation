package android.mobileapp.Data.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.mobileapp.Data.Database.AccountDB;
import android.mobileapp.Data.Entity.Account;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AccountModels {
    public static final String TAG = AccountModels.class.getName();

    private Context context;

    private AccountModels(Context context) {
        this.context = context;
    }

    public static AccountModels with(Context context) {
        return new AccountModels(context);
    }

    @SuppressLint("CheckResult")
    public void populateDB() {
        Completable.fromAction(() -> AccountDB.getInstance(context).accountDAO().insertUserAccount())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDBPopulationSuccess, this::onDBPopulationFailure);
    }

    private Account[] accountList() {
        List<Account> accounts = new ArrayList<>();
//        String[] accountNames = context.getResources().getStringArray(R.array.pokemon_names);
        String[] accountNames = null;
        for (int i = 0; i < accountNames.length; i++)
            accounts.add(new Account(Integer.toString(i + 1), accountNames[i]));
        return accounts.toArray(new Account[accounts.size()]);
    }

    private void onDBPopulationSuccess() {
        Log.d(TAG, "Accounts inserted successfully");
    }

    private void onDBPopulationFailure(Throwable t) {
        Log.e(TAG, "Accounts failed to be inserted, error:" + t.getMessage());
    }
}
