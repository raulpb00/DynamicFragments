package es.raulprieto.dynamicfragment;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import es.raulprieto.dynamicfragment.databinding.FragmentABinding;

public class FragmentA extends Fragment {
    private final String LOGTAG = "DynamicFragment";
    static final String TAG = "FragmentA";
    private FragmentABinding binding;
    private OnSetTextSizeListener listener;

    /**
     * This method comes from GOOGLE CREATING FACTORY DESIGN PATTERN
     * Method which creates an object from the own FragmentB class
     * Guaranteeing calling setArguments immediately after the creation.
     *
     * @param bundle arguments
     * @return instance of FragmentB with arguments (if they were necessary)
     */
    public static Fragment newInstance(Bundle bundle) {
        FragmentA fragmentA = new FragmentA();
        if (bundle != null)
            fragmentA.setArguments(bundle);
        return fragmentA;
    }

    /**
     * This interface will be used as contract between FragmentA and its container (Activity)
     */
    public interface OnSetTextSizeListener {
        void onSetTextSize(String text, int size);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_a, container, false);
        View view = binding.getRoot();

        Log.d(LOGTAG, "FragmentA -> onCreateView()");
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (OnSetTextSizeListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement OnSetTextSizeListener");
        }

        Log.d(LOGTAG, "FragmentA -> onAttach()");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btResize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSetTextSize(binding.edMessage.getText().toString(), binding.skbSize.getProgress());
            }
        });
        Log.d(LOGTAG, "FragmentA -> onViewCreated()");
    }

    /**
     * If it's initialized anything at onAttach(), it's assign to null at onDetach().
     */
    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        Log.d(LOGTAG, "FragmentA -> onDetach()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOGTAG, "FragmentA -> onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOGTAG, "FragmentA -> onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOGTAG, "FragmentA -> onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOGTAG, "FragmentA -> onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOGTAG, "FragmentA -> onDestroy()");
    }
}
