package com.example.assignment1.data.source.remote;

public interface OnFetchDataJsonListener<T> {
    void onSuccess(T data);
}
