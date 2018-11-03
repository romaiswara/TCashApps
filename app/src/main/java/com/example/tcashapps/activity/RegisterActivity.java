package com.example.tcashapps.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcashapps.R;
import com.example.tcashapps.model.retrofit.APIClient;
import com.example.tcashapps.model.retrofit.APIService;
import com.example.tcashapps.model.retrofit.Login;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.etFirstName)
    EditText etFirstName;
    @BindView(R.id.etLastName)
    EditText etLastName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etRetypePassword)
    EditText etRetypePassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvLogin)
    TextView tvLogin;

    APIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        apiService = APIClient.getClient().create(APIService.class);
    }

    @OnClick(R.id.btnRegister)
    public void goRegiter(){
        EditText[] ets = {
                etFirstName, etLastName, etEmail, etPhone, etPassword, etRetypePassword
        };
        String name = etFirstName.getText().toString()+" "+etLastName.getText().toString();
        String email = etEmail.getText().toString();
        String phone = etPhone.getText().toString();
        String pass = etPassword.getText().toString();
        String retypePass = etRetypePassword.getText().toString();

        if (isValidET(ets) && isValidEmail(email) && isValidPassword(pass, retypePass)){

//            new Intent(RegisterActivity.this, LoginActivity.class);
//            Toast.makeText(this, "Register Sukses", Toast.LENGTH_SHORT).show();
//            finish();

            Call<Login> call = apiService.registerUser(name, email, pass, phone);
            call.enqueue(new Callback<Login>() {
                @Override
                public void onResponse(Call<Login> call, Response<Login> response) {
                    if (response.code() == 200){
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Register Gagal", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Login> call, Throwable t) {

                }
            });

        }
    }

    @OnClick(R.id.tvLogin)
    public void goLogin(){
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    private boolean isValidEmail(String email) {
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email not valid");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String pass, String retypePass){
        if (!pass.equals(retypePass)){
            etRetypePassword.setError("Password does not match");
            return false;
        }
        return true;
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
