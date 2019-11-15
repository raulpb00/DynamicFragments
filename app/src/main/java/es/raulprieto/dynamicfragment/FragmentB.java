package es.raulprieto.dynamicfragment;


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
    public static final String TAG = "FragmentB";
    private final String SIZE = "size";
    private final String TEXT = "text";
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_b, container, false);
        View view = binding.getRoot();

        Log.d(TAG, "FragmentB -> onCreateView()");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            binding.tvMessage.setTextSize(bundle.getFloat(SIZE));
            binding.tvMessage.setText(bundle.getString(TEXT));
        }
    }

    /**
     * This method stores the fragment's dynamic state inside of the bundle
     *
     * @param outState bundle
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putFloat(SIZE, binding.tvMessage.getTextSize());
        outState.putString(TEXT, binding.tvMessage.getText().toString());
        Log.d(TAG, "FragmentB -> onSaveInstanceState()");
    }

    /**
     * This method restores the dynamic state stored into the savedInstanceState bundle
     *
     * @param savedInstanceState bundle
     */
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            binding.tvMessage.setTextSize(TypedValue.COMPLEX_UNIT_PX, savedInstanceState.getFloat(SIZE));
            binding.tvMessage.setText(savedInstanceState.getString(TEXT));
        }
        Log.d(TAG, "FragmentB -> onViewStateRestored()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "FragmentB -> onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "FragmentB -> onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "FragmentB -> onDestroy()");
    }

}
