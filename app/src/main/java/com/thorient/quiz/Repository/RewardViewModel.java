package com.thorient.quiz.Repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.thorient.quiz.model.CategoryResponse;
import com.thorient.quiz.model.ContactResponse;
import com.thorient.quiz.model.LoginResponse;
import com.thorient.quiz.model.QuestionResponse;
import com.thorient.quiz.model.QuizResponse;
import com.thorient.quiz.model.RegistrationResponse;

public class RewardViewModel extends ViewModel {

    private final RewardRepository repository;
    private final LiveData<CategoryResponse> categoryResponseLiveData;
    private final LiveData<QuizResponse> quizLiveData;
    private LiveData<QuestionResponse> questionResponseLiveData;
    private LiveData<RegistrationResponse> registrationResponseLiveData;
    private LiveData<LoginResponse> loginResponseLiveData;
    private LiveData<ContactResponse> contactResponseLiveData;

    public RewardViewModel() {
        repository = new RewardRepository();
        categoryResponseLiveData = repository.getCategories();
        quizLiveData = repository.getQuizzes();
        questionResponseLiveData = repository.getQuestions();
    }

    public LiveData<CategoryResponse> getCategory() {
        return categoryResponseLiveData;
    }

    public LiveData<QuizResponse> getQuizLiveData() {
        return quizLiveData;
    }

    public LiveData<QuestionResponse> getQuestionResponseLiveData() {
        return questionResponseLiveData;
    }

    public LiveData<RegistrationResponse> getRegistrationResponseLiveData(String json) {
        registrationResponseLiveData = repository.getRegisterUser(json);
        return registrationResponseLiveData;
    }

    public LiveData<LoginResponse> loginUser(String json) {
        loginResponseLiveData = repository.getLoginUser(json);
        return loginResponseLiveData;
    }

    public LiveData<ContactResponse> submitContactForm(String json) {
        contactResponseLiveData = repository.getContactForm(json);
        return contactResponseLiveData;
    }
}
