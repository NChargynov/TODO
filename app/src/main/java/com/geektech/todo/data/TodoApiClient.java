package com.geektech.todo.data;

import android.util.Log;

import com.geektech.todo.model.TodoAction;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class TodoApiClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    TodoApi client = retrofit.create(TodoApi.class);

    public void getAction(String type, Integer participants,
                          Float price, Float maxPrice, Float minPrice,
                          Float accessibility, Float minAccessibility,
                          Float maxAccessibility, final TodoActionCallback callBack) {
        Call<TodoAction> call = client.getAction(type, maxPrice, minPrice,
                minAccessibility, maxAccessibility);


        call.enqueue(new CoreCallback<TodoAction>() {
            @Override
            void onSuccess(TodoAction result) {
                callBack.onSuccess(result);
            }

            @Override
            void onFailure(Exception result) {
                callBack.onFailure(result);
            }
        });

    }

    public interface TodoActionCallback extends TodoBaseCallBack<TodoAction> {

    }

    public interface TodoBaseCallBack<A> {
        void onSuccess(A result);
        void onFailure(Exception exception);
    }

    public interface TodoApi {

        @GET("api/activity/")
        Call<TodoAction> getAction(
                @Query("type") String type,
                @Query("minprice") Float minPrice,
                @Query("maxprice") Float maxPrice,
                @Query("minaccessibility") Float minAccessibility,
                @Query("maxaccessibility") Float maxAccessibility);
    }

}
