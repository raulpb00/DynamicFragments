package es.raulprieto.dynamicfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import es.raulprieto.dynamicfragment.databinding.FragmentBBinding;

public class FragmentB extends Fragment {
    private final String LOGTAG = "DynamicFragment";
    public static final String TAG = "FragmentB";
    private final String SIZE = "size";
    private final String TEXT = "text";

    /**
     * Values are retain at these two fields
     */
    private float size;
    private String text;
    //******************************************************

    private FragmentBBinding binding;

    /**
     * This method comes from GOOGLE CREATING FACTORY DESIGN PATTERN
     * Method which creates an object from the own FragmentB class
     * Guaranteeing calling setArguments immediately after the creation.
     *
     * @param bundle arguments
     * @return instance of FragmentB with arguments (if they were necessary)
     */
    public static Fragment newInstance(Bundle bundle) {
        FragmentB instance = new FragmentB();
        instance.setArguments(bundle);
        return instance;
    }

    /**
     * This Fragment will retain
     *
     * @param savedInstanceState data
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        Log.d(LOGTAG, "FragmentB -> onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false);
        View view = binding.getRoot();

        Log.d(LOGTAG, "FragmentB -> onCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            // It must be checked if the Fragment has got arguments
            Bundle bundle = getArguments();
            if (bundle != null) {
                size = bundle.getFloat(SIZE);
                text = bundle.getString(TEXT);
            }
            Log.d(LOGTAG, "FragmentB -> 1st onViewCreated()");
        }
        binding.tvMessage.setTextSize(size);
        binding.tvMessage.setText(text);
        Log.d(LOGTAG, "FragmentB -> onViewCreated()");
    }

    /*
     * This method stores the fragment's dynamic state inside of the bundle
     *
     * @param outState bundle
     *//*
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(SIZE, binding.tvMessage.getTextSize());
        outState.putString(TEXT, binding.tvMessage.getText().toString());
        Log.d(LOGTAG, "FragmentB -> onSaveInstanceState()");
    }

    *//*
     * This method restores the dynamic state stored into the savedInstanceState bundle
     *
     * @param savedInstanceState bundle
     *//*
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            binding.tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, savedInstanceState.getFloat(SIZE));
            binding.tvMessage.setText(savedInstanceState.getString(TEXT));
        }
        Log.d(LOGTAG, "FragmentB -> onViewStateRestored()");
    }*/

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOGTAG, "FragmentB -> onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOGTAG, "FragmentB -> onStop()");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(LOGTAG, "FragmentB -> onAttach()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOGTAG, "FragmentB -> onDestroy()");
    }

}
