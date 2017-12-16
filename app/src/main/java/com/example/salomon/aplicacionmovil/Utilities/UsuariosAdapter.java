package com.example.salomon.aplicacionmovil.Utilities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.salomon.aplicacionmovil.R;
import com.example.salomon.aplicacionmovil.entidad.Usuario;
import com.example.salomon.aplicacionmovil.helper.FlipAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salomon on 08/12/2017.
 */

public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.MyViewHolder> implements Filterable  {
    private Context mContext;
    private List<Usuario> usuariosList;
    private List<Usuario> usuarioListFiltered;
    private UsuarioAdapterListener listener;
    private SparseBooleanArray selectedItems;

    // array used to perform multiple animation at once
    private SparseBooleanArray animationItemsIndex;
    private boolean reverseAllAnimations = false;

    // index is used to animate only the selected row
    // dirty fix, find a better solution
    private static int currentSelectedIndex = -1;

    private static final String TAG = "UsuarioAdapter: ";

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        public TextView name, login, sexo;
        public ImageView iconImp, imgProfile;
        public LinearLayout usuarioContainer;
        public RelativeLayout iconContainer, iconBack, iconFront;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.usuario_nombre);
            sexo = (TextView) view.findViewById(R.id.usuario_sexo);
            login = (TextView) view.findViewById(R.id.usuario_login);

            iconBack = (RelativeLayout) view.findViewById(R.id.icon_back);
            iconFront = (RelativeLayout) view.findViewById(R.id.icon_front);
            imgProfile = (ImageView) view.findViewById(R.id.icon_profile);
            usuarioContainer = (LinearLayout) view.findViewById(R.id.usuario_container);
            iconContainer = (RelativeLayout) view.findViewById(R.id.icon_container);
            view.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            listener.onRowLongClicked(getAdapterPosition());
            view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
            return true;
        }
    }

    public UsuariosAdapter(Context mContext, List<Usuario> usuarioList, UsuarioAdapterListener listener) {
        this.mContext = mContext;
        this.usuariosList = usuarioList;
        this.listener = listener;

        this.usuarioListFiltered = usuarioList;

        selectedItems = new SparseBooleanArray();
        animationItemsIndex = new SparseBooleanArray();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usuario_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Usuario usuario = usuarioListFiltered.get(position);
        String varSexo = (usuario.getSexo() == 1)?"Masculino":"Femenino";
        holder.name.setText(usuario.getNombre());
        holder.sexo.setText(varSexo);
        holder.login.setText(usuario.getLogin());

        // change the row state to activated
        holder.itemView.setActivated(selectedItems.get(position, false));
        // handle icon animation
        applyIconAnimation(holder, position);
        // display profile image
        applyProfilePicture(holder, usuario);
        // apply click events
        applyClickEvents(holder, position);
    }

    private void applyClickEvents(MyViewHolder holder, final int position) {
        holder.iconContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onIconClicked(position);
            }
        });

        holder.usuarioContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUsuarioRowClicked(position);
            }
        });

        holder.usuarioContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onRowLongClicked(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    private void applyProfilePicture(MyViewHolder holder, Usuario usuario) {
        holder.imgProfile.setImageResource(R.drawable.ic_account_circle_black_48dp);
        //holder.imgProfile.setColorFilter(message.getColor());
    }

    private void applyIconAnimation(MyViewHolder holder, int position) {
        if (selectedItems.get(position, false)) {
            holder.iconFront.setVisibility(View.GONE);
            resetIconYAxis(holder.iconBack);
            holder.iconBack.setVisibility(View.VISIBLE);
            holder.iconBack.setAlpha(1);
            if (currentSelectedIndex == position) {
                FlipAnimator.flipView(mContext, holder.iconBack, holder.iconFront, true);
                resetCurrentIndex();
            }
        } else {
            holder.iconBack.setVisibility(View.GONE);
            resetIconYAxis(holder.iconFront);
            holder.iconFront.setVisibility(View.VISIBLE);
            holder.iconFront.setAlpha(1);
            if ((reverseAllAnimations && animationItemsIndex.get(position, false)) || currentSelectedIndex == position) {
                FlipAnimator.flipView(mContext, holder.iconBack, holder.iconFront, false);
                resetCurrentIndex();
            }
        }
    }

    // As the views will be reused, sometimes the icon appears as
    // flipped because older view is reused. Reset the Y-axis to 0
    private void resetIconYAxis(View view) {
        if (view.getRotationY() != 0) {
            view.setRotationY(0);
        }
    }

    public void resetAnimationIndex() {
        reverseAllAnimations = false;
        animationItemsIndex.clear();
    }

    @Override
    public long getItemId(int position) {
        return usuariosList.get(position).getCodigoUsuario();
    }

    @Override
    public int getItemCount() {
        return usuarioListFiltered.size();
    }

    public void toggleSelection(int pos) {
        currentSelectedIndex = pos;
        if (selectedItems.get(pos, false)) {
            selectedItems.delete(pos);
            animationItemsIndex.delete(pos);
        } else {
            selectedItems.put(pos, true);
            animationItemsIndex.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        reverseAllAnimations = true;
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    private void resetCurrentIndex() {
        currentSelectedIndex = -1;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    usuarioListFiltered = usuariosList;
                } else {
                    List<Usuario> filteredList = new ArrayList<>();
                    for (Usuario row : usuariosList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getNombre().toLowerCase().contains(charString.toLowerCase()) || row.getApellidoPaterno().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    usuarioListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = usuarioListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                usuarioListFiltered = (ArrayList<Usuario>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface UsuarioAdapterListener {
        void onIconClicked(int position);

        void onUsuarioRowClicked(int position);

        void onRowLongClicked(int position);
    }
}
