package com.example.rajarshi.interviewprep.recyclerViewAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rajarshi.interviewprep.R;

import java.util.List;

public class InterviewAdapter extends RecyclerView.Adapter<InterviewAdapter.MyViewHolder> {

    private List<QuestionAnswer> questionAnswersList;

    public InterviewAdapter(List<QuestionAnswer> questionAnswersList) {
        this.questionAnswersList = questionAnswersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_question_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        QuestionAnswer questionAnswer = questionAnswersList.get(position);
        holder.question.setText(questionAnswer.getQuestions());
        holder.answer.setText(questionAnswer.getAnswers());
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
}
