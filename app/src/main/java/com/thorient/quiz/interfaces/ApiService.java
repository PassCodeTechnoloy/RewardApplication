package com.thorient.quiz.interfaces;

import com.thorient.quiz.model.CategoryResponse;
import com.thorient.quiz.model.ContactResponse;
import com.thorient.quiz.model.LoginResponse;
import com.thorient.quiz.model.QuestionResponse;
import com.thorient.quiz.model.QuizResponse;
import com.thorient.quiz.model.RegistrationResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("category/get-quiz-categories")
    Call<CategoryResponse> getCategories(@Query("isActive") boolean isActive);

    @GET("quiz/get-quizzes")
    Call<QuizResponse> getQuizzes(@Query("isActive") boolean isActive);


    @GET("quiz/get-questions")
    Call<QuestionResponse> getQuestions(@Query("isActive") boolean isActive);

    @POST("user/registration")
    Call<RegistrationResponse> registerUser(@Body RequestBody request);

    @POST("user/login")
    Call<LoginResponse> loginUser(@Body RequestBody request);

    @POST("user/contact")
    Call<ContactResponse> submitContactForm(@Body RequestBody contactRequest);


}
