package drawer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gietb.banhangkhoapham.ChangeInfoActivity;
import com.example.gietb.banhangkhoapham.OrderHistoryActivity;
import com.example.gietb.banhangkhoapham.R;

public class IsLogInDrawerFragment extends Fragment {
    private Button btnSignOut, btnOrderHistory, btnChangeInfo;
    private ISendButton listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ISendButton) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer_islogin, container, false);
        btnSignOut = view.findViewById(R.id.buttonSignOut);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pre = getActivity().getSharedPreferences("DATA_VALUE", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pre.edit();
                editor.putString("token", "null");
                editor.apply();
                listener.sendButton("LOG_OUT");
            }
        });
        btnOrderHistory = view.findViewById(R.id.buttonOrderHistory);
        btnOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderHistoryActivity.class);
                SharedPreferences pre = getActivity().getSharedPreferences("DATA_VALUE", Context.MODE_PRIVATE);
                String token = pre.getString("token", "null");
                intent.putExtra("TOKEN", token);
                startActivity(intent);
            }
        });
        btnChangeInfo = view.findViewById(R.id.buttonChangeInfo);
        btnChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ChangeInfoActivity.class));
            }
        });
        return view;
    }
}
