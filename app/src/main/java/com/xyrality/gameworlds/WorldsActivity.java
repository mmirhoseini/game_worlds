package com.xyrality.gameworlds;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xyrality.gameworlds.adapter.WorldsAdapter;
import com.xyrality.gameworlds.network.model.WorldsResponse;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Mohsen on 1/20/16.
 * A game worlds list that shows location and status.
 */
public class WorldsActivity extends AppCompatActivity {
    public static final String WORLDS_DATA = "worlds_data";
    final static String TAG = WorldsActivity.class.getSimpleName();

    // butterknife UI references.
    @Bind(R.id.worlds_recyclerview)
    RecyclerView worldsRecyclerView;

    private WorldsResponse worldsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worlds);

        // Set up the UI.
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // load data to list
        worldsData = (WorldsResponse) getIntent().getSerializableExtra(WORLDS_DATA);

        if (worldsData != null) {
            WorldsAdapter worldsAdapter = new WorldsAdapter(worldsData.getAllAvailableWorlds());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            worldsRecyclerView.setLayoutManager(linearLayoutManager);
            worldsRecyclerView.setHasFixedSize(true);
            worldsRecyclerView.setAdapter(worldsAdapter);
        } else {
            Snackbar.make(worldsRecyclerView, R.string.no_data_error, Snackbar.LENGTH_LONG).show();
        }
    }
}
