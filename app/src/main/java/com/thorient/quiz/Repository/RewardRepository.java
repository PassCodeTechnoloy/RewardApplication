package com.thorient.quiz.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.thorient.quiz.interfaces.ApiClient;
import com.thorient.quiz.interfaces.ApiService;
import com.thorient.quiz.model.CategoryResponse;
import com.thorient.quiz.model.ContactResponse;
import com.thorient.quiz.model.LoginResponse;
import com.thorient.quiz.model.QuestionResponse;
import com.thorient.quiz.model.QuizResponse;
import com.thorient.quiz.model.RegistrationResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RewardRepository {
    private final ApiService apiService;

    public RewardRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }


    public LiveData<CategoryResponse> getCategories() {
        MutableLiveData<CategoryResponse> liveData = new MutableLiveData<>();

        apiService.getCategories(true).enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {

            }
        });
       /* apiService.getVideos().enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {
                // Handle failure (e.g., show error message)
            }
        });*/
        return liveData;
    }

    public LiveData<QuizResponse> getQuizzes() {
        MutableLiveData<QuizResponse> quizMutableLiveData = new MutableLiveData<>();
        apiService.getQuizzes(true).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    quizMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {

            }
        });
        return quizMutableLiveData;
    }

    public LiveData<QuestionResponse> getQuestions() {
        MutableLiveData<QuestionResponse> quizResponseMutableLiveData = new MutableLiveData<>();
        apiService.getQuestions(true).enqueue(new Callback<QuestionResponse>() {
            @Override
            public void onResponse(Call<QuestionResponse> call, Response<QuestionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    quizResponseMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<QuestionResponse> call, Throwable t) {

            }
        });
        return quizResponseMutableLiveData;
    }


    public LiveData<RegistrationResponse> getRegisterUser(String json) {
        MutableLiveData<RegistrationResponse> rewardRepositoryMutableLiveData = new MutableLiveData<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        apiService.registerUser(requestBody).enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    rewardRepositoryMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
        return rewardRepositoryMutableLiveData;
    }

    public LiveData<LoginResponse> getLoginUser(String json) {
        MutableLiveData<LoginResponse> rewardRepositoryMutableLiveData = new MutableLiveData<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        apiService.loginUser(requestBody).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response != null) {
                    rewardRepositoryMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("response_", "onResponse: error" + t.getMessage());
            }
        });
        return rewardRepositoryMutableLiveData;
    }

    public LiveData<ContactResponse> getContactForm(String json) {
        MutableLiveData<ContactResponse> rewardRepositoryMutableLiveData = new MutableLiveData<>();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        apiService.submitContactForm(requestBody).enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                if (response.isSuccessful() && response != null) {
                    rewardRepositoryMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });

        return rewardRepositoryMutableLiveData;
    }
}
