package com.thorient.quiz.adpter;

import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.thorient.quiz.model.Category;
import com.thorient.quiz.ui.QuizFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final List<Category> dataList;
    private TextView tv_coin;

    public ViewPagerAdapter(FragmentActivity fragmentActivity, List<Category> dataList, TextView tv_coin) {
        super(fragmentActivity);
        this.dataList = dataList;
        this.tv_coin = tv_coin;
    }

    @Override
    public Fragment createFragment(int position) {
        return new QuizFragment(dataList.get(position).getId(), tv_coin);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}