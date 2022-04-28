package com.example.projectandroid;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimationFragment extends Fragment {

    public AnimationFragment() {
    }

    public static AnimationFragment newInstance() {
        return new AnimationFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_animation, container, false);

        ImageView img = (ImageView) view.findViewById(R.id.news_img);
        /*        Animation anim = AnimationUtils.loadAnimation(Main.this,
                  R.anim.rotate);
                btn.startAnimation(anim);
        */
        ObjectAnimator animBtnRotate = ObjectAnimator.ofFloat(img, "rotation", 0, 360);
        animBtnRotate.setDuration(600000);
        animBtnRotate.setInterpolator(new CycleInterpolator(100));
        animBtnRotate.start();
        return view;
    }
}