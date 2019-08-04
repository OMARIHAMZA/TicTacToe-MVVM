package omari.hamza.tictactoe;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import omari.hamza.tictactoe.databinding.ActivityMainBinding;
import omari.hamza.tictactoe.model.Player;
import omari.hamza.tictactoe.viewmodel.GameViewModel;

public class MainActivity extends AppCompatActivity {

    private GameViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDataBinding("Hamza", "Schiller");
    }

    private void initDataBinding(String player1, String player2){
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mViewModel.init(player1, player2);
        activityMainBinding.setGameViewModel(mViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        mViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }


    @VisibleForTesting
    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.getName()) ? "No Winner" : winner.getName();
        Toast.makeText(this, winnerName, Toast.LENGTH_SHORT).show();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
