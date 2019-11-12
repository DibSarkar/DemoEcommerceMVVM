package com.app.nextgrocer.data.rest;

import com.google.gson.JsonElement;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.app.nextgrocer.data.rest.Status.ERROR;
import static com.app.nextgrocer.data.rest.Status.ERROR_STATUS;
import static com.app.nextgrocer.data.rest.Status.LOADING;
import static com.app.nextgrocer.data.rest.Status.SUCCESS;



/**
 * Created by ${Saquib} on 03-05-2018.
 */

public class ApiResponse {

    public final Status status;



    @Nullable
    public final Throwable error;

    private ApiResponse(Status status,@Nullable Throwable error) {
        this.status = status;

        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING,  null);
    }

    public static ApiResponse success() {
        return new ApiResponse(SUCCESS,null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, error);
    }
    public static ApiResponse error_status() {
        return new ApiResponse(ERROR_STATUS, null);
    }
}
