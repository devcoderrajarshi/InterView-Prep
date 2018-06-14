package com.example.rajarshi.interviewprep.recyclerViewAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.rajarshi.interviewprep.R;

import java.util.List;

public class InterviewAdapter extends RecyclerView.Adapter<InterviewAdapter.MyViewHolder> {

    private List<QuestionAnswerModel> questionAnswersList;
    private Context ctx;

    public InterviewAdapter(List<QuestionAnswerModel> questionAnswersList, Context context) {
        this.questionAnswersList = questionAnswersList;
        ctx = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_question_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        QuestionAnswerModel questionAnswer = questionAnswersList.get(position);
        holder.question.setText(questionAnswer.getQuestions());
        holder.answer.setText(questionAnswer.getAnswers());
        animate(holder);
    }

    @Override
    public int getItemCount() {
        return questionAnswersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView question, answer;

        public MyViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.interviewQuestion);
            answer = (TextView) view.findViewById(R.id.interviewAnswer);
        }
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(ctx, R.anim.fadein);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
