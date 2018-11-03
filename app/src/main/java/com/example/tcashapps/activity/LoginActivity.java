package com.example.tcashapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcashapps.Constant;
import com.example.tcashapps.R;
import com.example.tcashapps.SessionManagement;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.btnSign)
    Button btnSign;
    @BindView(R.id.etNomor)
    EditText etNomor;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvRegister)
    TextView tvRegister;

    private APIService apiService;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sessionManagement = new SessionManagement(this);
        apiService = APIClient.getClient().create(APIService.class);

    }

    @OnClick(R.id.btnSign)
    public void goSign(){
        Log.d(TAG, "goSign: onResponse: ");
//        startActivity(new Intent(LoginActivity.this, MainActivity.class));

        EditText[] ets = {etNomor, etPassword};
        String nomor = etNomor.getText().toString();
        String password = etPassword.getText().toString();

        if (isValidET(ets)) {
            Call<Login> call = apiService.loginUser(nomor, password);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.code() == 200) {
                        sessionManagement.setToken(response.body().getToken());
                        Toast.makeText(LoginActivity.this, "Login Sukses", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Nomor / Password Salah", Toast.LENGTH_SHORT).show();
                    }
                    Log.d(TAG, "onResponse: " + response.code());
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });
        }


    }

    @OnClick(R.id.tvRegister)
    public void goRegister(){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private Boolean isValidET(EditText[] ets){
        Boolean result = true;
        for (EditText et : ets) {
            if (et.getText().toString().isEmpty()){
                et.setError("Field cannot be empty!");
                result = false;
            }
        }
        return result;
    }
}
