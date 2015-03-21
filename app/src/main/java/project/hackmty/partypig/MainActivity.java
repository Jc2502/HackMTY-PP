package project.hackmty.partypig;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import io.orchestrate.client.Client;
import io.orchestrate.client.KvObject;
import io.orchestrate.client.OrchestrateClient;
import io.orchestrate.client.ResponseAdapter;
import project.hackmty.partypig.models.User;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Client client = OrchestrateClient.builder("6af7467f-116e-4031-ad8c-0b8d743dcc5e")
                .build();


        client.kv("users", "1234")
                .get(User.class)
                .on(new ResponseAdapter<KvObject<User>>() {
                    @Override
                    public void onFailure(final Throwable error) {
                        // handle errors
                    }

                    @Override
                    public void onSuccess(final KvObject<User> object) {
                        if (object == null) {
                            // we received a 404, no KV object exists
                            Toast.makeText(getApplicationContext(),"No encontro", Toast.LENGTH_SHORT).show();
                        }

                       User data = object.getValue();


                        // do something with the 'data'
                        Toast.makeText(getApplicationContext(), data.getUserId(), Toast.LENGTH_SHORT).show();
                    }
                });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
