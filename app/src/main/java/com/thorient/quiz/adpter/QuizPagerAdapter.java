package com.thorient.quiz.adpter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thorient.quiz.R;
import com.thorient.quiz.interfaces.ApiClient;
import com.thorient.quiz.interfaces.ApiService;
import com.thorient.quiz.model.Category;
import com.thorient.quiz.model.Quiz;
import com.thorient.quiz.model.QuizResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizPagerAdapter extends RecyclerView.Adapter<QuizPagerAdapter.QuizViewHolder> {
    private Context context;
    private List<Category> categories;

    public QuizPagerAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        Category category = categories.get(position);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        apiService.getQuizzes(true).enqueue(new Callback<QuizResponse>() {
            @Override
            public void onResponse(Call<QuizResponse> call, Response<QuizResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<Quiz> quizzes = response.body().getData();
                    List<Quiz> categoryQuizzes = new ArrayList<>();
                    for (Quiz quiz : quizzes) {
                        if (quiz.getCategoryId() == category.getId()) {
                            categoryQuizzes.add(quiz);
                        }
                    }
                    holder.bind(categoryQuizzes);
                }
            }

            @Override
            public void onFailure(Call<QuizResponse> call, Throwable t) {
                Log.d("checkactivity","onFailure===="+call);
                // Handle failure
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class QuizViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerView;

        public QuizViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.quizRecyclerView);

            recyclerView.setLayoutManager(new LinearLayoutManager(context));

        }

        public void bind(List<Quiz> quizzes) {

            QuizListAdapter quizListAdapter = new QuizListAdapter(context, quizzes);
            recyclerView.setAdapter(quizListAdapter);
        }
    }
}

