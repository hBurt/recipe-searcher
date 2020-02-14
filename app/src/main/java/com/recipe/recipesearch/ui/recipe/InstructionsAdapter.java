package com.recipe.recipesearch.ui.recipe;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.recipesearch.R;
import com.recipe.recipesearch.database.contents.Step;

import java.util.ArrayList;

public class InstructionsAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Step> steps;
    private Context context;

    public InstructionsAdapter(Context context, ArrayList<Step> steps){
        setContext(context);
        setSteps(steps);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return getSteps().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public Step getInstructionAtIndex(int index){ return getSteps().get(index); }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public void removeItemAtIndex(int i){
        getSteps().remove(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final Step step = getSteps().get(i);

        if(view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            view = layoutInflater.inflate(R.layout.list_instruction, viewGroup, false);

        }

        setStepNumber(view, step.getNumber());
        setStepInstructionsText(view, step.getStepInstruction());

        return view;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getSteps().size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void setStepNumber(View view, int stepNumber){
        TextView textView = view.findViewById(R.id.list_instruction_step_number);

        String num = String.valueOf(stepNumber);
        textView.setText(num);
    }

    private void setNumber(View view, String title){
        TextView instructionStepNumber = view.findViewById(R.id.list_instruction_step_number);
        instructionStepNumber.setText(title);
    }

    private void setStepInstructionsText(View view, String instructions){
        TextView textView = view.findViewById(R.id.list_instruction_step_text);

        textView.setText(instructions);
    }

    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }
}