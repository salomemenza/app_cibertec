package com.example.salomon.aplicacionmovil;

import android.app.SearchManager;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.salomon.aplicacionmovil.DAO.UsuarioDAO;
import com.example.salomon.aplicacionmovil.Utilities.MyDividerItemDecoration;
import com.example.salomon.aplicacionmovil.Utilities.UsuariosAdapter;
import com.example.salomon.aplicacionmovil.entidad.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuariosActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, UsuariosAdapter.UsuarioAdapterListener {
    private static final String TAG = UsuariosActivity.class.getSimpleName();
    private List<Usuario> usuarioList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UsuariosAdapter mAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ActionModeCallback actionModeCallback;
    private ActionMode actionMode;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.usuarios_activity_title);

        recyclerView = (RecyclerView) findViewById(R.id.usuario_recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_usuario);
        swipeRefreshLayout.setOnRefreshListener(this);

        usuarioList = new ArrayList<>();
        mAdapter = new UsuariosAdapter(this,usuarioList,this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new MyDividerItemDecoration(this,LinearLayoutManager.VERTICAL,16));
        recyclerView.setAdapter(mAdapter);

        actionModeCallback = new ActionModeCallback();

        // show loader and fetch messages
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        getUsuarioData();
                    }
                }
        );
    }

    private void getUsuarioData(){
        swipeRefreshLayout.setRefreshing(true);
        usuarioList.clear();

        UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
        ArrayList<Usuario> listaDeUsuarios = usuarioDAO.listarUsuarios();

        for (Usuario objUsuario: listaDeUsuarios) {
            usuarioList.add(objUsuario);
        }

        swipeRefreshLayout.setRefreshing(false);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
        public void onBackPressed() {
        // close search view on back button pressed
        /*if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();*/
    }

    @Override
    public void onRefresh() {
        // swipe refresh is performed, fetch the messages again
        getUsuarioData();
    }

    @Override
    public void onIconClicked(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }

        toggleSelection(position);
    }

    @Override
    public void onUsuarioRowClicked(int position) {
        // verify whether action mode is enabled or not
        // if enabled, change the row state to activated
         if (mAdapter.getSelectedItemCount() > 0) {
            enableActionMode(position);
        } else {
            // read the message which removes bold from the row
            Usuario usuario = usuarioList.get(position);
            usuarioList.set(position, usuario);
            mAdapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "Seleccionó: " + usuario.getNombre(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRowLongClicked(int position) {
        // long press is performed, enable action mode
        enableActionMode(position);
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        mAdapter.toggleSelection(position);
        int count = mAdapter.getSelectedItemCount();

        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private class ActionModeCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);

            // disable swipe refresh if action mode is enabled
            swipeRefreshLayout.setEnabled(false);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    // delete all the selected messages
                    eliminarUsuarios();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mAdapter.clearSelections();
            swipeRefreshLayout.setEnabled(true);
            actionMode = null;
            recyclerView.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter.resetAnimationIndex();
                    mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void eliminarUsuarios() {
        mAdapter.resetAnimationIndex();
        List<Integer> selectedItemPositions =
                mAdapter.getSelectedItems();
        for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
            Integer key = selectedItemPositions.get(i);

            Usuario objUsuario = usuarioList.get(key);
            UsuarioDAO usuarioDAO = new UsuarioDAO(getApplicationContext());
            usuarioDAO.eliminar(objUsuario.getCodigoUsuario());

            Log.i("Eliminar: ", "nombre : "+objUsuario.getNombre());
        }

        //refrescar la grilla
        getUsuarioData();
        mAdapter.notifyDataSetChanged();
    }
}
